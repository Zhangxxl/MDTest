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
    public SwipeRefreshLayout srl_keylist;
    private List<Key> keylist;
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
        srl_keylist.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        Observable.just(Key.class)
                .subscribeOn(Schedulers.io())
                .map(clazz -> act.myapp.dao.query(clazz))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(keylist -> this.keylist = keylist);
        adapter = new KeyListAdapter(getActivity(), keylist);
        recyclerView.setAdapter(adapter);
        srl_keylist.setOnRefreshListener(this::onRefresh);
        onRefresh();
    }

    public void onRefresh() {
        srl_keylist.setRefreshing(true);
        JSONObject json = HttpUtils.getJson(HttpUtils.Api.API_DOWN_KEY);
        Observable<ResponseBean<List<Key>>> downKey = act.myapp.httpService.downKeys(json.toString());
        downKey.subscribeOn(Schedulers.io())
                .map(keyListResp -> {
                    act.myapp.dao.save(keyListResp.bizobj);
                    return keyListResp.bizobj;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(keyList -> {
                    this.keylist = keyList;
                    if (adapter != null) {
                        adapter.keyList = this.keylist;
                        adapter.notifyDataSetChanged();
                    }
                    srl_keylist.setRefreshing(false);
                });
    }
}
