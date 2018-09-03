package com.yuer.king.activty;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.Window;

import com.yuer.king.R;
import com.yuer.king.weight.slide.InnerMenuBackGroundView;

/**
 * Created by yuer on 2018/7/26.
 */

public class SlidingMenuBarActivity extends Activity {
    InnerMenuBackGroundView imbv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_slidingmenubar);
       // imbv = findViewById(R.id.imbv);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        imbv.setControlXAndY(event.getRawX(),event.getRawY());
        imbv.setCurrentXAndY(event);
        return super.onTouchEvent(event);
    }

}
