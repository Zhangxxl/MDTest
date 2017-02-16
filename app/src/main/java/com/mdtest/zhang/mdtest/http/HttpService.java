package com.mdtest.zhang.mdtest.http;

import com.mdtest.zhang.mdtest.bean.Key;
import com.mdtest.zhang.mdtest.bean.ResponseBean;
import com.mdtest.zhang.mdtest.bean.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zhangxx on 2017/2/10.
 * MDTest --> com.mdtest.zhang.mdtest.http
 */

public interface HttpService {

    String base = "service/ldo";

    @FormUrlEncoded
    @POST(base)
    Observable<ResponseBean<User>> login(@Field("requestjson") String json);

    @FormUrlEncoded
    @POST(base)
    Observable<ResponseBean<List<Key>>> downKeys(@Field("requestjson") String json);
}
