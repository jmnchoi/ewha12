package com.example.seethru;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CheckBox;
import android.widget.ImageButton;

public class SettingActivity extends Activity implements View.OnClickListener {
	static SharedPreferences pref;
	static boolean check;
	private CheckBox checkBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);

		ImageButton autosaveBtn = (ImageButton)findViewById(R.id.imgbtn_setting_autosave);
		autosaveBtn.setOnClickListener(this);
		ImageButton historyBtn = (ImageButton)findViewById(R.id.imgbtn_setting_history);
		historyBtn.setOnClickListener(this);
		ImageButton developerBtn = (ImageButton)findViewById(R.id.imgbtn_setting_developer);
		developerBtn.setOnClickListener(this);
		
		checkBox = (CheckBox)findViewById(R.id.checkbox_setting);
		checkBox.setChecked(check);

		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				check = isChecked;
				checkBox.setChecked(check);
				SharedPreferences.Editor editor = pref.edit();
				editor.putBoolean("value", check);
				editor.commit();
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (v.getId()) {
		case R.id.imgbtn_setting_autosave:
			check = !check;
			checkBox.setChecked(check);
			break;
		case R.id.imgbtn_setting_developer:
			intent = new Intent (SettingActivity.this, DeveloperActivity.class);
			startActivity(intent);
			break;
		case R.id.imgbtn_setting_history:
			intent = new Intent (SettingActivity.this, HistoryActivity.class);
			startActivity(intent);
			break;
		}
	}
}