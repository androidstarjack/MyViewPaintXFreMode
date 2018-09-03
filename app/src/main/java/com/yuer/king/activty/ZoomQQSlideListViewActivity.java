package com.yuer.king.activty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.yuer.king.R;
import com.yuer.king.adapter.ShowListAdapter;
import com.yuer.king.impl.DataProvider;
import com.yuer.king.model.ListBean;
import com.yuer.king.weight.ZoomQQSlideListView;

import java.util.List;

/**
 * Created by yuer on 2018/7/31.
 */

public class ZoomQQSlideListViewActivity extends AppCompatActivity {
    private List<ListBean> listBeanList;
    private ZoomQQSlideListView lv_list;
    private ShowListAdapter showListAdapter;
    private View headView;

    public ZoomQQSlideListViewActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoomqqlist);
        lv_list  =  findViewById(R.id.lv_list);
        listBeanList = DataProvider.getDataList();
        headView = View.inflate(this,R.layout.headview_qq_like,null);
        ImageView imageView = headView.findViewById(R.id.layout_header_image);
        if(listBeanList !=null){
            lv_list.addHeaderView(headView);
            lv_list.setDealView(imageView);
            lv_list.setAdapter(new ShowListAdapter(this,listBeanList));
        }
    }
}
