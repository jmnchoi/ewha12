package com.example.seethru;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.luxand.FSDK;
import com.luxand.FSDK.FSDK_Features;
import com.luxand.FSDK.HImage;
import com.luxand.FSDK.TFacePosition;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class DetectActivity extends Activity {
	private final static int RESULT_TAKE_PICTURE = 100;
	private final static int RESULT_LOAD_IMAGE = 200;
	static Bitmap imageBitmap;
	private HImage oldpicture;
	private String picturePath;
	private Uri imageUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.face_detect);

		Bundle b = getIntent().getExtras();
		int option = b.getInt("option");

		if (option == 0) {
			Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
			imageUri = getOutputMediaFileUri(); // create a file to save the image
			i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); // set the image file name
			startActivityForResult(i, RESULT_TAKE_PICTURE);
		}
		else {
			Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(i, RESULT_LOAD_IMAGE);
		}

		ImageButton resultBtn = (ImageButton)findViewById(R.id.imgbtn_detect_result);
		resultBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DetectActivity.this, ResultActivity.class);
				intent.putExtra("isSaved", false);
				startActivity(intent);
			}
		});
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case RESULT_TAKE_PICTURE:
				picturePath = imageUri.getPath();
				break;
			case RESULT_LOAD_IMAGE:
				//				if (data != null)
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };
				Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
				cursor.moveToFirst();
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				picturePath = cursor.getString(columnIndex);
				cursor.close();
				break;
			}
			Log.i("Result", "Processing...");
			new DetectFaceInBackground().execute(picturePath);
		} else {
			finish();
			Log.i("Result", "Fail...");
		}

		if (picturePath == null) {
			finish();
		}
	}

	/** Create a file Uri for saving an image or video */
	private static Uri getOutputMediaFileUri(){
		return Uri.fromFile(getOutputMediaFile());
	}

	/** Create a File for saving an image or video */
	private static File getOutputMediaFile(){
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
				Environment.DIRECTORY_PICTURES), "SeeThru12");

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()){
			if (!mediaStorageDir.mkdirs()){
				Log.d("SeeThru12", "failed to create directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File mediaFile = new File(mediaStorageDir.getPath() + File.separator +
				"IMG_"+ timeStamp + ".jpg");

		return mediaFile;
	}

	// Subclass for async processing of FaceSDK functions.
	// If long-run task runs in foreground - Android kills the process.
	private class DetectFaceInBackground extends AsyncTask<String, Void, String> {
		protected FSDK_Features features;
		protected TFacePosition faceCoords;

		protected HImage picture;
		protected int result;

		@Override
		protected String doInBackground(String... params) {
			String log = new String();
			picturePath = params[0];
			faceCoords = new TFacePosition();
			faceCoords.w = 0;
			picture = new HImage();
			result = FSDK.LoadImageFromFile(picture, picturePath);
			if (result == FSDK.FSDKE_OK) {
				result = FSDK.DetectFace(picture, faceCoords);
				features = new FSDK_Features();
				if (result == FSDK.FSDKE_OK) {
					result = FSDK.DetectFacialFeaturesInRegion(picture, faceCoords, features);
				}
			}
			return log;
		}   

		@Override
		protected void onPostExecute(String resultstring) {
			if (result != FSDK.FSDKE_OK) {
				AlertDialog.Builder ad = new AlertDialog.Builder(DetectActivity.this);
				String message = "얼굴을 찾지 못했습니다. 얼굴 정면이 잘 나온 사진을 촬영/선택해주세요.";
				ad.setIcon(android.R.drawable.ic_dialog_alert);
				ad.setMessage(message);
				ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						finish();
					}
				});
				ad.show();
				return;
			}

			FaceImageView faceImgView = (FaceImageView) findViewById(R.id.faceimgview_detect);
			imageBitmap = BitmapFactory.decodeFile(picturePath);
			faceImgView.setImageBitmap(imageBitmap);

			Log.i("Result", resultstring);

			faceImgView.detectedFace = faceCoords;
			if (features.features[0] != null) // if detected
				faceImgView.facial_features = features;

			int [] realWidth = new int[1];
			FSDK.GetImageWidth(picture, realWidth);
			faceImgView.faceImageWidthOrig = realWidth[0];
			faceImgView.invalidate(); // redraw, marking up faces and features

			if (oldpicture != null)
				FSDK.FreeImage(oldpicture);
			oldpicture = picture;

			Log.i("Result", "Done");

			// crop image to save thumbnail
			imageBitmap = Bitmap.createScaledBitmap(imageBitmap, 300, 300, true);
//			imageBitmap = Bitmap.createScaledBitmap(imageBitmap, 100, 100*imageBitmap.getHeight()/imageBitmap.getWidth(), true);
		}

		@Override
		protected void onPreExecute() {
		}
		@Override
		protected void onProgressUpdate(Void... values) {
		}
	}
}
