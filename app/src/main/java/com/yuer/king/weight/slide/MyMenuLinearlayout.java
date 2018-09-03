package com.yuer.king.weight.slide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by yuer on 2018/7/27.
 */

public class MyMenuLinearlayout extends LinearLayout {

    public MyMenuLinearlayout(@NonNull Context context) {
        this(context,null,0);
    }

    public MyMenuLinearlayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public MyMenuLinearlayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
    }


}
