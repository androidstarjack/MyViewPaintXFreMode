package com.yuer.king.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.yuer.king.R;
import com.yuer.king.utils.LogUtils;


/**
 * 类功能描述：</br>
 *  高仿QQ空间listVierw下拉图片放大特效
 * @author 于亚豪
 * @date 2018/7/31.
 * @version 1.0 </p> 修改时间： </br> 修改备注：</br>
 */
public class ZoomQQSlideListView extends ListView {
    private ImageView imageView;//要处理的iamgevirew
    private int minHeihgt;
    private MyNaimation myNaimation = null;
    public ZoomQQSlideListView(Context context) {
        this(context,null);
    }

    public ZoomQQSlideListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        minHeihgt = this.getResources().getDimensionPixelSize(R.dimen.size_default_height);
    }


    public ZoomQQSlideListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setDealView(ImageView imageView) {
        this.imageView  = imageView;
    }



    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
       // LogUtils.e("ZoomQQSlideListView",  "deltaX: "+deltaX +"   deltaY:"+deltaY +"   scrollX:"+scrollX +"   scrollY:"+scrollY +"   scrollRangeX:"+scrollRangeX +"  scrollRangeY: "+ scrollRangeY +"  maxOverScrollX: "+ maxOverScrollX +"   maxOverScrollY:"+ maxOverScrollY +"  isTouchEvent: "+isTouchEvent);
        if(deltaY >0 ){//上拉过度  放大，
            imageView.getLayoutParams().height = imageView.getHeight() - deltaY;
            imageView.requestLayout();
        }else if(deltaY < 0 ){//小于0,则为下拉  缩小，变为正常
            int currentHeigt = imageView.getHeight() - deltaY;
            if(currentHeigt > minHeihgt * 2){
                Toast.makeText(getContext(),"最多滑动到这里哦",Toast.LENGTH_SHORT).show();
            }else{
                imageView.getLayoutParams().height =currentHeigt;
                imageView.requestLayout();
            }

        }
        LogUtils.e("overScrollBy",  "iamgeview的高度：  " +  imageView.getHeight() );
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        LogUtils.e("onSizeChanged",  "w: "+w +"   h:"+h +"   oldw:"+oldw +"   oldh:"+oldh);
        View parentView = (View) imageView.getParent();
//        if(parentView instanceof ListView){
        int top = parentView.getTop();
        LogUtils.e("onSizeChanged",  "耶耶耶   top" +  top);
        if(imageView.getHeight() > minHeihgt){
            imageView.getLayoutParams().height = imageView.getHeight() - top;
            parentView.layout(parentView.getLeft(), 0, parentView.getRight(),parentView.getHeight());
            parentView.requestLayout();
        }
//        }
//        try {
//            myNaimation = new MyNaimation(minHeihgt);
//            myNaimation.setDuration(500);
//            myNaimation.setInterpolator(new LinearInterpolator());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_UP:
                imageView.clearAnimation();
                myNaimation = new MyNaimation(minHeihgt);
                myNaimation.setDuration(500);
                myNaimation.setInterpolator(new AnticipateOvershootInterpolator());
                imageView.startAnimation(myNaimation);
                break;
            case MotionEvent.ACTION_DOWN:
                imageView.clearAnimation();
                break;
        }
        return super.onTouchEvent(ev);
    }
    private class  MyNaimation extends Animation{
        private int currentHeight;
        private int extraHeight;
        public MyNaimation(int targetHeight){
            this.currentHeight =   imageView.getHeight();
            extraHeight = imageView.getHeight()- targetHeight;
        }
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            imageView.getLayoutParams().height = (int) (currentHeight - (extraHeight * interpolatedTime));
            imageView.requestLayout();
        }
    }
}
