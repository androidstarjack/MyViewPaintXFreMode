package com.yuer.king.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 类功能描述：</br>
 *
 * @author 于亚豪
 * @version 1.0 </p> 修改时间：2018/8/17</br> 修改备注：</br>
 */
public class MyTextXfermodeView extends View {
    private int  currentWidth;
    private int  currentHeight;
    private Rect rect;
    private Paint paint;
    public MyTextXfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        if(Build.VERSION.SDK_INT >= 11){
            setLayerType(LAYER_TYPE_SOFTWARE, null);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(rect,paint);

        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC);
        int left = currentWidth /8 * 3;
        int top = rect.top;
        int right = currentWidth/8 * 5 ;
        int bottom =rect.bottom;
        paint.setColor(Color.BLACK);
        String text = "终端研发部";
        float length = paint.measureText(text);
        Rect rect2 = new Rect(left,top,right,bottom);
        canvas.drawText(text,rect2.centerX() - length/2,rect2.exactCenterY()-10,paint);

        paint.setXfermode(xfermode);
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect2,paint);
        paint.setColor(Color.BLUE);
        canvas.drawText(text,rect2.centerX()/2 - length/2,rect2.exactCenterY()-10,paint);



        paint.setXfermode(null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.currentHeight = h;
        this.currentWidth = w;
        int left = currentWidth / 4 ;
        int top = currentHeight/ 4;
        int right = currentWidth/4 * 3 ;
        int bottom = currentHeight/ 4 * 3;
        rect = new Rect(left,top,right,bottom);
    }
}
