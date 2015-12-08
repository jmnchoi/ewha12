package com.example.seethru;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

public class HistoryAdapter extends BaseAdapter {
	ArrayList<HistoryData> historyList;
	private LayoutInflater inflater;
	SQLiteOpenHelper helper;
	SQLiteDatabase db;

	public HistoryAdapter(Context context, ArrayList<HistoryData> historyList) {
		// TODO Auto-generated constructor stub
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.historyList = historyList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return historyList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return historyList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.history_item, parent, false);
		}
		helper = new HistoryDB(convertView.getContext(), "seethru12_history.db", null, 1);
		db = helper.getReadableDatabase();

		ImageView photo = (ImageView)convertView.findViewById(R.id.imgview_history);
		ImageButton resultBtn = (ImageButton)convertView.findViewById(R.id.imgbtn_history_result);
		ImageButton deleteBtn = (ImageButton)convertView.findViewById(R.id.imgbtn_history_delete);

		resultBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String result = historyList.get(position).getResult();
				Intent intent = new Intent(v.getContext(), ResultActivity.class);
				intent.putExtra("content", result);
				intent.putExtra("isSaved", true);
				v.getContext().startActivity(intent);
			}
		});

		deleteBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder ad = new AlertDialog.Builder(v.getContext());
				ad.setIcon(android.R.drawable.ic_dialog_alert);
				ad.setTitle("관상결과를 삭제하시겠습니까?");
				ad.setPositiveButton("네", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
						db = helper.getWritableDatabase();
						String date = historyList.get(position).getDate();
						String sql = "DELETE FROM history WHERE date = '" + date + "'";
						db.execSQL(sql);
						db.close();
						HistoryActivity.historyList.remove(position);
						notifyDataSetChanged();
					}
				});
				ad.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// User clicked Cancel so do some stuff 
						dialog.cancel();
					}
				});
				ad.show();
			}
		});

		// convert string to image
		byte[] imageByte = Base64.decode(historyList.get(position).getPhoto(), 0);
		ByteArrayInputStream is = new ByteArrayInputStream(imageByte);
		Bitmap imageBitmap = BitmapFactory.decodeStream(is);
		photo.setImageBitmap(imageBitmap);

		return convertView; // 뷰리턴
	}
}
