package com.yuer.king.activty;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yuer.king.R;
import com.yuer.king.weight.UnfoldButton;

/**
 * Created by yuer on 2018/7/11.
 */

public class UnfoldButtonActivty extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfoldbutton);
        UnfoldButton f = (UnfoldButton) findViewById(R.id.unfoldButton);
        f.addElement(R.drawable.ic_launcher_round, R.color.colorAccent, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里写菜单的点击事件
            }
        });
        f.addElement(R.drawable.bestjay,R.color.colorAccent,null);//同上
        f.addElement(R.drawable.bestjay2,R.color.colorAccent,null);//同上
        f.setmRotatable(true);//设置图标是否旋转 默认为true
        f.setmScale(1);//设置弹出缩放的比例 1为不缩放 范围是0—1
        f.setLength(250);//设置弹出的距离

    }
}
