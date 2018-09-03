package com.yuer.king.weight;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import static android.view.animation.Animation.RESTART;

/**
 * Created by yuer on 2018/7/2.
 */

public class PathSmallFaceView  extends View{
    private int mWidth ,mHeight;
    private Paint paint;
    private Paint bgPaint;
    private Paint paintD;
    private Paint paintRed;

    private Path path ;
    private Path optPath ;
    private int paddingScreen = 20;
    private int eyeRadio = 100;
    private int widthPerFour;
    private int heightPerFour;
    private  int floatMove = eyeRadio;
    private  int floatMove2 = 80;
    public PathSmallFaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();

        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.STROKE);

        paintRed = new Paint();
        paintRed.setColor(Color.RED);
        paintRed.setStrokeWidth(8);
        paintRed.setStyle(Paint.Style.FILL);

        bgPaint = new Paint();
        bgPaint.setColor(Color.RED);
        bgPaint.setStrokeWidth(2);
        bgPaint.setStyle(Paint.Style.STROKE);

        paintD = new Paint();
        paintD.setColor(Color.GRAY);
        paintD.setStrokeWidth(2);
        paintD.setStyle(Paint.Style.FILL);

        path = new Path();
        optPath = new Path();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(-1,1);

        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValues = (float) animation.getAnimatedValue();
                floatMove = (int) (eyeRadio * currentValues);
                floatMove2 = (int) (eyeRadio * currentValues);
                invalidate();

            }
        });
        valueAnimator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
        path.reset();
        
        optPath.addRoundRect(new RectF(paddingScreen,paddingScreen,w-paddingScreen,h-paddingScreen),w/6,w/6, Path.Direction.CCW);

        widthPerFour = (mWidth   -paddingScreen *2)/4;
        heightPerFour =( mHeight  -paddingScreen * 2) /4;
        

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(paddingScreen,mHeight/2-paddingScreen,mWidth-paddingScreen,mHeight/2 - paddingScreen,bgPaint);
        canvas.drawLine(mWidth/2-paddingScreen,paddingScreen,mWidth/2-paddingScreen,mHeight-paddingScreen,bgPaint);
        canvas.save();

        drawFaceImg(canvas);

        canvas.drawPath(optPath,paint);

        canvas.restore();
    }

    private void drawFaceImg(Canvas canvas) {
        path.reset();
        int leftEye_X =   widthPerFour + paddingScreen ;
        int leftEye_Y =heightPerFour + paddingScreen + floatMove;

        path.addCircle(leftEye_X ,leftEye_Y,eyeRadio, Path.Direction.CW);
        RectF f  = new RectF(leftEye_X - eyeRadio ,leftEye_Y - eyeRadio+50,leftEye_X + eyeRadio,leftEye_Y + eyeRadio - 50);
        path.addOval(f ,Path.Direction.CW);
        path.addCircle(leftEye_X ,leftEye_Y ,20,Path.Direction.CW);


        int rightEye_X =  widthPerFour * 3 + paddingScreen ;
        int rightEye_Y = leftEye_Y;

        RectF r  = new RectF(rightEye_X - eyeRadio,rightEye_Y - eyeRadio+50,rightEye_X + eyeRadio,rightEye_Y + eyeRadio - 50);
        path.addCircle(rightEye_X,rightEye_Y,eyeRadio, Path.Direction.CW);
        path.addCircle(rightEye_X,rightEye_Y ,20,Path.Direction.CW);
        path.addOval(r,Path.Direction.CW);


        float x1 = (float) (widthPerFour  * 1.5)+ floatMove2;
        float y1 = heightPerFour * 2 + paddingScreen +30  + floatMove;
        float x2 =  (float) (widthPerFour  * 2.5)+floatMove2;
        float y2 = heightPerFour * 2 + paddingScreen +30 +  + floatMove;
        path.moveTo(x1,y1);

        //绘制嘴巴
        path.quadTo(widthPerFour * 2 , heightPerFour * 3 ,x2,y2);

        paintRed.setStrokeWidth(20);
        canvas.drawPoint(x1,y1,paintRed);
        canvas.drawPoint(x2,y2,paintRed);
        canvas.drawPoint(widthPerFour * 2 , heightPerFour * 3 ,paintRed);
        canvas.drawPath(path,paint);
    }
}
