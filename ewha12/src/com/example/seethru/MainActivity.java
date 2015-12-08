package com.example.seethru;

import com.luxand.FSDK;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener{
	// FaceSDK License Key
	private String key = "ilF1JFcecPkol2UmQpiMDWLJYaI50VXIZ9K9l5BatDSg55Cr"
			+ "/L/MAZ2OQbf8lr9Wypw5xYiLjFt0ER+Rdpr87nBeZUv9+fR2B6d"
			+ "/A1svnUHWLiWgJ3H580uuitjTxlYb+ejzVF3wSb5k+00MYvGFfmoiKu8p+YFJuXKG4gz3LNw=";
	private boolean processing = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (processing == true) {
			new InitializeBackground().execute(key);
		}

		ImageButton settingBtn = (ImageButton)findViewById(R.id.imgbtn_main_setting);
		settingBtn.setOnClickListener(this);
		ImageButton startBtn = (ImageButton)findViewById(R.id.imgbtn_main_start);
		startBtn.setOnClickListener(this);
		ImageButton pushBtn = (ImageButton)findViewById(R.id.imgbtn_main_push);
		pushBtn.setOnClickListener(this);
		
		SettingActivity.pref = getSharedPreferences("autosave", MODE_PRIVATE);
		SettingActivity.check = SettingActivity.pref.getBoolean("value", true);		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imgbtn_main_setting:
			Intent intent = new Intent (MainActivity.this, SettingActivity.class);
			startActivity(intent);
			break;
		case R.id.imgbtn_main_push:
		case R.id.imgbtn_main_start:
			CharSequence[] items = {"카메라로 촬영하기", "앨범에서 불러오기"};
			AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
			ad.setTitle("사진 선택");
			ad.setItems(items, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					Intent intent = new Intent(MainActivity.this, DetectActivity.class);
					intent.putExtra("option", item);
					startActivity(intent);
				}
			});
			ad.show();
			break;
		}
	}

	private class InitializeBackground extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... params) {
			String log = new String();
			try {	    	
				int res = FSDK.ActivateLibrary(params[0]);
				FSDK.Initialize();
				FSDK.SetFaceDetectionParameters(false, false, 100);
				FSDK.SetFaceDetectionThreshold(5);

				if (res == FSDK.FSDKE_OK) {
					Log.i("Result", "FaceSDK activated");
					processing = false;
				} else {
					Log.i("Result", "Error activating FaceSDK: " + res);
				}
			}
			catch (Exception e) {
				Log.i("Result", "exception " + e.getMessage());
			}
			return log;
		}   
	}
}
