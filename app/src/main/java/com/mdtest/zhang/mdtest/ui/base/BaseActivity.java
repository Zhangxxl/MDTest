package com.mdtest.zhang.mdtest.ui.base;


import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.mdtest.zhang.mdtest.MyApp;
import com.mdtest.zhang.mdtest.utils.ActManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhangxx on 2017/2/13.
 * MDTest --> com.mdtest.zhang.mdtest.ui
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    public MyApp myapp;
    private Unbinder bind;
    public ActManager actManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myapp = (MyApp) getApplication();
        actManager = ActManager.getInstance();
        actManager.addActivity(this);
        setContentView(getLayout());
        bind = ButterKnife.bind(this);

    }

    public abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
        actManager.removeActivity(this);
    }

    public void setStatusBarColor(int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }
}
