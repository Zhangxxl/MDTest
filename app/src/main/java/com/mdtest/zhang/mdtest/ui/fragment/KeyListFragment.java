package com.mdtest.zhang.mdtest.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdtest.zhang.mdtest.R;
import com.mdtest.zhang.mdtest.adapter.KeyListAdapter;
import com.mdtest.zhang.mdtest.bean.Key;
import com.mdtest.zhang.mdtest.bean.ResponseBean;
import com.mdtest.zhang.mdtest.http.HttpUtils;
import com.mdtest.zhang.mdtest.ui.base.BaseFragment;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by blzx on 2017/2/6.into MDTest
 */
public class KeyListFragment extends BaseFragment {

    @BindView(R.id.rv_list)
    RecyclerView recyclerView;
    @BindView(R.id.srl_keylist)
    public SwipeRefreshLayout srl_key_list;
    private List<Key> key_list;
    private KeyListAdapter adapter;

    @Nullable
    @Override
    public View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        srl_key_list.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        Observable.just(Key.class)
                .subscribeOn(Schedulers.io())
                .compose(bindToLifecycle())
                .map(clazz -> act.myapp.dao.query(clazz))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(key_list -> this.key_list = key_list);
        adapter = new KeyListAdapter(getActivity(), key_list);
        recyclerView.setAdapter(adapter);
        srl_key_list.setOnRefreshListener(this::onRefresh);
        onRefresh();
    }

    public void onRefresh() {
        srl_key_list.setRefreshing(true);
        JSONObject json = HttpUtils.getJson(HttpUtils.Api.API_DOWN_KEY);
        Observable<ResponseBean<List<Key>>> downKey = act.myapp.httpService.downKeys(json.toString());
        downKey.subscribeOn(Schedulers.io())
                .compose(bindToLifecycle())
                .map(keyListResp -> {
                    act.myapp.dao.save(keyListResp.bizobj);
                    return keyListResp.bizobj;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(keyList -> {
                    this.key_list = keyList;
                    if (adapter != null) {
                        adapter.keyList = this.key_list;
                        adapter.notifyDataSetChanged();
                    }
                    srl_key_list.setRefreshing(false);
                });
    }
}