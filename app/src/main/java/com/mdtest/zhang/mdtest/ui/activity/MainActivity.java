package com.mdtest.zhang.mdtest.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jayfeng.lesscode.core.LogLess;
import com.mdtest.zhang.mdtest.R;
import com.mdtest.zhang.mdtest.adapter.MainPagerAdapter;
import com.mdtest.zhang.mdtest.ui.base.BaseActivity;
import com.mdtest.zhang.mdtest.ui.fragment.KeyListFragment;
import com.mdtest.zhang.mdtest.ui.fragment.Tab1Fragment;
import com.mdtest.zhang.mdtest.ui.fragment.Tab3Fragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    public ActionBarDrawerToggle mDrawerToggle;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    public DrawerLayout mDrawerLayout;
    @BindView(R.id.fab)
    public FloatingActionButton fab;
    @BindView(R.id.nav_view)
    public NavigationView nav_view;
    @BindView(R.id.tab)
    public TabLayout tab;
    @BindView(R.id.vp_content)
    public ViewPager vp_content;
    @BindView(R.id.banner)
    public ImageView iv_banner;

    public ImageView iv_head_icon;
    public TextView tv_head_title;
    public TextView tv_head_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        findHeadView();
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action ^_^", Snackbar.LENGTH_LONG).setAction("Action", null).show());
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        nav_view.setNavigationItemSelectedListener(this);
        initContentPager();
    }

    private void findHeadView() {
        View headerView = nav_view.getHeaderView(0);
        if (headerView != null) {
            iv_head_icon = ButterKnife.findById(headerView, R.id.iv_head_icon);
            tv_head_title = ButterKnife.findById(headerView, R.id.tv_head_title);
            tv_head_msg = ButterKnife.findById(headerView, R.id.tv_head_msg);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogLess.$e("onStart");
        initUser();
    }

    private void initUser() {
        LogLess.$e("initUser");
        Glide.with(this)
                .load(myapp.user.photo)
                .fitCenter()
                .bitmapTransform(new CropCircleTransformation(this))
                .into(iv_head_icon);
        tv_head_title.setText(myapp.user.nickname);
        tv_head_msg.setText("个人积分 : " + myapp.user.score);
    }

    private void initContentPager() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Tab1Fragment());
        fragments.add(new KeyListFragment());
        fragments.add(new Tab3Fragment());
        vp_content.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), fragments));
        tab.setupWithViewPager(vp_content);
        vp_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int resource = R.drawable.banner1;
                switch (position) {
                    case 0:
                        resource = R.drawable.banner1;
                        break;
                    case 1:
                        resource = R.drawable.banner2;
                        break;
                    case 2:
                        resource = R.drawable.banner3;
                        break;
                }
                iv_banner.setImageResource(resource);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setElevation(0);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nav_camera:

                break;
            case R.id.nav_gallery:

                break;
            case R.id.nav_slideshow:

                break;
            case R.id.nav_manage:

                break;
            case R.id.nav_share:

                break;
            case R.id.nav_send:

                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iv_head_icon = null;
        tv_head_title = null;
        tv_head_msg = null;
    }
}
