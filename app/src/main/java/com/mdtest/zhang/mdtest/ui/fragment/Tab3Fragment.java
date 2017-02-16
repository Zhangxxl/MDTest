package com.mdtest.zhang.mdtest.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jayfeng.lesscode.core.ToastLess;
import com.mdtest.zhang.mdtest.R;
import com.mdtest.zhang.mdtest.ui.base.BaseFragment;

import java.util.Random;

import butterknife.BindView;

public class Tab3Fragment extends BaseFragment {

    @BindView(R.id.rv_grid)
    public RecyclerView rv_grid;

    @Override
    public View getContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_grid.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        rv_grid.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new MyHolder(new FrameLayout(getActivity()));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                MyHolder myHolder = (MyHolder) holder;
                myHolder.tv.setText("positon:" + position);
                myHolder.tv.setOnClickListener(v -> onItemClickListener(rv_grid, myHolder.tv, position));
            }

            @Override
            public int getItemCount() {
                return 100;
            }
        });
    }

    private void onItemClickListener(RecyclerView rv_grid, TextView tv, int position) {
//        ToastUtils.show(getActivity(), "position : " + position);
        ToastLess.$("position : " + position);
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyHolder(View itemView) {
            super(itemView);
            tv = new TextView(getActivity());
            tv.setGravity(Gravity.CENTER);
            Random random = new Random();
            int height = random.nextInt(500) + 100;
            tv.setTextColor(getResources().getColor(android.R.color.black));
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
            params.setMargins(8, 8, 0, 0);
            tv.setLayoutParams(params);
            int a = random.nextInt(255);
            int r = random.nextInt(255);
            int g = random.nextInt(255);
            int b = random.nextInt(255);
            tv.setBackgroundColor(Color.argb(a, r, g, b));
            ((ViewGroup) itemView).addView(tv);
        }
    }
}
