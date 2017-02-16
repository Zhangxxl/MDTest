package com.mdtest.zhang.mdtest.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhangxx on 2017/2/13.
 * MDTest --> com.mdtest.zhang.mdtest.ui.base
 */

public abstract class BaseFragment extends Fragment {

    protected BaseActivity act;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        act = (BaseActivity) getActivity();
        View view = getContentView(inflater, container, savedInstanceState);
        bind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
    }

    public abstract View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);
}
