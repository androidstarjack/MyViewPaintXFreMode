package com.yuer.king.activty;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.yuer.king.R;
import com.yuer.king.weight.Drawable02View;
import com.yuer.king.weight.DrawableView01;

/**
 * Created by yuer on 2018/6/26.
 */

public class DrawavleSimple01Activty extends AppCompatActivity {
    ImageView iv_src;
    DrawableView01 drawableView01 ;
    Drawable02View drawable02View ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawavlesimple01);
        iv_src = findViewById(R.id.iv_src);
        drawableView01 = new  DrawableView01(getResources().getDrawable(R.drawable.avft),getResources().getDrawable(R.drawable.avft_active));
        Drawable [] drawables = {
                getResources().getDrawable(R.drawable.bubbles_active),getResources().getDrawable(R.drawable.ic_launcher_round),
                getResources().getDrawable(R.drawable.bullseye_active),getResources().getDrawable(R.drawable.circle_filled_active),
                getResources().getDrawable(R.drawable.circle_outline_active),getResources().getDrawable(R.drawable.bubble_frame)
        };
        drawable02View = new Drawable02View(drawables);
        iv_src.setImageDrawable(drawable02View);
    }
}