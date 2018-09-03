package com.yuer.king.weight;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yuer.king.utils.LogUtils;

import static android.animation.ValueAnimator.INFINITE;

/**
 * 注意事项：
 * https://blog.csdn.net/dzb19891120/article/details/66975434
 *在android KITKAT版本及之前的版本，该方法所获取到的path是无法被绘制显示出来的。
 * 要解决该问题的简单方法就是在所要获取的path上执行lineTo(0, 0) 或执行rLineTo(0, 0);
 */

public class PathMeasureCircleLoadingView extends View {
    private int mWidth,mHeight;
    private Paint paint;
    private int mRadio = 0;
    private Path mPath ;
    private float currentValue;
    public PathMeasureCircleLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public PathMeasureCircleLoadingView(Context context) {
        this(context, null);
        init(context);
    }
    @SuppressLint("WrongConstant")
    private void init(Context context) {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(20);
        mPath = new Path();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRadio =  mWidth / 2 -50;
        mPath.addCircle(0,0,mRadio, Path.Direction.CW);
    }
    Path path = new Path();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        path.lineTo(0,0);
//        mPath.lineTo(0,0);
        canvas.drawColor(Color.WHITE);
        canvas.translate(mWidth/2,mHeight/2);
        canvas.drawCircle(0,0,10,paint);
        canvas.save();

        PathMeasure pathMeasure = new PathMeasure(mPath,false);
        path.reset();
        path.lineTo(0,0);
        LogUtils.e("pathMeasure","pathMeasure:  " + pathMeasure.getLength());


        float  stopD =   (pathMeasure.getLength() * currentValue);
        float startD =(float) (stopD - (0.5 - Math.abs(0.5 - currentValue)) * pathMeasure.getLength());
//
//        float  stopD = (float) (pathMeasure.getLength() * 0.5);
//        float startD = (float) (pathMeasure.getLength() * 0.1);

        pathMeasure.getSegment(startD,stopD,path,false);

//        canvas.drawLine(,paint);
        canvas.drawPath(path,paint);

    }
}
