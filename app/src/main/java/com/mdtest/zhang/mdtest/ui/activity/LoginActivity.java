package com.mdtest.zhang.mdtest.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.dd.CircularProgressButton;
import com.jayfeng.lesscode.core.LogLess;
import com.jayfeng.lesscode.core.NetworkLess;
import com.jayfeng.lesscode.core.ToastLess;
import com.mdtest.zhang.mdtest.MyApp;
import com.mdtest.zhang.mdtest.R;
import com.mdtest.zhang.mdtest.bean.ResponseBean;
import com.mdtest.zhang.mdtest.bean.User;
import com.mdtest.zhang.mdtest.http.HttpUtils;
import com.mdtest.zhang.mdtest.ui.base.BaseActivity;
import com.trello.rxlifecycle2.android.ActivityEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.bt_login)
    CircularProgressButton bt_login;
    @BindView(R.id.username)
    EditText et_username;
    @BindView(R.id.password)
    EditText et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        bt_login.setIndeterminateProgressMode(true);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.bt_login)
    public void login(View v) {
        bt_login.setProgress(50);
        String username = et_username.getText().toString().trim();
        String pwd = et_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)) {
            loginError("账号或密码为空");
        } else if (!NetworkLess.$online()) {
            loginError("网络连接失败,请检查网络");
        } else {
            submit(username, pwd);
        }
        bt_login.setClickable(false);
    }

    private void submit(String username, String pwd) {
        JSONObject json = HttpUtils.getBaseJson(HttpUtils.Api.API_LOGIN);
        try {
            json.put("tel", username).put("passwd", pwd);
        } catch (JSONException e) {
        }
        Observable<ResponseBean<User>> login = myapp.httpService.login(json.toString());
        login.observeOn(AndroidSchedulers.mainThread())
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .subscribe(this::onSuccessful, this::onError);
    }

    private void onSuccessful(ResponseBean<User> resp) {
        if (resp == null) {
            loginError("未知错误");
        } else if ("0".equals(resp.status.err_code)) {
            bt_login.setProgress(100);
            LogLess.$e(System.currentTimeMillis() + "");
            MyApp.user = resp.bizobj;
            Observable.timer(500, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .compose(bindToLifecycle())
                    .map(timer -> {
                        bt_login.setProgress(0);
                        return timer;
                    })
                    .subscribeOn(Schedulers.io())
                    .delay(300, TimeUnit.MILLISECONDS)
                    .subscribe(timer -> {
                        bt_login.setClickable(true);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        LogLess.$e(resp.toString());
                    });
        } else {
            loginError(resp.status.err_msg);
        }
    }

    private void onError(Throwable throwable) {

    }

    private void loginError(@NonNull String msg) {
        Observable.timer(1200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(timer -> {
                    bt_login.setProgress(-1);
                    ToastLess.$("--" + msg + "--");
                });
        Observable.timer(2200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(timer -> {
                    bt_login.setProgress(0);
                    bt_login.setClickable(true);
                });
    }
}
