package com.mdtest.zhang.mdtest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by blzx on 2017/2/6.into MDTest
 */
public class MainPagerAdapter extends FragmentPagerAdapter{

    ArrayList<Fragment> fragments;

    public MainPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "测试1";
            case 1:
                return "开 门";
            case 2:
                return "测试3";
        }
        return fragments.get(position).getClass().getSimpleName();
    }
}
