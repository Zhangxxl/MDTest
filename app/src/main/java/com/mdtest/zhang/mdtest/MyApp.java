package com.mdtest.zhang.mdtest;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.jayfeng.lesscode.core.$;
import com.jayfeng.lesscode.core.AppLess;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;
import com.mdtest.zhang.mdtest.bean.User;
import com.mdtest.zhang.mdtest.event.DBUpdateListener;
import com.mdtest.zhang.mdtest.http.HttpService;
import com.mdtest.zhang.mdtest.http.HttpUtils;

/**
 * Created by zhangxx on 2017/2/13.
 * MDTest --> com.mdtest.zhang.mdtest
 */

public class MyApp extends Application {

    public Gson gson;
    public static User user;
    public HttpService httpService;
    public SharedPreferences sp;
    public LiteOrm dao;


    @Override
    public void onCreate() {
        super.onCreate();
        $.getInstance().context(getApplicationContext())
                .log(BuildConfig.DEBUG, "Zhang")
                .build();
        httpService = HttpUtils.retrofit.create(HttpService.class);
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        gson = new Gson();
        DataBaseConfig daoConfig = new DataBaseConfig(this,AppLess.$appname(),BuildConfig.DEBUG, AppLess.$vercode(),new DBUpdateListener());
        dao = LiteOrm.newSingleInstance(daoConfig);
    }
}
