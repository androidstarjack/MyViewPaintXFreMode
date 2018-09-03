package com.yuer.king.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by hongfei.wang on 2018/6/15.
 *
 * @author hongfei.wang
 */

public class ChartProgressView extends View {

    private Paint mTextPaint;
    private Paint mRectPaint;

    private float mMinContentWidth;
    private float mMaxContentWidth;
    private float mContentWidth;
    private int mWidth;
    private int mHeight;
    private float mTextY;

    private float mPercent;

    private float mCurrentData;
    private String mDate;

    private int mSelectColor = Color.parseColor("#47B0FF");
    private int mNormalColor = Color.parseColor("#BFBFBF");

    public ChartProgressView(Context context) {
        super(context);
    }

    public ChartProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChartProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setData(String date, float currentData, float maxData) {
        mDate = date;
        mCurrentData = currentData;
        if (maxData == 0) {
            mPercent = 0;
        }
        else {
            mPercent = currentData / maxData;
            if (mPercent > 1) {
                mPercent = 1;
            }
        }
        invalidate();
    }

    private void init() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(dpToPx(12));
        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mRectPaint.setStyle(Paint.Style.FILL);
        mRectPaint.setColor(Color.parseColor("#47B0FF"));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = (int) (dpToPx(10) * 2 + mTextPaint.getFontSpacing());
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        //为基线到字体上边框的距离,即上图中的top
        float top = fontMetrics.top;
        //为基线到字体下边框的距离,即上图中的bottom
        float bottom = fontMetrics.bottom;
        mTextY = mHeight / 2f - top / 2 - bottom / 2;
        mMinContentWidth = mTextPaint.measureText("0000-00-00 0.0") + dpToPx(32);
        mMaxContentWidth = mWidth - mMinContentWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDate == null) {
            return;
        }
        if (isSelected()) {
            mRectPaint.setColor(mSelectColor);
        }
        else {
            mRectPaint.setColor(mNormalColor);
        }
        mContentWidth = mMinContentWidth + mMaxContentWidth * mPercent;
        canvas.drawRect(new RectF(0, 0, mContentWidth, mHeight), mRectPaint);
        canvas.drawText(mDate, dpToPx(10), mTextY, mTextPaint);
        String data = String.valueOf(mCurrentData);
        canvas.drawText(data, mContentWidth - mTextPaint.measureText(data) - dpToPx(10), mTextY, mTextPaint);
    }

    private float dpToPx(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
