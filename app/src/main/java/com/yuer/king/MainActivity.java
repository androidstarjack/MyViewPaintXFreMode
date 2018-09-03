package com.yuer.king;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yuer.king.activty.CanvasView01Activty;
import com.yuer.king.activty.ChaneseAllMapActivity;
import com.yuer.king.activty.DrawavleSimple01Activty;
import com.yuer.king.activty.PaintFoilterBitmapActivty;
import com.yuer.king.activty.PaintFoilterColorActivty;
import com.yuer.king.activty.PathMeasureCircleLoadingActivity;
import com.yuer.king.activty.PathSmallFaceActivty;
import com.yuer.king.activty.REdextTextMainActivity;
import com.yuer.king.activty.ScratchCardActivty;
import com.yuer.king.activty.SlidingMenuBarActivity;
import com.yuer.king.activty.SvgTestActivty;
import com.yuer.king.activty.TestProgressBarActivity;
import com.yuer.king.activty.UnfoldButtonActivty;
import com.yuer.king.activty.WebViewHellowActivity;
import com.yuer.king.activty.ZoomQQSlideListViewActivity;
import com.yuer.king.adapter.SimpleRecycleAdapter;
import com.yuer.king.impl.DataProvider;
import com.yuer.king.utils.ActivityUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.uetool.UETool;

/**
 * Android 水波纹显示进度效果:
 * https://blog.csdn.net/u014452224/article/details/55193542
 */
public class MainActivity extends AppCompatActivity {
//    @Bind(R.id.rlv_main)
//    RecyclerView rlv_main ;
//    private SimpleRecycleAdapter simpleRecycleAdapter;
//    private List<String> list = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UETool.showUETMenu();
//        try{
//            setContentView(R.layout.activity_main2);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        ButterKnife.bind(this);
//        rlv_main.setLayoutManager(new LinearLayoutManager(this));
//        rlv_main.setHasFixedSize(true);
//        rlv_main.setHasFixedSize(true);
//        list = DataProvider.getDataListMainData();
//        simpleRecycleAdapter  = new SimpleRecycleAdapter(list);
//        rlv_main.setAdapter(simpleRecycleAdapter);
    }

    @OnClick({R.id.btn01,R.id.btn02,R.id.btn03,R.id.btn04,R.id.btn05,R.id.btn06,R.id.btn07,R.id.btn08,R.id.btn09,R.id.btn010,R.id.btn011, R.id.btn012,R.id.btn013,R.id.btn014,R.id.btn015})
    public void  onClick(View view){
           switch (view.getId()){
               case R.id.btn01:
                   ActivityUtils.showActivity(MainActivity.this,PaintFoilterColorActivty.class);
                   break;
               case R.id.btn02:
                   ActivityUtils.showActivity(MainActivity.this,PaintFoilterBitmapActivty.class);
                   break;
               case R.id.btn03:
                   ActivityUtils.showActivity(MainActivity.this,ScratchCardActivty.class);
                   break;
                   case R.id.btn04:
                   ActivityUtils.showActivity(MainActivity.this,CanvasView01Activty.class);
                   break;
                  case R.id.btn05:
                   ActivityUtils.showActivity(MainActivity.this,DrawavleSimple01Activty.class);
                   break;
                  case R.id.btn06:
                   ActivityUtils.showActivity(MainActivity.this,PathMeasureCircleLoadingActivity.class);
                   break;
                    case R.id.btn07:
                   ActivityUtils.showActivity(MainActivity.this,PathSmallFaceActivty.class);
                   break;
               case R.id.btn08:
                   ActivityUtils.showActivity(MainActivity.this,UnfoldButtonActivty.class);
                   break;
                   case R.id.btn09://Android 自定义view-仿新浪微博#话题#插入EditText
                   ActivityUtils.showActivity(MainActivity.this,REdextTextMainActivity.class);
                   break;
               case R.id.btn010://Android 自定义view-仿新浪微博#话题#插入EditText
                   ActivityUtils.showActivity(MainActivity.this,SlidingMenuBarActivity.class);
                   break;
              case R.id.btn011://高仿QQ空间打造下拉头部具变大效果
                   ActivityUtils.showActivity(MainActivity.this,ZoomQQSlideListViewActivity.class);
                   break;
                case R.id.btn012://中国地图
                   ActivityUtils.showActivity(MainActivity.this,ChaneseAllMapActivity.class);
                   break;
               case R.id.btn013://
                   ActivityUtils.showActivity(MainActivity.this,SvgTestActivty.class);
                   break;
               case R.id.btn014://
                   ActivityUtils.showActivity(MainActivity.this,TestProgressBarActivity.class);
                   break;
                   case R.id.btn015://
                   ActivityUtils.showActivity(MainActivity.this,WebViewHellowActivity.class);
                   break;
           }
    }
}
