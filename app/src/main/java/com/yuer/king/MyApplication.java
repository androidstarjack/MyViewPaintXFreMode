package com.yuer.king;

import android.app.Application;
import android.content.Context;

import java.util.List;

/**
 * Created by yuer on 2018/6/21.
 */

public class MyApplication extends Application {

    public static final boolean DEBUG =  true;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

    }
}
