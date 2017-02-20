package com.mdtest.zhang.mdtest.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jayfeng.lesscode.core.DrawableLess;
import com.jayfeng.lesscode.core.ToastLess;
import com.mdtest.zhang.mdtest.R;
import com.mdtest.zhang.mdtest.bean.Key;

import java.util.List;
import java.util.Random;

/**
 * Created by zhangxx on 2017/2/15.
 * MDTest --> com.mdtest.zhang.mdtest.adapter
 */

public class KeyListAdapter extends RecyclerView.Adapter {

    private Context context;
    public List<Key> keyList;
    private Random random;

    public KeyListAdapter(Context context, List<Key> keyList) {
        this.context = context;
        this.keyList = keyList;
        this.random = new Random();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_key, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        Key key = keyList.get(position);
        TextView tv_key_name = myViewHolder.findView(R.id.tv_key_name);
        TextView tv_key_location = myViewHolder.findView(R.id.tv_key_location);
        TextView tv_validity_time = myViewHolder.findView(R.id.tv_validity_time);
        ImageView iv_icon = myViewHolder.findView(R.id.iv_icon);

        tv_key_name.setText(key.key_name);
        tv_key_location.setText(key.community_name);
        String validity_time = DateFormat.format("有效期 : yyyy年MM月dd日",key.deadline).toString();
        tv_validity_time.setText(validity_time);
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        iv_icon.setImageDrawable(DrawableLess.$tint(context.getResources().getDrawable(R.mipmap.ic_launcher).mutate(), Color.rgb(r,g,b)));
        myViewHolder.itemView.setOnClickListener(v -> onItemClickListener(myViewHolder.itemView, position));
    }

    @Override
    public int getItemCount() {
        return keyList == null ? 0 : keyList.size();
    }

    private void onItemClickListener(View itemView, int position) {
        ToastLess.$(keyList.get(position).key_name);

    }

    public void setItemClickListener(){

    }
}
