package com.mdtest.zhang.mdtest.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdtest.zhang.mdtest.R;
import com.mdtest.zhang.mdtest.ui.base.BaseFragment;

public class Tab1Fragment extends BaseFragment {

    @Override
    public View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }

}
