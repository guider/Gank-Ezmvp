package com.yanyuanquan.gank.main.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.yanyuanquan.android.automvp.BaseActivity;
import com.yanyuanquan.android.automvp.annotation.Presenter;
import com.yanyuanquan.gank.R;
import com.yanyuanquan.gank.main.adapter.ViewPageFragmentAdapter;
import com.yanyuanquan.gank.main.fragment.WelfareListFragment;
import com.yanyuanquan.gank.main.model.GankCategory;
import com.yanyuanquan.gank.main.presenter.MainPresenter;
import com.yanyuanquan.gank.main.view.IMainView;
import com.yanyuanquan.gank.util.PreferencesUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

@Presenter(MainPresenter.class)
public class MainActivity extends BaseActivity<MainPresenter> {

    @Nullable
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.tab_layout)
    TabLayout tablayout;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private ViewPageFragmentAdapter tabsAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initToobar();
        setTitle(R.string.title_main);
        tabsAdapter = new ViewPageFragmentAdapter(getSupportFragmentManager(), tablayout, viewPager);
        for (GankCategory gankCategory : GankCategory.values()) {
            tabsAdapter.addTab(gankCategory.name().toString(), "", WelfareListFragment.class,
                    getBundle(gankCategory.name()));
        }
        viewPager.setOffscreenPageLimit(GankCategory.values().length);
        viewPager.setAdapter(tabsAdapter);
        tablayout.setupWithViewPager(viewPager);
        fab.setOnClickListener(v -> startActivity(AboutActivity.newIntent(v.getContext())));

    }

    private void initToobar() {
        setTitle("");
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private Bundle getBundle(String type) {
        Bundle bundle = new Bundle();
        bundle.putString(WelfareListFragment.BUNDLE_KEY_TYPE, type);
        return bundle;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_day_night);
        initNotifiableItemState(item);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        int uiMode = getResources().getConfiguration().uiMode;
        int dayNightUiMode = uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (dayNightUiMode == Configuration.UI_MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (dayNightUiMode == Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
        }
    }

    private void initNotifiableItemState(MenuItem item) {
        PreferencesUtils preferencesUtils = new PreferencesUtils(this);
        item.setChecked(preferencesUtils.getBoolean(R.string.action_day_night, false));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_day_night:
                PreferencesUtils preferencesUtils = new PreferencesUtils(this);
                if (item.isChecked()) {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    preferencesUtils.saveBoolean(R.string.action_day_night, false);
                } else {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    preferencesUtils.saveBoolean(R.string.action_day_night, true);
                }
                recreate();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }


}
