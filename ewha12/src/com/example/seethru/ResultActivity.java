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

			String eyebrowIntro = "[눈썹]\n";
			String eyeIntro = "\n[눈]\n";
			String noseIntro = "\n[코]\n";
			String lipIntro = "\n[입]\n";
			String eyebrowSlope = "";
			String eyebrow = "";
			String eyeSlope = "";
			String eye = "";
			String iris = "";
			String nose = "";
			String lip = "";
			String lipCorner = "";
			double irisR;

			// 눈 기울기
			if (FaceImageView.eyeInnerCorner25y > FaceImageView.eyeOuterCorner26y) {
				eyeSlope = "올라간 눈으로 세련되고 영리한 인상을 주며 성품이 강하고 솔직하지만 예민하고 성급하다. "
						+ "두뇌에 혈류공급이 잘되어 예리하고 눈썰미가 좋아 판단을 잘내리는 사람이다. "
						+ "즉흥적이고 기분에 따르는 행동을 많이하는 타입이며 일을 처리할 때도 빨리 해치우는 편이다. ";       
				aEyeSlope = 2;
			}
			else { 
				eyeSlope = "내려간 눈으로 순박하고 선한 인상을 주어 부드럽고 침착하며 본심을 잘 드러내지 않는다. "
						+ "유순하고 느린편이나 눈치가 빠르며 안목이 대단해 어떤 상황에서도 잘 대처한다. "
						+ "목표를 세워 끈기있게 자신을 잘 다스려 대기만성형이며 배우자와 평생 해로할 수 있다. ";
				aEyeSlope = 1;
			}

			// 눈동자 위치
			irisR = FaceImageView.eyeRIrisCorner30x - FaceImageView.eye0x;

			if ((FaceImageView.eye0y + irisR) < FaceImageView.eyeLowerLine27y 
					&& (FaceImageView.eye0y - irisR) < FaceImageView.eyeUpperLine28y) {
				iris = "하백안은 냉정하고 숨은 야욕이 있다. "
						+ "집념가의 전형으로 무슨 일을 시작하게 되면 끝까지 파고드는 스타일로 자신의 의지대로 밀고나가 성공도 하지만 실패도 있다. "
						+ "시기와 질투심이 많고, 목적을 위해 수단을 가리지 않아 범죄나 잔혹한 짓을 할 가능성이 있다. ";
				aIris = 1;
			} else if ((FaceImageView.eye0y + irisR) > FaceImageView.eyeLowerLine27y 
					&& (FaceImageView.eye0y - irisR) > FaceImageView.eyeUpperLine28y) {
				iris = "상백안은 집념이 대단히 강하고 자신의 주장을 굽히지 않는 편이다. "
						+ "파괴적이고 이해타산적인 면이 있으며 마음이 오락가락하여 간교한 꾀에 능하다. ";
				aIris = 2;
			} else if ((FaceImageView.eye0y + irisR) < FaceImageView.eyeLowerLine27y 
					&& (FaceImageView.eye0y - irisR) > FaceImageView.eyeUpperLine28y) {
				iris = "사백안은 이성적인 것보다 감정적으로 치우쳐있어 윤리의식에 어긋나는 일을 저지르기 쉬워 조심해야할 상이다. "
						+ "이성을 잃게 되면 아무도 말리지 못하기에 피하는 것이 상책. ";
				aIris = 3;
			} else {
				aIris = 0;
			}

			// 눈 길이
			double eyeHeight = FaceImageView.eyeLowerLine27y - FaceImageView.eyeUpperLine28y;
			double eyeWidth = FaceImageView.eyeInnerCorner24x - FaceImageView.eyeOuterCorner23x;

			if (eyeWidth / eyeHeight > 3.3) {
				eye = "눈이 좁은 편으로 감성적이기보다는 치밀하고 계략적인 두뇌형이다. ";
			} else if (eyeWidth / eyeHeight < 2.8) {
				eye = "눈의 폭이 넓은 편으로 감성적이고 분위기를 잘 타는 형이다. ";
			} else {
				eye = "논리적이고 치밀한 두뇌와 감성적이고 직감적인 능력이 조화를 이룬 이상적인 눈을 가졌다. ";
			}

			if (eyeWidth / eyeHeight < 1.5) {
				eye += "둥근 눈을 가진 사람은 머리가 좋고 순발력이 있는 편이다. ";
				aEye = 1;
			} else {
				aEye = 0;
			}

			// 눈썹 기울기
			if (FaceImageView.eyebrowMiddle21y < FaceImageView.eyebrowInnerCorner14x) {
				eyebrowSlope = "올라간 눈썹은 기세가 당당하고 매사에 분명하고 확실한 성격이며 승부욕을 갖고 있어 항상 적극적으로 임하는 편이다. ";
				aEyebrowSlope = 1;
			} else {
				eyebrowSlope = "내려간 눈썹은 성격이 온순하여 다른 사람을 먼저 생각하고 도와주어 신뢰를 많이 얻는다. "
						+ "낙천적이며 느긋하고 호방한 성격에 주변 사람들과 화합을 잘한다. 끊고 맺는 것을 잘 못하기도. ";
				aEyebrowSlope = 2;
			}

			// 눈썹 길이
			if (FaceImageView.eyebrowOuterCorner15x > FaceImageView.eyeOuterCorner26x) {
				eyebrow = "눈썹이 길수록 총명하며 운과 복이 많다. 의리가 있고 정이 많으며 섬세하고 책임감이 강하다. ";
				aEyebrow = 0;
			} else {
				eyebrow = "눈썹이 짧아 고독하고 차가우며 혼자 있는 것을 좋아하는 편이다. "
						+ "일에 대한 추진력이 강하고, 경쟁심과 승부욕이 강하다. 몸을 움직이는 일이 잘 맞고 남에게 지시받는 직종은 안 맞는다. ";
				aEyebrow = 1;
			}

			// 콧볼 넓이
			if (FaceImageView.eyeInnerCorner25x - FaceImageView.eyeInnerCorner24x 
					< FaceImageView.noseRWingOuter46x - FaceImageView.noseLWingOuter45x){
				nose = "콧볼이 넓을수록 자비롭고 덕이 많다. 처세술이 뛰어나며 재물복도 많다. ";
				aNose = 1;
			} else {
				nose = "콧볼이 좁은편으로 돈을 벌어도 넣어놓을 지갑이 없는 형상으로 많이 벌더라도 재물을 모으는데 어려움이 있다. "
						+ "부자 배우자보단 학자인 배우자를 만날 가능성이 크다. ";
				aNose = 2;
			}

			// 입술 두께
			if (FaceImageView.mouthBottom55y - FaceImageView.mouthInnerBottom64y 
					< FaceImageView.mouthInnerTop61y - FaceImageView.mouthTop54y) {
				lip = "윗입술이 두꺼운 사람은 주관이 없는 편이나 다정다감한 성격으로 대인관계가 원만하고 근면하고 성실하여 재물복도 많다. ";
			} else {
				lip = "아랫입술이 두꺼운 사람은 성품이 냉정하고 자기 마음대로 행동하기 쉬우나 "
						+ "생각이나 태도가 믿음직하고 착실하며 매사에 운세가 좋다. 여성의 경우 아랫입술이 도톰하면 이성운이 좋다. ";
			}

			// 입꼬리
			if ((FaceImageView.mouthRCorner3y + FaceImageView.mouthLCorner4y) / 2 
					< FaceImageView.mouthInnerBottom64y) {
				lipCorner = "올라간 입꼬리로 말을 조리있게 잘하며 언변이 뛰어나고 긍정적이며 적극적인 성향으로 남들의 사랑을 많이 받는다. ";
				aLipCorner = 2;
			} else {
				lipCorner = "내려간 입꼬리는 매사에 침착하며 지극히 안정적이며 "
						+ "목표한 일을 이루고자 하는 높은 의지와 신념이 강해 어려운 일에도 흔들림이 없다. 다소 우울하며 고독한면이 있다. ";
				aLipCorner = 1;
			}

			content = eyebrowIntro + eyebrowSlope + eyebrow + "\n" + eyeIntro + eyeSlope + eye + iris
					+ "\n" + noseIntro + nose + "\n" + lipIntro + lip + lipCorner;

			// convert image to string
			timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			imageBase64 = bitmapToString(DetectActivity.imageBitmap);

			if (SettingActivity.check == true) {
				insert(timeStamp, content, imageBase64);
				Toast.makeText(getApplicationContext(), "관상결과가 자동저장되었습니다.", Toast.LENGTH_LONG).show();
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
				Toast.makeText(getApplicationContext(), "관상결과가 이미 저장되었습니다.", Toast.LENGTH_LONG).show();
			}
			else {
				insert(timeStamp, content, imageBase64);
				isSaved = true;
				Toast.makeText(getApplicationContext(), "관상결과가 저장되었습니다.", Toast.LENGTH_LONG).show();
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
