package com.yuer.king.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yuer.king.utils.LogUtils;


/**
 * 类功能描述：</br>
 *
 * @author 于亚豪
 * @version 1.0 </p> 修改时间：2018/8/3</br> 修改备注：</br>
 */
public class SvgTestView extends View {
    private Paint paint,paint2;
    private Path path;
    private int width = 0;
    private int height = 0;
    private  Region region = null;
    private int mControlX = 100;
    private int mControlY = 100;
    public SvgTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint2 = new Paint();
        paint2.setColor(Color.BLUE);
        paint2.setAntiAlias(true);
        paint2.setStrokeWidth(2);
        paint2.setStyle(Paint.Style.FILL);



        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

         path = new Path();
        region = new Region();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(width/2-100,height/2-100,width/2+100,height/2+100,paint);

        path.reset();
        path.lineTo(width/2,height/2);
        int cx= width/2 +mControlX;
        int cy= height/2 - mControlY;
        int endx =  width/2;
        int endy =  height/3;
        path.reset();
        path.quadTo(cx,cy,endx,endy);
        path.close();
        canvas.drawPath(path,paint);


        RectF rectF = new RectF();
        path.computeBounds(rectF,true);
        region.setPath(path,new Region((int)rectF.left,(int)rectF.top,(int)rectF.right,(int)rectF.bottom));

        Rect rectF2= region.getBounds();
        canvas.drawRect(rectF2,paint);

        Path path2 = region.getBoundaryPath();
        canvas.drawPath(path2,paint2);
        LogUtils.e("yyh",mControlX +"   "+mControlY);
        canvas.drawPoint(mControlX,mControlY,paint2);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
        handler.sendEmptyMessageDelayed(100,1000);
    }

//    private class startRun implements  Runnable{
//        @Override
//        public void run() {
//            if(mControlX > width){
//                mControlX --;
//            }else   if(mControlX < 0){
//                mControlX++;
//            }
//            if(mControlY > height){
//                mControlY --;
//            }else   if(mControlY < 0){
//                mControlY++;
//            }
//            handler.sendEmptyMessageDelayed(100,800);
//        }
//    }
    private  boolean isMoreThanWidth  = false;
    private  boolean isMoreThanHeight  = false;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case  100:
                    if(isMoreThanWidth){
                        mControlX = mControlX -10 ;
                    }else{
                        mControlX = mControlX+10;
                    }
                    if(isMoreThanHeight){
                        mControlY = mControlY - 10;
                    }else{
                        mControlY = mControlY +10;
                    }

                    if(mControlX > width){
                        isMoreThanWidth = true;
                    }else   if(mControlX < 0){
                        isMoreThanWidth = false;
                    }

                    if(mControlY > height){
                        isMoreThanHeight = true;
                    }else   if(mControlY < 0){
                        isMoreThanHeight = false;
                    }
                    postInvalidate();
                    handler.sendEmptyMessageDelayed(100,100);
                    break;
            }
        }
    };
}
