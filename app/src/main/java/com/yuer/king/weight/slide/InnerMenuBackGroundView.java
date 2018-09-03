package com.yuer.king.weight.slide;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;

import com.yuer.king.R;
import com.yuer.king.utils.DensityUtil;

/**
 * 类功能描述：</br>
 * 底部背景用贝塞尔曲线实现
 * @author 于亚豪
 * @version 1.0 </p> 修改时间：</br> 修改备注：</br>
 */
public class InnerMenuBackGroundView extends View {
    private Paint paint;
    private float startX;
    private float startY;
    private float endX;
    private float  endY;
    private float controlX;
    private float controlY;
    private Path path;
    private int screenHeight;
    private int screenWidth;

    private float currentToucthX;
    private float currentToucthY;
    private boolean isShowDrawCurrentPoint ;
    private Rect rect;
    @SuppressLint("ObjectAnimatorBinding")
    private     ObjectAnimator anim;
    public InnerMenuBackGroundView(Context context) {
        this(context,null,0);
    }


    public InnerMenuBackGroundView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public InnerMenuBackGroundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        rect = new Rect();
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
        paint.setAntiAlias(true);

        path = new Path();
        screenHeight = DensityUtil.getScreenIntHeight(context) - DensityUtil.getStatusBarHeight(getContext());
        screenWidth = DensityUtil.getScreenIntWidth(context);
        startX = 0;
        startY = 0;
        endX = 0;
        endY = screenHeight;
        controlX = screenWidth/2;
        controlY = screenHeight/2;
        AlpahAnimation();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        setControlXAndY(event.getRawX(),event.getRawY());
//        return super.onTouchEvent(event);
//    }

    public void setCurrentXAndY(MotionEvent motionEvent){
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                isShowDrawCurrentPoint = true;
                break;
            case MotionEvent.ACTION_MOVE:
                currentToucthX = motionEvent.getRawX();
                currentToucthY = motionEvent.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                isShowDrawCurrentPoint = false;
                break;
        }


    }
    public void setControlXAndY(float x,float y){
        this.controlX = x * 3 /2 +100;
        this.controlY = y;

        path.reset();
        path.moveTo(0,0);
        path.lineTo(startX,startY);
        path.quadTo(controlX,controlY,endX,endY);
        path.close();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));


        canvas.drawPath(path,paint);
        if(isShowDrawCurrentPoint){
            canvas.drawRect(currentToucthX-20,currentToucthY-20,currentToucthX+20,currentToucthY+20,paint);
            if(!anim.isRunning()){
                anim.start();
            }
        }else{
            if(!anim.isRunning()){
                anim.cancel();
            }
        }

    }

    private void ss(){
//        Animation animation = A
    }
    @SuppressLint("ObjectAnimatorBinding")
    private void AlpahAnimation() {
        anim = ObjectAnimator.ofFloat(rect, "alpha", 1.0f, 0.8f, 0.6f, 0.4f, 0.2f, 0.0f);
        anim.setRepeatCount(-1);
        anim.setRepeatMode(ObjectAnimator.REVERSE);
        anim.setDuration(2000);

    }

    public void setColor(int colorId){
        paint.setColor(ContextCompat.getColor(getContext(),colorId));
    }
}
