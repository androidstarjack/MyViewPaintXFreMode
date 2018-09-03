package com.yuer.king.weight;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import com.yuer.king.utils.LogUtils;

/**
 * Created by yuer on 2018/6/28.
 */

public class Drawable02View extends Drawable {
    private Paint paint;

    /**
     * BitmapShader
     */
    private BitmapShader mBitmapShader;
    private     Drawable [] drawables = null;

    public Drawable02View( Drawable [] drawables ){
        this.drawables = drawables;
        init();
    }
    private void init() {
        LogUtils.e("yyh","level:  " + getLevel());
        paint  = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
//        setLevel(10000);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void draw(@NonNull Canvas canvas) {
//        canvas.drawColor(Color.RED);
        Rect rect = getBounds();
//        canvas.drawRect(bounds,paint);
//        canvas.drawCircle(100,100,50,paint);
        try {
            for (int i = 0; i < drawables.length; i++) {
                Rect tempRect = new Rect();
                tempRect.left =  drawables[0].getIntrinsicWidth() *  2 /3 * i;
                tempRect.top =  0;
                tempRect.right =  drawables[0].getIntrinsicWidth() * 2 /3 * (i+1);
                tempRect.bottom =  drawables[0].getIntrinsicHeight();

                if(i == 0){
                    paint.reset();     paint.setStyle(Paint.Style.STROKE);
                    paint.setColor(Color.RED);
                }else if(i == 1){
                    paint.reset();     paint.setStyle(Paint.Style.STROKE);
                    paint.setColor(Color.BLUE);
                }else if(i == 2){
                    paint.reset();     paint.setStyle(Paint.Style.STROKE);
                    paint.setColor(Color.YELLOW);
                }else if(i == 3){
                    paint.reset();     paint.setStyle(Paint.Style.STROKE);
                    paint.setColor(Color.GRAY);
                }else if(i == 4){
                    paint.reset();     paint.setStyle(Paint.Style.STROKE);
                    paint.setColor(Color.RED);
                }else if(i == 5){
                    paint.reset();     paint.setStyle(Paint.Style.STROKE);
                    paint.setColor(Color.LTGRAY);
                }

//            canvas.drawRect(tempRect,paint);
                drawables[i].setBounds(tempRect);
//            canvas.clipRect(tempRect);
//            drawables[i].draw(canvas);
                BitmapDrawable bd = (BitmapDrawable)  drawables[i];
                mBitmapShader = new BitmapShader(bd.getBitmap(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                paint.setShader(mBitmapShader);
                //   canvas.drawRoundRect(tempRect, tempRect.centerX(), tempRect.centerX() ,paint);
                //canvas.drawRoundRect(tempRect.left,tempRect.top,tempRect.right,tempRect.bottom,tempRect.centerX(), tempRect.centerY(),paint);
//                canvas.drawBitmap(bd.getBitmap(),,paint);
                drawables[i].draw(canvas);
                canvas.save();
                LogUtils.e("draw",   tempRect.left + "   " + tempRect.top + "   " + tempRect.right +"   " +   tempRect.bottom );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getIntrinsicHeight() {
        int intrinsicHeight = 0;
        for (Drawable drawable2 : drawables){
            intrinsicHeight = Math.max(drawable2.getIntrinsicHeight(),intrinsicHeight);
        }
        return intrinsicHeight;
    }

    @Override
    public int getIntrinsicWidth() {
        int interrisicWidth = drawables[0].getIntrinsicWidth() * drawables.length * 2 /3 + drawables[0].getIntrinsicWidth()  * 1 /3;
        LogUtils.e("getIntrinsicWidth",interrisicWidth+"");
        return interrisicWidth;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {

        for (int i = 0; i < drawables.length; i++) {
            Rect subRect = new Rect();
            subRect.left =  drawables[0].getIntrinsicWidth() * 2 /3 * i;
            subRect.top =  0;
            subRect.right =  drawables[0].getIntrinsicWidth() * 2 /3 * (i+1);
            subRect.bottom =  drawables[0].getIntrinsicHeight();
            drawables[i].setBounds(subRect);
            LogUtils.e("yyh",   subRect.left + "   " + subRect.top + "   " + subRect.right +"   " +   subRect.bottom );
        }
        super.onBoundsChange(bounds);
    }


    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    @Override
    protected boolean onLevelChange(int level) {
        //当level变化的时候，需要进行对自己重回
//        invalidateSelf();
        return true;
    }
}
