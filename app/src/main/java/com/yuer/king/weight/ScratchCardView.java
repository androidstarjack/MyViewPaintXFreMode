package com.yuer.king.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.yuer.king.R;

/**
 * Created by yuer on 2018/6/22.
 */

public class ScratchCardView extends View {
    private Bitmap mBitDest,mBitmapSrc,mBitmap;
    private Paint mPaint;
    private Path mPath;
    public ScratchCardView(Context context) {
        super(context);
        init();
    }

    public ScratchCardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mBitDest = BitmapFactory.decodeResource(getResources(), R.drawable.guaguaka,null);
        mBitmapSrc = BitmapFactory.decodeResource(getResources(), R.drawable.guaguaka_text1,null);
        mBitmap = Bitmap.createBitmap(mBitDest.getWidth(),mBitDest.getHeight(),Bitmap.Config.ARGB_8888);

        mPaint = new Paint();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(50);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmapSrc,0,0,mPaint);
        int c = canvas.saveLayer(0,0,getWidth(),getHeight(),null,Canvas.ALL_SAVE_FLAG);

        Canvas cx = new Canvas(mBitmap);
        cx.drawPath(mPath,mPaint);

        canvas.drawBitmap(mBitmap,0,0,mPaint);

        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
        mPaint.setXfermode(xfermode);

        canvas.drawBitmap(mBitDest,0,0,mPaint);

        mPaint.setXfermode(null);
        canvas.restoreToCount(c);
    }
    float getX = 0;
    float getY = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(),event.getY());
                getX = event.getX();
                getY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = (event.getX() + getX) / 2;
                float endY =(event.getY() + getY)/2 ;
                mPath.quadTo(getX,getY,endX,endY);
                getX = event.getX();
                getY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }


}
