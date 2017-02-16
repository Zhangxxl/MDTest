package com.mdtest.zhang.mdtest.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by blzx on 2017/2/7.into MDTest
 */
public class MyViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;

    public MyViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<View>();
    }

    public <T extends View> T findView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public MyViewHolder setClickListener(int viewId, View.OnClickListener listener) {
        View view = findView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}
