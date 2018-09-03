package com.yuer.king.weight.slide;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by yuer on 2018/7/27.
 */

public class MyBrageProxyRelativelayout extends RelativeLayout {

    private InnerMenuBackGroundView innerMenuBackGroundView;

    private MyMenuLinearlayout myMenuLinearlayout;

    public MyBrageProxyRelativelayout(Context context) {
        this(context,null,0);
    }

    public MyBrageProxyRelativelayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyBrageProxyRelativelayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        innerMenuBackGroundView = new InnerMenuBackGroundView(getContext());
    }

    private MyBrageProxyRelativelayout setNeedContentView(MyMenuLinearlayout myMenuLinearlayout) {
        //做一个Relativelayout做一个中间代理媒介，专门为内容提供一个背景
        this.myMenuLinearlayout = myMenuLinearlayout;

        //既然是转移对象，布局的大小，宽高傲和颜色都要拿过来是吧

        setLayoutParams(myMenuLinearlayout.getLayoutParams());

        //还原颜色
        innerMenuBackGroundView.setColor(myMenuLinearlayout.getDrawingCacheBackgroundColor());
        addView(innerMenuBackGroundView,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        innerMenuBackGroundView.setColor(Color.TRANSPARENT);
        //大小
        addView(myMenuLinearlayout,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        return this;
    }
}
