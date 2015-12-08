package com.example.seethru;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class AdviceActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advice);

		String content = "";
		String intro = "관상은 타고난 것이긴 하지만 충분히 개인의 노력으로 개선이 가능합니다. \n";
		String person = "[개인 관상 보완법] \n";
		String money = "\n[재물운] \n눈꼬리가 길고 눈두덩은 넓으며 콧망울이 도톰하며 둥근 것이 좋습니다. "
				+ "눈두덩은 돈, 부동산 등을 상징하는데 이부분이 넓고 살집이 있으며 윤기가 돌면 지갑에 돈이 떨어질 일이 없다고 합니다. "
				+ "골드나 옐로우 같은 톤의 아이섀도우로 푹꺼진 눈두덩을 밝혀줍니다. ";
		String mNose = "코는 재물이 쌓인다는 중요한 부위입니다. 재물운을 높이려면 곧고 윤기있는 콧대와 두툼한 콧망울이 관건입니다. "
				+ "메이크업을 마무리할 때, 파우더 타입 하이라이터로 콧대를 쓸어주고 양쪽 콧망울에 가볍게 굴리듯 발라줍니다. ";
		String mEye = "";
		String success = "\n[출세운] \n둥글고 봉긋 솟은 이마와 살짝 치켜뜬 듯한 각진 눈썹, 양끝이 올라간 입꼬리는 출세운을 불러옵니다. "
				+ "관상에서 '관록궁'이라 불리는 이마는 사람의 뇌와 같은 의미로 모양이 둥글고 솟을수록 지성미가 높습니다. "
				+ "펄이 함유된 하이라이터를 이마 가운데 발라 도드라지게 연출하면 취업운이 상승할 것입니다. ";
		String sEyebrowSlope = "또렷하고 힘있는 눈썹은 총명하고 명석해보이게 만들어주며 명예운, 출세운을 불러옵니다. "
				+ "이런 눈썹을 만들기 위해서는 팬슬 타입의 아이브로우로 눈썹 선을 또렷하게 잡아준 뒤, "
				+ "아이브로우 마스카라로 눈썹의 결을 풍성하게 채워주는 것이 좋습니다. ";
		String sLip = "";
		String love = "\n[애정운] \n둥글고 부드러운 눈썹과 강아지처럼 살짝 처진듯한 눈꼬리 그리고 핑크빛 뺨과 도톰한 입술은 애정운을 높여줍니다. ";
		String lEyebrowSlope = "여성의 눈썹이 초승달 모양이면 남성들의 사랑을 독차지한다고 합니다. "
				+ "달달한 연애가 시급하다면 당장 눈썹을 부드러운 곡선으로 정리하고, 팬슬 타입보다는 섀도우로 음영을 넣듯 펼쳐서 그려줍니다. ";
		String lEyeSlope = "";
		String lLip = "핑크 컬러 블러셔를 활용해 두 뺨을 생기있게 연출하고, 입술에는 립스틱 대신 립글로즈를 활용하여 촉촉하고 도톰하게 만들어 줍니다. ";
		String eyeSlope = "";
		String iris = "";
		String eyebrowSlope = "";
		String eyebrow = "";
		String nose = "";
		String libCorner = "";

		//눈동자 위치 보완법
		if (ResultActivity.aIris == 1) {
			iris = "하백안인 사람은 선한 마음을 갖도록 항상 마음을 다스리고 집념을 좋은 쪽으로 쓴다면 큰 성공을 거둘 수 있습니다. "
					+ "또한 충분히 교정이 가능하다고 합니다. 하백안의 좋은 예는 대표적으로 나폴레옹이 있습니다. ";
		} else if(ResultActivity.aIris == 2) {
			iris = "상백안인 사람은 다른 사람들의 의견을 수용하려고 노력하며 선한 마음을 갖도록 항상 마음을 다스려야합니다. "
					+ "또한 충분히 교정이 가능하다고 합니다. ";
		} else if(ResultActivity.aIris == 3) {
			iris = "주위에 사백안인 사람이 있다면 피하고 조심해야 합니다. 이성을 잃고 행동하는 일이 없도록 항상 조심합니다. ";
		}

		//눈썹 기울기 보완법
		if(ResultActivity.aEyebrowSlope == 1){
			eyebrowSlope = "너무 올라간 눈썹을 가졌다면 눈썹 끝을 살짝 다듬어 부드러운 인상을 주는 것이 좋습니다. ";
		}
		else if(ResultActivity.aEyebrowSlope == 2){
			eyebrowSlope = "너무 내려간 눈썹은 다소 인상이 느려보일 수 있으므로 눈썹 끝을 살짝 다듬고 아이브로우로 눈썹 끝을 평행하도록 그려줍니다. ";
		}

		//눈썹 길이 보완법
		if(ResultActivity.aEyebrow == 1){
			eyebrow = "눈썹이 짧은 사람은 아이브로우를 사용하여 눈보다 길게 그려주는 것이 좋습니다. 인덕을 불러오며 수명이 길고 가족운도 좋아집니다. ";
		}

		//눈 기울기 보완법
		if(ResultActivity.aEyeSlope == 1){
			eyeSlope = "내려간 눈은 부드러운 인상을 주지만 아이라이너로 윤곽을 또렷하게 그리되 끝을 살짝 올려서 그린다면 신뢰와 호감을 주는 인상이 됩니다. ";
			lEyeSlope = "내려간 눈을 가진 사람은 애정운이 높은 편입니다. "
					+ "아이라인을 그릴 때 눈 앞머리부터 끝까지 꼼꼼하게 채워주며 내려간 눈꼬리를 선명하게 보여줍니다. ";
		}
		else if(ResultActivity.aEyeSlope == 2){
			lEyeSlope = "눈꼬리는 애정운의 정도를 알 수 있는 중요한 체크포인트. "
					+ "눈꼬리가 내려갈수록 애정운이 높다고 하니 아이라인을 그릴 때는 눈 앞머리부터 끝까지 꼼꼼하게 채워주되 끝부분은 살짝 내려서 그려줍니다. ";
		}

		//눈 길이 보완법
		if(ResultActivity.aEye == 1){
			mEye = "눈꼬리가 길면 재물운을 불러옵니다. 따라서 아이라이너를 이용해 눈 앞머리부터 꼬리부분까지 선명하고 길게 그리면 좋습니다. ";
		}

		//콧볼 보완법
		if(ResultActivity.aNose == 1){
			nose = "코의 가지런한 모양을 만들기 위해 피부 톤보다 반톤 정도 어두운 쉐딩을 이용해 양쪽 콧망울을 둥글게 블렌딩합니다. ";
		}
		else if(ResultActivity.aNose == 2){
			nose = "코가 좁은 편이어도 콧대가 있다면 좋은 기운을 불러 올 수 있습니다. 펄이 들어가 있는 하이라이터로 콧대를 세워줍니다. "
					+ "너무 과하다면 센 인상을 줄 수 있으니 유의해주세요. ";
		}

		//입꼬리 보완법
		if(ResultActivity.aLipCorner == 1){
			libCorner = "입꼬리가 내려간 사람은 우울해 보이는 인상을 줄 수 있으므로 입꼬리를 올리는 연습을 하는 것이 좋습니다. "
					+ "올라간 입꼬리는 밝고 호감있는 인상을 줍니다. ";
			sLip = "입꼬리가 살짝 올라가 있고, 위 아래 입술의 두께가 같으면 의욕적이고 활력이 넘치는 길상입니다. "
					+ "취업운을 높이고 싶다면 립라이너로 입술 두께를 같은 비율로 만들고 입꼬리를 살짝 올려 그려줍니다. ";
		}
		else if(ResultActivity.aLipCorner == 2){
			sLip = "입꼬리가 살짝 올라가 있고, 위 아래 입술의 두께가 같으면 의욕적이고 활력이 넘치는 길상입니다. "
					+ "취업운을 높이고 싶다면 립라이너로 입술 두께를 같은 비율로 만들어 주는 것이 좋습니다. ";
		}

		content = intro + person + iris + eyebrowSlope + eyebrow + eyeSlope + nose + libCorner 
				+ "\n" + money + mEye + mNose + "\n" + success + sEyebrowSlope + sLip 
				+ "\n" + love + lEyebrowSlope + lEyeSlope + lLip;

		TextView advice = (TextView)findViewById(R.id.textview_advice);
		advice.setText(content);

		ImageButton homeBtn = (ImageButton)findViewById(R.id.imgbtn_advice_home);
		homeBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AdviceActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
	}
}