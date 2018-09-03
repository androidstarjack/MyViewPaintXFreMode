package com.yuer.king.weight.manager;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * 类功能描述：</br>
 *
 * @author 于亚豪
 * @version 1.0 </p> 修改时间：2018/8/10</br> 修改备注：</br>
 */
public class MyAutoReacycleManager extends RecyclerView.LayoutManager {
    private SparseArray<Rect> allItemframs = new SparseArray<>();
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if(getItemCount() <= 0 ){
            return ;
        }
        if(state.isPreLayout()){
            return ;
        }
        //拆分View视图，重新放入放入scrap,以便对View重新布局
        detachAndScrapAttachedViews(recycler);
        int viewCount = getChildCount();
        int offsetX = 0;
        int offsetY = 0;
        int viewHight = 0;
        for (int i = 0; i < viewCount; i++) {
           // View view = getChildAt(i);
            View view = recycler.getViewForPosition(i);
            addView(view);
            measureChild(view,0,0);
            //此时应该拿到View的宽高
            int subWidth = getDecoratedMeasuredWidth(view);
            int subHeight = getDecoratedMeasuredHeight(view);
            //接下来取偏移量，进行换行
            viewHight = subHeight;
        }
        super.onLayoutChildren(recycler, state);
    }



    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }
    private void decathListForCheidView(RecyclerView.Recycler recycler) {

    }
}
