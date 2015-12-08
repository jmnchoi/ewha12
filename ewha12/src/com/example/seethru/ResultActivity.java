package com.example.seethru;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class ResultActivity extends Activity implements OnClickListener{
	private SQLiteDatabase db;
	private SQLiteOpenHelper helper;
	private String content;
	private String timeStamp;
	private String imageBase64;
	private boolean isSaved;
	static int aIris;
	static int aEyebrowSlope;
	static int aEyebrow;
	static int aEyeSlope;
	static int aEye;
	static int aNose;
	static int aLipCorner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle b = getIntent().getExtras();
		isSaved = b.getBoolean("isSaved");

		if (isSaved == true) {
			setContentView(R.layout.history_result);
			content = b.getString("content");
		} else {
			setContentView(R.layout.result);
			content = "";

			String eyebrowIntro = "[����]\n";
			String eyeIntro = "\n[��]\n";
			String noseIntro = "\n[��]\n";
			String lipIntro = "\n[��]\n";
			String eyebrowSlope = "";
			String eyebrow = "";
			String eyeSlope = "";
			String eye = "";
			String iris = "";
			String nose = "";
			String lip = "";
			String lipCorner = "";
			double irisR;

			// �� ����
			if (FaceImageView.eyeInnerCorner25y > FaceImageView.eyeOuterCorner26y) {
				eyeSlope = "�ö� ������ ���õǰ� ������ �λ��� �ָ� ��ǰ�� ���ϰ� ���������� �����ϰ� �����ϴ�. "
						+ "�γ��� ���������� �ߵǾ� �����ϰ� ����̰� ���� �Ǵ��� �߳����� ����̴�. "
						+ "�������̰� ��п� ������ �ൿ�� �����ϴ� Ÿ���̸� ���� ó���� ���� ���� ��ġ��� ���̴�. ";       
				aEyeSlope = 2;
			}
			else { 
				eyeSlope = "������ ������ �����ϰ� ���� �λ��� �־� �ε巴�� ħ���ϸ� ������ �� �巯���� �ʴ´�. "
						+ "�����ϰ� �������̳� ��ġ�� ������ �ȸ��� ����� � ��Ȳ������ �� ��ó�Ѵ�. "
						+ "��ǥ�� ���� �����ְ� �ڽ��� �� �ٽ��� ��⸸�����̸� ����ڿ� ��� �ط��� �� �ִ�. ";
				aEyeSlope = 1;
			}

			// ������ ��ġ
			irisR = FaceImageView.eyeRIrisCorner30x - FaceImageView.eye0x;

			if ((FaceImageView.eye0y + irisR) < FaceImageView.eyeLowerLine27y 
					&& (FaceImageView.eye0y - irisR) < FaceImageView.eyeUpperLine28y) {
				iris = "�Ϲ���� �����ϰ� ���� �߿��� �ִ�. "
						+ "���䰡�� �������� ���� ���� �����ϰ� �Ǹ� ������ �İ��� ��Ÿ�Ϸ� �ڽ��� ������� �а��� ������ ������ ���е� �ִ�. "
						+ "�ñ�� �������� ����, ������ ���� ������ ������ �ʾ� ���˳� ��Ȥ�� ���� �� ���ɼ��� �ִ�. ";
				aIris = 1;
			} else if ((FaceImageView.eye0y + irisR) > FaceImageView.eyeLowerLine27y 
					&& (FaceImageView.eye0y - irisR) > FaceImageView.eyeUpperLine28y) {
				iris = "������ ������ ����� ���ϰ� �ڽ��� ������ ������ �ʴ� ���̴�. "
						+ "�ı����̰� ����Ÿ������ ���� ������ ������ ���������Ͽ� ������ �ҿ� ���ϴ�. ";
				aIris = 2;
			} else if ((FaceImageView.eye0y + irisR) < FaceImageView.eyeLowerLine27y 
					&& (FaceImageView.eye0y - irisR) > FaceImageView.eyeUpperLine28y) {
				iris = "������ �̼����� �ͺ��� ���������� ġ�����־� �����ǽĿ� ��߳��� ���� �������� ���� �����ؾ��� ���̴�. "
						+ "�̼��� �Ұ� �Ǹ� �ƹ��� ������ ���ϱ⿡ ���ϴ� ���� ��å. ";
				aIris = 3;
			} else {
				aIris = 0;
			}

			// �� ����
			double eyeHeight = FaceImageView.eyeLowerLine27y - FaceImageView.eyeUpperLine28y;
			double eyeWidth = FaceImageView.eyeInnerCorner24x - FaceImageView.eyeOuterCorner23x;

			if (eyeWidth / eyeHeight > 3.3) {
				eye = "���� ���� ������ �������̱⺸�ٴ� ġ���ϰ� �跫���� �γ����̴�. ";
			} else if (eyeWidth / eyeHeight < 2.8) {
				eye = "���� ���� ���� ������ �������̰� �����⸦ �� Ÿ�� ���̴�. ";
			} else {
				eye = "�����̰� ġ���� �γ��� �������̰� �������� �ɷ��� ��ȭ�� �̷� �̻����� ���� ������. ";
			}

			if (eyeWidth / eyeHeight < 1.5) {
				eye += "�ձ� ���� ���� ����� �Ӹ��� ���� ���߷��� �ִ� ���̴�. ";
				aEye = 1;
			} else {
				aEye = 0;
			}

			// ���� ����
			if (FaceImageView.eyebrowMiddle21y < FaceImageView.eyebrowInnerCorner14x) {
				eyebrowSlope = "�ö� ������ �⼼�� ����ϰ� �Ż翡 �и��ϰ� Ȯ���� �����̸� �ºο��� ���� �־� �׻� ���������� ���ϴ� ���̴�. ";
				aEyebrowSlope = 1;
			} else {
				eyebrowSlope = "������ ������ ������ �¼��Ͽ� �ٸ� ����� ���� �����ϰ� �����־� �ŷڸ� ���� ��´�. "
						+ "��õ���̸� �����ϰ� ȣ���� ���ݿ� �ֺ� ������ ȭ���� ���Ѵ�. ���� �δ� ���� �� ���ϱ⵵. ";
				aEyebrowSlope = 2;
			}

			// ���� ����
			if (FaceImageView.eyebrowOuterCorner15x > FaceImageView.eyeOuterCorner26x) {
				eyebrow = "������ ����� �Ѹ��ϸ� ��� ���� ����. �Ǹ��� �ְ� ���� ������ �����ϰ� å�Ӱ��� ���ϴ�. ";
				aEyebrow = 0;
			} else {
				eyebrow = "������ ª�� ���ϰ� ������� ȥ�� �ִ� ���� �����ϴ� ���̴�. "
						+ "�Ͽ� ���� �������� ���ϰ�, ����ɰ� �ºο��� ���ϴ�. ���� �����̴� ���� �� �°� ������ ���ù޴� ������ �� �´´�. ";
				aEyebrow = 1;
			}

			// �ຼ ����
			if (FaceImageView.eyeInnerCorner25x - FaceImageView.eyeInnerCorner24x 
					< FaceImageView.noseRWingOuter46x - FaceImageView.noseLWingOuter45x){
				nose = "�ຼ�� �������� �ں�Ӱ� ���� ����. ó������ �پ�� �繰���� ����. ";
				aNose = 1;
			} else {
				nose = "�ຼ�� ���������� ���� ��� �־���� ������ ���� �������� ���� ������ �繰�� �����µ� ������� �ִ�. "
						+ "���� ����ں��� ������ ����ڸ� ���� ���ɼ��� ũ��. ";
				aNose = 2;
			}

			// �Լ� �β�
			if (FaceImageView.mouthBottom55y - FaceImageView.mouthInnerBottom64y 
					< FaceImageView.mouthInnerTop61y - FaceImageView.mouthTop54y) {
				lip = "���Լ��� �β��� ����� �ְ��� ���� ���̳� �����ٰ��� �������� ���ΰ��谡 �����ϰ� �ٸ��ϰ� �����Ͽ� �繰���� ����. ";
			} else {
				lip = "�Ʒ��Լ��� �β��� ����� ��ǰ�� �����ϰ� �ڱ� ������� �ൿ�ϱ� ���쳪 "
						+ "�����̳� �µ��� �������ϰ� �����ϸ� �Ż翡 ��� ����. ������ ��� �Ʒ��Լ��� �����ϸ� �̼����� ����. ";
			}

			// �Բ���
			if ((FaceImageView.mouthRCorner3y + FaceImageView.mouthLCorner4y) / 2 
					< FaceImageView.mouthInnerBottom64y) {
				lipCorner = "�ö� �Բ����� ���� �����ְ� ���ϸ� ���� �پ�� �������̸� �������� �������� ������ ����� ���� �޴´�. ";
				aLipCorner = 2;
			} else {
				lipCorner = "������ �Բ����� �Ż翡 ħ���ϸ� ������ �������̸� "
						+ "��ǥ�� ���� �̷���� �ϴ� ���� ������ �ų��� ���� ����� �Ͽ��� ��鸲�� ����. �ټ� ����ϸ� ���Ѹ��� �ִ�. ";
				aLipCorner = 1;
			}

			content = eyebrowIntro + eyebrowSlope + eyebrow + "\n" + eyeIntro + eyeSlope + eye + iris
					+ "\n" + noseIntro + nose + "\n" + lipIntro + lip + lipCorner;

			// convert image to string
			timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			imageBase64 = bitmapToString(DetectActivity.imageBitmap);

			if (SettingActivity.check == true) {
				insert(timeStamp, content, imageBase64);
				Toast.makeText(getApplicationContext(), "�������� �ڵ�����Ǿ����ϴ�.", Toast.LENGTH_LONG).show();
				isSaved = true;
			}
			ImageButton adviceBtn = (ImageButton)findViewById(R.id.imgbtn_result_advice);
			adviceBtn.setOnClickListener(this);
			ImageButton saveBtn = (ImageButton)findViewById(R.id.imgbtn_result_save);
			saveBtn.setOnClickListener(this);
		}
		ImageButton shareBtn = (ImageButton)findViewById(R.id.imgbtn_result_share);
		shareBtn.setOnClickListener(this);
		ImageButton homeBtn = (ImageButton)findViewById(R.id.imgbtn_result_home);
		homeBtn.setOnClickListener(this);
		TextView result = (TextView)findViewById(R.id.textview_result);
		result.setText(content);
	}

	private String bitmapToString(Bitmap bitmap) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.PNG, 100, os);
		byte[] imageByte = os.toByteArray();
		try {
			os.flush();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Base64.encodeToString(imageByte, 0);
	}

	private void insert(String timeStamp, String result, String imageBase64) {
		helper = new HistoryDB(ResultActivity.this, "seethru12_history.db", null, 1);
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("date", timeStamp);
		values.put("result", result);
		values.put("image", imageBase64);
		db.insert("history", null, values);
		db.close();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (v.getId()) {
		case R.id.imgbtn_result_home:
			intent = new Intent(ResultActivity.this, MainActivity.class);
			startActivity(intent);
			break;
		case R.id.imgbtn_result_advice:
			intent = new Intent(ResultActivity.this, AdviceActivity.class);
			startActivity(intent);
			break;
		case R.id.imgbtn_result_save:
			if (SettingActivity.check == true || isSaved == true) {
				Toast.makeText(getApplicationContext(), "�������� �̹� ����Ǿ����ϴ�.", Toast.LENGTH_LONG).show();
			}
			else {
				insert(timeStamp, content, imageBase64);
				isSaved = true;
				Toast.makeText(getApplicationContext(), "�������� ����Ǿ����ϴ�.", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.imgbtn_result_share:
			intent = new Intent();
			intent.setAction(Intent.ACTION_SEND);
			intent.putExtra(Intent.EXTRA_TEXT, content);
			intent.setType("text/plain");
			startActivity(intent);
			break;
		}

	}
}
