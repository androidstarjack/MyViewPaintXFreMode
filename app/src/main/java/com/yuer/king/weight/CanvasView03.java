package com.yuer.king.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yuer.king.R;

/**
 * Created by yuer on 2018/6/27.
 */

public class CanvasView03 extends View {
    private Paint paint;
    private Bitmap bitmap;
    public CanvasView03(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);                           //设置画笔为无锯齿
        paint.setColor(Color.BLACK);                        //设置画笔颜色
        paint.setTextSize((float) 30.0);                    //设置字体大小
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xyjy2);
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.clipRect(100, 100, 350, 600, Region.Op.INTERSECT);//设置显示范围
//        canvas.drawColor(Color.RED);
        canvas.drawBitmap(bitmap,0,0,paint);
        canvas.drawCircle(100,100,100,paint);
    }

}
