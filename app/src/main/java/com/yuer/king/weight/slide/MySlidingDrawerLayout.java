package com.yuer.king.weight.slide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;

/**
 * Created by yuer on 2018/7/27.
 */

public class MySlidingDrawerLayout extends DrawerLayout {

    public MySlidingDrawerLayout(@NonNull Context context) {
        this(context,null,0);
    }

    public MySlidingDrawerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MySlidingDrawerLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
