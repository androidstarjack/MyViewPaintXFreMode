package com.yuer.king.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * 类功能描述：</br>
 *
 * @author 于亚豪
 * @version 1.0 </p> 修改时间：2018/8/9</br> 修改备注：</br>
 */
public class SimpleRecycleAdapter extends  RecyclerView.Adapter implements View.OnClickListener {
    private List<String> list;
    public SimpleRecycleAdapter(List<String> list){
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Button button = new Button(parent.getContext());
        return new MySimpleViewHolder(button);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MySimpleViewHolder mySimpleViewHolder = (MySimpleViewHolder) holder;
        mySimpleViewHolder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public void onClick(View view) {

    }

    private class MySimpleViewHolder extends RecyclerView.ViewHolder {
        Button textView = null;
        public MySimpleViewHolder(Button itemView) {
            super(itemView);
            textView = itemView;
        }
    }
}
