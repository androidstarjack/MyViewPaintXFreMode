package com.yuer.king.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yuer.king.utils.LogUtils;

/**
 * Created by yuer on 2018/6/21.
 */

public class ColorPaintView extends View {
    private static final Xfermode[] sModes = {
            new PorterDuffXfermode(PorterDuff.Mode.CLEAR),
            new PorterDuffXfermode(PorterDuff.Mode.SRC),
            new PorterDuffXfermode(PorterDuff.Mode.DST),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER),
            new PorterDuffXfermode(PorterDuff.Mode.DST_OVER),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_IN),
            new PorterDuffXfermode(PorterDuff.Mode.DST_IN),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT),
            new PorterDuffXfermode(PorterDuff.Mode.DST_OUT),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP),
            new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP),
            new PorterDuffXfermode(PorterDuff.Mode.XOR),
            new PorterDuffXfermode(PorterDuff.Mode.DARKEN),
            new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN),
            new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY),
            new PorterDuffXfermode(PorterDuff.Mode.SCREEN)
    };

    private static final String[] sLabels = {
            "Clear", "Src", "Dst", "SrcOver",
            "DstOver", "SrcIn", "DstIn", "SrcOut",
            "DstOut", "SrcATop", "DstATop", "Xor",
            "Darken", "Lighten", "Multiply", "Screen"
    };

    private int mScreenWidth;
    private int mScreenHeight;

    private int mCurrentWidth;
    private int mCurrentHeight;

    private Paint paint;
    private Paint circlrpaint;

    private int mCenterX;
    private int mCenterY;

    private int previousX;
    private int previousY;
  private int PREVIOUS_X;
    private int PREVIOUS_Y;

    private int mCirclrLangth ;
    private int perUunt = 0;
    public ColorPaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public ColorPaintView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if(Build.VERSION.SDK_INT >= 11){
            setLayerType(LAYER_TYPE_SOFTWARE, null);
        }
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(14);

        circlrpaint = new Paint();
        circlrpaint.setColor(Color.BLACK);
        circlrpaint.setAntiAlias(true);
        circlrpaint.setStyle(Paint.Style.STROKE);
        circlrpaint.setStrokeWidth(14);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mCurrentWidth =   MeasureSpec.getSize(widthMeasureSpec);
        int mCurrentHeight =   MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mCurrentWidth,mCurrentHeight);
        LogUtils.e("tag","mCurrentWidth:  " + mCurrentWidth + "    mCurrentHeight:  " + mCurrentHeight);
        mCurrentWidth = getMeasuredWidth();
        mCurrentHeight = getMeasuredHeight();
        LogUtils.e("tag","mCurrentWidth:  " + mCurrentWidth + "    mCurrentHeight:  " + mCurrentHeight);
        perUunt =  mCurrentWidth / 4;
        mCirclrLangth =  perUunt / 3 -20;//半径
        mCenterX = mCirclrLangth;
        mCenterY = mCirclrLangth;
    }

    //先画目标图，在绘制原图，在绘制原图之前，给画笔paint进行设置xfermode，然后用原图进行交会，这两张图就会按照模式规则进行处理
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //背景色
        canvas.drawARGB(255, 255, 156, 161);

        for (int column = 0; column < 4; column++) {
            for (int row = 0; row < 4; row++) {
                int sc = canvas.saveLayer(0, 0, canvas.getWidth(), canvas.getHeight(), null, Canvas.ALL_SAVE_FLAG);
                paint.setXfermode(null);
                paint.setColor(Color.YELLOW);
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.FILL);
                paint.setStrokeWidth(14);

                previousX = perUunt * row +mCenterX;
                previousY = perUunt *column + mCenterY;

                canvas.drawCircle(previousX,previousY,mCirclrLangth,paint);
                canvas.drawCircle(previousX,previousY,10,circlrpaint);

                paint.reset();
                paint.setColor(Color.LTGRAY);

                //绘制边框
                /*RectF  rectF2 = new RectF(previousX - perUunt * row - previousX ,previousY - perUunt *column - previousY,previousX+ row * previousX,previousY + column*previousY);
                canvas.drawRect(rectF2,paint);*/


                LogUtils.e("tag","previousX:  " + previousX + "    previousY:  " + previousY);

                paint.reset();
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.BLUE);
                paint.setAntiAlias(true);
                Xfermode xfermode  = sModes[i];
                paint.setXfermode(xfermode);
                RectF  rectF = new RectF(previousX,previousY,previousX+2*mCirclrLangth,previousY + 2*mCirclrLangth);
                canvas.drawRect(rectF,paint);

                canvas.translate(mCenterX,mCenterY);
                //最后
                //还原
                paint.setXfermode(null);
                canvas.restoreToCount(sc);
                i = i + 1 ;

            }
        }
    }

    private int i = 0;
}
