package com.yuer.king.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yuer.king.R;
import com.yuer.king.utils.LogUtils;

/**
 * Created by yuer on 2018/6/21.
 */

public class BitmapPaintView extends View {
    private Bitmap mBitmapDst ,mBitmapSrc;

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
    public BitmapPaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public BitmapPaintView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if(Build.VERSION.SDK_INT >= 11){
            setLayerType(LAYER_TYPE_SOFTWARE, null);
        }
        paint = new Paint();
//        paint.setColor(Color.YELLOW);
//        paint.setAntiAlias(true);
//        paint.setStyle(Paint.Style.FILL);
//        paint.setStrokeWidth(14);
//
//        circlrpaint = new Paint();
//        circlrpaint.setColor(Color.BLACK);
//        circlrpaint.setAntiAlias(true);
//        circlrpaint.setStyle(Paint.Style.STROKE);
//        circlrpaint.setStrokeWidth(14);
        mBitmapDst = BitmapFactory.decodeResource(getResources(), R.drawable.twiter_bg,null);
        mBitmapSrc = BitmapFactory.decodeResource(getResources(), R.drawable.twiter_light,null);
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

    //鲜花目标图，在绘制原图，在绘制原图之前，给画笔paint进行设置xfermode，然后用原图进行交会，这两张图就会按照模式规则进行处理
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

      try {
          //背景色
          canvas.drawARGB(255, 255, 156, 161);
          int lao = canvas.saveLayer(0,0,getWidth(),getHeight(),null,Canvas.ALL_SAVE_FLAG);

          canvas.drawBitmap(mBitmapSrc,0,0,paint);

          Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN);
          paint.setXfermode(xfermode);
          canvas.drawBitmap(mBitmapDst,0,0,paint);
          paint.setXfermode(null);
          canvas.restoreToCount(lao);
      }catch (Exception e){
          e.printStackTrace();
      }
    }

}
