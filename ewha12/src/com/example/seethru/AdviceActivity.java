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
		String intro = "������ Ÿ�� ���̱� ������ ����� ������ ������� ������ �����մϴ�. \n";
		String person = "[���� ���� ���Ϲ�] \n";
		String money = "\n[�繰��] \n�������� ��� ���ε��� ������ ������� �����ϸ� �ձ� ���� �����ϴ�. "
				+ "���ε��� ��, �ε��� ���� ��¡�ϴµ� �̺κ��� �а� ������ ������ ���Ⱑ ���� ������ ���� ������ ���� ���ٰ� �մϴ�. "
				+ "��峪 ���ο� ���� ���� ���̼������ ǫ���� ���ε��� �����ݴϴ�. ";
		String mNose = "�ڴ� �繰�� ���δٴ� �߿��� �����Դϴ�. �繰���� ���̷��� ��� �����ִ� ���� ������ ������� �����Դϴ�. "
				+ "����ũ���� �������� ��, �Ŀ�� Ÿ�� ���̶����ͷ� ��븦 �����ְ� ���� ����￡ ������ ������ �߶��ݴϴ�. ";
		String mEye = "";
		String success = "\n[�⼼��] \n�ձ۰� ���� ���� �̸��� ��¦ ġ�Ѷ� ���� ���� ����, �糡�� �ö� �Բ����� �⼼���� �ҷ��ɴϴ�. "
				+ "���󿡼� '���ϱ�'�̶� �Ҹ��� �̸��� ����� ���� ���� �ǹ̷� ����� �ձ۰� �������� �����̰� �����ϴ�. "
				+ "���� ������ ���̶����͸� �̸� ��� �߶� ��������� �����ϸ� ������� ����� ���Դϴ�. ";
		String sEyebrowSlope = "�Ƿ��ϰ� ���ִ� ������ �Ѹ��ϰ� ���غ��̰� ������ָ� ����, �⼼���� �ҷ��ɴϴ�. "
				+ "�̷� ������ ����� ���ؼ��� �ҽ� Ÿ���� ���̺�ο�� ���� ���� �Ƿ��ϰ� ����� ��, "
				+ "���̺�ο� ����ī��� ������ ���� ǳ���ϰ� ä���ִ� ���� �����ϴ�. ";
		String sLip = "";
		String love = "\n[������] \n�ձ۰� �ε巯�� ����� ������ó�� ��¦ ó������ ������ �׸��� ��ũ�� ���� ������ �Լ��� �������� �����ݴϴ�. ";
		String lEyebrowSlope = "������ ������ �ʽ´� ����̸� �������� ����� �������Ѵٰ� �մϴ�. "
				+ "�޴��� ���ְ� �ñ��ϴٸ� ���� ������ �ε巯�� ����� �����ϰ�, �ҽ� Ÿ�Ժ��ٴ� ������� ������ �ֵ� ���ļ� �׷��ݴϴ�. ";
		String lEyeSlope = "";
		String lLip = "��ũ �÷� ���Ÿ� Ȱ���� �� ���� �����ְ� �����ϰ�, �Լ����� ����ƽ ��� ���۷�� Ȱ���Ͽ� �����ϰ� �����ϰ� ����� �ݴϴ�. ";
		String eyeSlope = "";
		String iris = "";
		String eyebrowSlope = "";
		String eyebrow = "";
		String nose = "";
		String libCorner = "";

		//������ ��ġ ���Ϲ�
		if (ResultActivity.aIris == 1) {
			iris = "�Ϲ���� ����� ���� ������ ������ �׻� ������ �ٽ����� ������ ���� ������ ���ٸ� ū ������ �ŵ� �� �ֽ��ϴ�. "
					+ "���� ����� ������ �����ϴٰ� �մϴ�. �Ϲ���� ���� ���� ��ǥ������ ���������� �ֽ��ϴ�. ";
		} else if(ResultActivity.aIris == 2) {
			iris = "������ ����� �ٸ� ������� �ǰ��� �����Ϸ��� ����ϸ� ���� ������ ������ �׻� ������ �ٽ������մϴ�. "
					+ "���� ����� ������ �����ϴٰ� �մϴ�. ";
		} else if(ResultActivity.aIris == 3) {
			iris = "������ ������ ����� �ִٸ� ���ϰ� �����ؾ� �մϴ�. �̼��� �Ұ� �ൿ�ϴ� ���� ������ �׻� �����մϴ�. ";
		}

		//���� ���� ���Ϲ�
		if(ResultActivity.aEyebrowSlope == 1){
			eyebrowSlope = "�ʹ� �ö� ������ �����ٸ� ���� ���� ��¦ �ٵ�� �ε巯�� �λ��� �ִ� ���� �����ϴ�. ";
		}
		else if(ResultActivity.aEyebrowSlope == 2){
			eyebrowSlope = "�ʹ� ������ ������ �ټ� �λ��� �������� �� �����Ƿ� ���� ���� ��¦ �ٵ�� ���̺�ο�� ���� ���� �����ϵ��� �׷��ݴϴ�. ";
		}

		//���� ���� ���Ϲ�
		if(ResultActivity.aEyebrow == 1){
			eyebrow = "������ ª�� ����� ���̺�ο츦 ����Ͽ� ������ ��� �׷��ִ� ���� �����ϴ�. �δ��� �ҷ����� ������ ��� ����� �������ϴ�. ";
		}

		//�� ���� ���Ϲ�
		if(ResultActivity.aEyeSlope == 1){
			eyeSlope = "������ ���� �ε巯�� �λ��� ������ ���̶��̳ʷ� ������ �Ƿ��ϰ� �׸��� ���� ��¦ �÷��� �׸��ٸ� �ŷڿ� ȣ���� �ִ� �λ��� �˴ϴ�. ";
			lEyeSlope = "������ ���� ���� ����� �������� ���� ���Դϴ�. "
					+ "���̶����� �׸� �� �� �ոӸ����� ������ �Ĳ��ϰ� ä���ָ� ������ �������� �����ϰ� �����ݴϴ�. ";
		}
		else if(ResultActivity.aEyeSlope == 2){
			lEyeSlope = "�������� �������� ������ �� �� �ִ� �߿��� üũ����Ʈ. "
					+ "�������� ���������� �������� ���ٰ� �ϴ� ���̶����� �׸� ���� �� �ոӸ����� ������ �Ĳ��ϰ� ä���ֵ� ���κ��� ��¦ ������ �׷��ݴϴ�. ";
		}

		//�� ���� ���Ϲ�
		if(ResultActivity.aEye == 1){
			mEye = "�������� ��� �繰���� �ҷ��ɴϴ�. ���� ���̶��̳ʸ� �̿��� �� �ոӸ����� �����κб��� �����ϰ� ��� �׸��� �����ϴ�. ";
		}

		//�ຼ ���Ϲ�
		if(ResultActivity.aNose == 1){
			nose = "���� �������� ����� ����� ���� �Ǻ� �溸�� ���� ���� ��ο� ������ �̿��� ���� ������� �ձ۰� �����մϴ�. ";
		}
		else if(ResultActivity.aNose == 2){
			nose = "�ڰ� ���� ���̾ ��밡 �ִٸ� ���� ����� �ҷ� �� �� �ֽ��ϴ�. ���� �� �ִ� ���̶����ͷ� ��븦 �����ݴϴ�. "
					+ "�ʹ� ���ϴٸ� �� �λ��� �� �� ������ �������ּ���. ";
		}

		//�Բ��� ���Ϲ�
		if(ResultActivity.aLipCorner == 1){
			libCorner = "�Բ����� ������ ����� ����� ���̴� �λ��� �� �� �����Ƿ� �Բ����� �ø��� ������ �ϴ� ���� �����ϴ�. "
					+ "�ö� �Բ����� ��� ȣ���ִ� �λ��� �ݴϴ�. ";
			sLip = "�Բ����� ��¦ �ö� �ְ�, �� �Ʒ� �Լ��� �β��� ������ �ǿ����̰� Ȱ���� ��ġ�� ����Դϴ�. "
					+ "������� ���̰� �ʹٸ� �����̳ʷ� �Լ� �β��� ���� ������ ����� �Բ����� ��¦ �÷� �׷��ݴϴ�. ";
		}
		else if(ResultActivity.aLipCorner == 2){
			sLip = "�Բ����� ��¦ �ö� �ְ�, �� �Ʒ� �Լ��� �β��� ������ �ǿ����̰� Ȱ���� ��ġ�� ����Դϴ�. "
					+ "������� ���̰� �ʹٸ� �����̳ʷ� �Լ� �β��� ���� ������ ����� �ִ� ���� �����ϴ�. ";
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