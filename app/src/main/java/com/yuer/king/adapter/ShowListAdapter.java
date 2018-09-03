package com.yuer.king.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuer.king.R;
import com.yuer.king.model.ListBean;

import java.util.List;

/**
 * Created by yuer on 2018/7/31.
 */

public class ShowListAdapter extends BaseAdapter {
    private List<ListBean> modelsList;
    private Context context;

    public ShowListAdapter(Context context, List<ListBean> modelsList) {
        this.context = context;
        this.modelsList = modelsList;

    }

    @Override
    public int getCount() {
        return modelsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_iteam, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_name.setText(modelsList.get(i).getName());
        return view;
    }



    class ViewHolder {
        public View rootView;
        public TextView tv_name;
        public ImageView iv_pic;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
        }

    }
}