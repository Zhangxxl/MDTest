package com.mdtest.zhang.mdtest.http;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mdtest.zhang.mdtest.BuildConfig;
import com.mdtest.zhang.mdtest.MyApp;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhangxx on 2017/2/13.
 * MDTest --> com.mdtest.zhang.mdtest.http
 */

public class HttpUtils {

    public static Retrofit retrofit;

    private static String[] publicParams = new String[]{"appid", "app_version", "device_model",
            "os_type", "app_code", "apiId", "userid", "tel", "token"};                              //    公共参数

    private static final String baseUrl = "http://139.224.29.240:8380/";
    private static final long CONNECT_TIME_OUT = 6000L;

    static {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())     //解析方法
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    public static JSONObject getBaseJson(Api api) {
        JSONObject baseJson = new JSONObject();
        try {
            baseJson.put(publicParams[0], "T00001")
                    .put(publicParams[1], "8.8.8")
                    .put(publicParams[2], "A1")
                    .put(publicParams[3], "0")
                    .put(publicParams[4], BuildConfig.APPLICATION_ID)
                    .put(publicParams[5], api.url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseJson;
    }

    public static JSONObject getJson(Api api) {
        JSONObject json = getBaseJson(api);
        try {
            json.put(publicParams[6], MyApp.user.userid).put(publicParams[7], MyApp.user.tel).put(publicParams[8], MyApp.user.token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public enum Api {

        API_LOGIN("User_Login_Request"),
        API_DOWN_KEY("Key_Down_Request");

        String url;

        Api(String url) {
            this.url = url;
        }
    }
}
