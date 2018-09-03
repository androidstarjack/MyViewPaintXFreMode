package com.yuer.king.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import com.yuer.king.R;
import com.yuer.king.utils.LogUtils;

/**
 * Created by yuer on 2018/6/26.
 */

public class DrawableView01 extends Drawable {
    private Bitmap mBitDest, mBitmapSrc, mBitmap;
    private Drawable unSelectDrawable;
    private Drawable selectDrawabl;
    private Paint paint;
    public DrawableView01(Drawable unSelectDrawable,Drawable selectDrawabl) {
         this.unSelectDrawable = unSelectDrawable;
         this.selectDrawabl = selectDrawabl;
        init();
    }


    private void init() {
      LogUtils.e("yyh","level:  " + getLevel());
        paint  = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        setLevel(10000);
    }

    @Override
    public int getIntrinsicHeight() {
        return Math.max(selectDrawabl.getIntrinsicHeight(),unSelectDrawable.getIntrinsicHeight());
    }

    @Override
    public int getIntrinsicWidth() {
        return Math.max(selectDrawabl.getIntrinsicWidth(),unSelectDrawable.getIntrinsicWidth());
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        //定义好Drawable的实际快高
        selectDrawabl.setBounds(bounds);
        unSelectDrawable.setBounds(bounds);
        //其实是重叠在一起的
        super.onBoundsChange(bounds);
    }

    @Override
    protected boolean onLevelChange(int level) {
        //当level变化的时候，需要进行对自己重回
         invalidateSelf();
        return true;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        Rect rect = getBounds();
        Rect tempRect = new Rect();
        Gravity.apply(Gravity.LEFT, rect.width()/2, rect.height(),rect,tempRect);
        canvas.drawCircle(rect.centerX(),rect.centerY(),20,paint);

        canvas.save();
        canvas.clipRect(tempRect);
        unSelectDrawable.draw(canvas);

        canvas.restore();

        Gravity.apply(Gravity.RIGHT, rect.width()/2, rect.height(),rect,tempRect);

        canvas.clipRect(tempRect);
        selectDrawabl.draw(canvas);

        canvas.drawCircle(rect.centerX(),rect.centerY(),20,paint);

    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }
}
