package com.yuer.king.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.yuer.king.R;
import com.yuer.king.utils.DensityUtil;

import java.text.DecimalFormat;

/**
 * 类功能描述：</br>
 *
 * @author 于亚豪
 * @version 1.0 </p> 修改时间：2018/8/17</br> 修改备注：</br>
 */
public class ProgressCostomerView2 extends ProgressBar {

    private Context mContext;
    private Paint mPaint;
    private PorterDuffXfermode mPorterDuffXfermode;
    private float mProgress;
    private int mState;

    // IconTextProgressBar的状态
    private static final int STATE_DEFAULT = 101;
    private static final int STATE_DOWNLOADING = 102;
    private static final int STATE_PAUSE = 103;
    private static final int STATE_DOWNLOAD_FINISH = 104;
    // IconTextProgressBar的文字大小(sp)
    private static final float TEXT_SIZE_SP = 17f;
    // IconTextProgressBar的图标与文字间距(dp)
    private static final float ICON_TEXT_SPACING_DP = 5f;

    private int screenProgressWidth = 0;
    private int rightShowProgressTextWidth = 0;
    private int rightTextPadding = 0;

    public ProgressCostomerView2(Context context) {
        this(context,null);
    }

    public ProgressCostomerView2(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ProgressCostomerView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.MyComprogressBarText);
         rightShowProgressTextWidth = (int) ta.getDimension(R.styleable.MyComprogressBarText_rightShowProgressTextWidth,100);
         rightTextPadding = (int) ta.getDimension(R.styleable.MyComprogressBarText_rightTextPadding,10);


        init();
    }

//    public ProgressCostomerView2(Context context) {
//        super(context, null, android.R.attr.progressBarStyleHorizontal);
//        mContext = context;
//        init();
//    }
//    public ProgressCostomerView2(Context context) {
//        super(context, null, android.R.attr.progressBarStyleHorizontal);
//        mContext = context;
//        init();
//    }

//    public ProgressCostomerView2(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        mContext = context;
//        init();
//    }



    /**
     * 设置下载状态
     */
    public synchronized void setState(int state) {
        mState = state;
        invalidate();
    }

    /**
     * 设置下载进度
     */
    public synchronized void setProgress(float progress) {
        super.setProgress((int) progress);
        mProgress = progress;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mState) {
            case STATE_DEFAULT:
                drawIconAndText(canvas, STATE_DEFAULT, false);
                break;

            case STATE_DOWNLOADING:
                drawIconAndText(canvas, STATE_DOWNLOADING, false);
                break;

            case STATE_PAUSE:
                drawIconAndText(canvas, STATE_PAUSE, false);
                break;

            case STATE_DOWNLOAD_FINISH:
                drawIconAndText(canvas, STATE_DOWNLOAD_FINISH, true);
                break;

            default:
                drawIconAndText(canvas, STATE_DEFAULT, false);
                break;
        }
    }

    private void init() {
        setIndeterminate(false);
        setIndeterminateDrawable(ContextCompat.getDrawable(mContext,
                android.R.drawable.progress_indeterminate_horizontal));
        setProgressDrawable(ContextCompat.getDrawable(mContext,
                R.drawable.pb_shape_blue));
        setMax(100);

        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setTextSize(DensityUtil.sp2px(mContext, TEXT_SIZE_SP));
        mPaint.setTypeface(Typeface.MONOSPACE);

        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    private void initForState(int state) {
        switch (state) {
            case STATE_DEFAULT:
                setProgress(0);
                mPaint.setColor(Color.WHITE);
                break;

            case STATE_DOWNLOADING:
                mPaint.setColor(ContextCompat.getColor(mContext, R.color.pb_blue));
                break;
            case STATE_PAUSE:
                mPaint.setColor(ContextCompat.getColor(mContext, R.color.pb_blue));
                break;

            case STATE_DOWNLOAD_FINISH:
                setProgress(100);
                mPaint.setColor(Color.WHITE);
                break;

            default:
                setProgress(0);
                mPaint.setColor(Color.WHITE);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void drawIconAndText(Canvas canvas, int state, boolean onlyText) {
        initForState(state);

        String text = getText(state);
        Rect textRect = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), textRect);

        float textX = (screenProgressWidth ) ;
        float textY = (getHeight() / 2) - textRect.centerY();

        if (onlyText) {
            // 仅绘制文字

            canvas.drawText(text, textX, textY, mPaint);
        } else {

//            float textX = (getWidth() / 2) - getOffsetX(icon.getWidth(), textRect.centerX(), ICON_TEXT_SPACING_DP, true);
//            float textY = (getHeight() / 2) - textRect.centerY();

            canvas.drawText(text, textX, textY, mPaint);
            if (state == STATE_DEFAULT) return;



            Bitmap bufferBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas bufferCanvas = new Canvas(bufferBitmap);
            bufferCanvas.drawText(text, textX, textY, mPaint);


            // 设置混合模式
            mPaint.setXfermode(mPorterDuffXfermode);
            mPaint.setColor(Color.RED);
            RectF rectF = new RectF(0, 0, getWidth() * mProgress / 100, getHeight());
            // 绘制源图形
            bufferCanvas.drawRect(rectF, mPaint);
//
            canvas.drawBitmap(bufferBitmap, 0, 0, null);
            // 清除混合模式
            mPaint.setXfermode(null);
            if (!bufferBitmap.isRecycled()) {
                bufferBitmap.recycle();
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getText(int state) {
        String text;
        switch (state) {
            case STATE_DEFAULT:
                text = getResources().getString(R.string.pb_download);
                rightShowProgressTextWidth = (int) mPaint.measureText(text);
                break;

            case STATE_DOWNLOADING:
                DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                text = decimalFormat.format(mProgress) + "%";
//                rightShowProgressTextWidth = (int) (mPaint.measureText(text)* 3);
                break;

            case STATE_PAUSE:
                text = getResources().getString(R.string.pb_continue);
                rightShowProgressTextWidth = (int) mPaint.measureText(text);
                break;

            case STATE_DOWNLOAD_FINISH:
                text = getResources().getString(R.string.pb_open);
                rightShowProgressTextWidth = (int) mPaint.measureText(text);
                break;

            default:
                text = getResources().getString(R.string.pb_download);
                rightShowProgressTextWidth = (int) mPaint.measureText(text);
                break;
        }
        rightShowProgressTextWidth = (int) (mPaint.measureText(text));
        screenProgressWidth =getWidth() - rightShowProgressTextWidth - rightTextPadding;
        return text;
    }

    private float getOffsetX(float iconWidth, float textHalfWidth, float spacing, boolean isText) {
        float totalWidth = iconWidth + DensityUtil.dip2px(mContext, spacing) + textHalfWidth * 2;
        // 文字偏移量
        if (isText) return totalWidth / 2 - iconWidth - spacing;
        // 图标偏移量
        return totalWidth / 2 - iconWidth;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        screenProgressWidth = w - rightShowProgressTextWidth - rightTextPadding;
    }
}