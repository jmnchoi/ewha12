package com.example.seethru;

import com.luxand.FSDK;
import com.luxand.FSDK.*;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class FaceImageView extends ImageView {
	private Paint painter;
	public TFacePosition detectedFace;
	public FSDK_Features facial_features;

	int faceImageWidthOrig;

	static int eye0x;
	static int eye0y;

	static int mouthRCorner3y;
	static int mouthLCorner4y;

	static int eyebrowInnerCorner13x;
	static int eyebrowInnerCorner13y;
	static int eyebrowInnerCorner14x;
	static int eyebrowInnerCorner14y;
	static int eyebrowOuterCorner15x;

	static int eyebrowMiddle18y;
	static int eyebrowMiddle21y;

	static int eyeOuterCorner23x;
	static int eyeInnerCorner24x;
	static int eyeInnerCorner25x;
	static int eyeInnerCorner25y;
	static int eyeOuterCorner26x;
	static int eyeOuterCorner26y;

	static int eyeLowerLine27y;
	static int eyeUpperLine28y;
	static int eyeRIrisCorner30x;

	static int noseLWingOuter45x;
	static int noseRWingOuter46x;

	static int mouthTop54y;
	static int mouthBottom55y;
	static int mouthInnerTop61y;
	static int mouthInnerBottom64y;

	public void Init() {
		faceImageWidthOrig = 0;
		facial_features = null;
		detectedFace = new TFacePosition();
		detectedFace.w = 0;

		painter = new Paint();
		painter.setColor(Color.BLUE);
		painter.setStrokeWidth(5);
		painter.setStyle(Paint.Style.STROKE);
	}

	public FaceImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		Init();
	}
	public FaceImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Init();
	}
	public FaceImageView(Context context) {
		super(context);
		Init();
	}

	//display detected faces, features or eyes
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (faceImageWidthOrig > 0 && facial_features != null) {
			int displayedWidth = this.getWidth();
			double ratio = displayedWidth / (faceImageWidthOrig*1.0);

			for (int i=0; i<FSDK.FSDK_FACIAL_FEATURE_COUNT; ++i) { //for all facial features
				//scale detected facial features
				int cx = (int)(facial_features.features[i].x * ratio);
				int cy = (int)(facial_features.features[i].y * ratio);
				canvas.drawCircle(cx, cy, 5, painter);

				switch (i) {
				case FSDK.FSDKP_LEFT_EYE:
					eye0x = (int)facial_features.features[i].x;
					eye0y = (int)facial_features.features[i].y;
					break;
				case FSDK.FSDKP_MOUTH_RIGHT_CORNER:
					mouthRCorner3y = (int)facial_features.features[i].y;
					break;
				case FSDK.FSDKP_MOUTH_LEFT_CORNER:
					mouthLCorner4y = (int)facial_features.features[i].y;
					break;
				case FSDK.FSDKP_LEFT_EYEBROW_INNER_CORNER:
					eyebrowInnerCorner13x = (int)facial_features.features[i].x;
					eyebrowInnerCorner13y = (int)facial_features.features[i].y;
					break;
				case FSDK.FSDKP_RIGHT_EYEBROW_INNER_CORNER:
					eyebrowInnerCorner14x = (int)facial_features.features[i].x;
					eyebrowInnerCorner14y = (int)facial_features.features[i].y;
					break;
				case FSDK.FSDKP_RIGHT_EYEBROW_OUTER_CORNER:
					eyebrowOuterCorner15x = (int)facial_features.features[i].x;
					break;
				case FSDK.FSDKP_LEFT_EYEBROW_MIDDLE_LEFT:
					eyebrowMiddle18y = (int)facial_features.features[i].y;
					break;
				case FSDK.FSDKP_RIGHT_EYEBROW_MIDDLE_RIGHT:
					eyebrowMiddle21y = (int)facial_features.features[i].y;
					break;
				case FSDK.FSDKP_LEFT_EYE_OUTER_CORNER:
					eyeOuterCorner23x = (int)facial_features.features[i].x;				
					break;
				case FSDK.FSDKP_LEFT_EYE_INNER_CORNER:
					eyeInnerCorner24x = (int)facial_features.features[i].x;
					break;
				case FSDK.FSDKP_RIGHT_EYE_INNER_CORNER:
					eyeInnerCorner25x = (int)facial_features.features[i].x;
					eyeInnerCorner25y = (int)facial_features.features[i].y;
					break;
				case FSDK.FSDKP_RIGHT_EYE_OUTER_CORNER:
					eyeOuterCorner26x = (int)facial_features.features[i].x;
					eyeOuterCorner26y = (int)facial_features.features[i].y;
					break;
				case FSDK.FSDKP_LEFT_EYE_LOWER_LINE2:
					eyeLowerLine27y = (int)facial_features.features[i].y;
					break;
				case FSDK.FSDKP_LEFT_EYE_UPPER_LINE2:
					eyeUpperLine28y = (int)facial_features.features[i].y;
					break;
				case FSDK.FSDKP_LEFT_EYE_RIGHT_IRIS_CORNER:
					eyeRIrisCorner30x = (int)facial_features.features[i].x;
					break;
				case FSDK.FSDKP_NOSE_LEFT_WING_OUTER:
					noseLWingOuter45x = (int)facial_features.features[i].x;
					break;
				case FSDK.FSDKP_NOSE_RIGHT_WING_OUTER:
					noseRWingOuter46x = (int)facial_features.features[i].x;
					break;
				case FSDK.FSDKP_MOUTH_TOP:
					mouthTop54y = (int)facial_features.features[i].y;
					break;
				case FSDK.FSDKP_MOUTH_BOTTOM:
					mouthBottom55y = (int)facial_features.features[i].y;
					break;
				case FSDK.FSDKP_MOUTH_TOP_INNER:
					mouthInnerTop61y = (int)facial_features.features[i].y;
					break;
				case FSDK.FSDKP_MOUTH_BOTTOM_INNER:
					mouthInnerBottom64y = (int)facial_features.features[i].y;
					break;
				}
			}
		}

	}
	
	//remove white borders of image to mark face correctly
	@Override 
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		Drawable drawable = getDrawable();
		if (drawable != null) {
			int width = MeasureSpec.getSize(widthMeasureSpec);
			int height = (int) Math.ceil((float) width * (float) drawable.getIntrinsicHeight() / (float) drawable.getIntrinsicWidth());
			setMeasuredDimension(width, height);
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}
	
}
