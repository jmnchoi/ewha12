package com.example.seethru;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ListView;

public class HistoryActivity extends Activity {
	private SQLiteDatabase db;
	private SQLiteOpenHelper helper;
	private String date;
	private String result;
	private String imageBase64;
	static ArrayList<HistoryData> historyList;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_list);

		helper = new HistoryDB(HistoryActivity.this, "seethru12_history.db", null, 1);
		listView = (ListView)findViewById(R.id.listview_history);

		selectAll();
		
		listView.setOnItemClickListener(null);
	}

	private void selectAll() {
		db = helper.getReadableDatabase();
		historyList = new ArrayList<HistoryData>();
		historyList.clear();
		Cursor c = db.query("history", null, null, null, null, null, null);

		while (c.moveToNext()) {
			date = c.getString(c.getColumnIndex("date"));
			result = c.getString(c.getColumnIndex("result"));
			imageBase64 = c.getString(c.getColumnIndex("image"));

			HistoryData historyData = new HistoryData(date, imageBase64, result);
			historyList.add(historyData);
		}
		c.close();
		db.close();

		HistoryAdapter adapter = new HistoryAdapter(this, historyList);;
		listView.setAdapter(adapter);
	}
}
