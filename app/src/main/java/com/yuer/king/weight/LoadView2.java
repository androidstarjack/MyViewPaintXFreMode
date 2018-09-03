package com.yuer.king.weight;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.yuer.king.R;

public class LoadView2 extends View {

    private float currentValue = 0;     // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度

    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的tangent值,用于计算图片所需旋转的角度
    private Bitmap mBitmap;             // 箭头图片
    private Matrix mMatrix;             // 矩阵,用于对图片进行一些操作
    private Paint mDeafultPaint;
    private int mViewWidth;
    private int mViewHeight;
    private Paint mPaint;

    private Path mPath;

    private float mAnimValue;


//    public LoadView2(Context context) {
//        this(context,null);
//    }

    public LoadView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

//    public LoadView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init(this.getContext());
//    }

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public LoadView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        init(this.getContext());
//    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }


    private void init(Context context) {
        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;       // 缩放图片
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.circle_filled, options);
        mMatrix = new Matrix();

        mDeafultPaint = new Paint();
        mDeafultPaint.setColor(Color.RED);
        mDeafultPaint.setStrokeWidth(5);
        mDeafultPaint.setStyle(Paint.Style.STROKE);

        mPaint = new Paint();
        mPaint.setColor(Color.DKGRAY);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);


        mPath = new Path();
        mPath.addCircle(0,0,100,Path.Direction.CW);


        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimValue = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });


        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();



    }

    Path dst = new Path();
    @Override
    protected void onDraw(Canvas canvas) {



        canvas.drawColor(Color.WHITE);

        canvas.translate(mViewWidth / 2,mViewHeight / 2);


        dst.reset();
        dst.lineTo(0,0);
        PathMeasure measure = new PathMeasure(mPath,false);

        float end = measure.getLength() * mAnimValue;
        float start = (float) (end - ((0.5 - Math.abs(mAnimValue - 0.5)) * measure.getLength()));

        measure.getSegment(start,end,dst,true);


        canvas.drawPath(dst,mDeafultPaint);

    }
}
