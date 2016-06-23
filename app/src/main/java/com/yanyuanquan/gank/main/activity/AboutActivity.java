package com.yanyuanquan.gank.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.widget.TextView;


import com.yanyuanquan.gank.BuildConfig;
import com.yanyuanquan.gank.R;
import com.yanyuanquan.gank.base.BaseActivity;

import butterknife.Bind;

public class AboutActivity extends BaseActivity {


    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.tv_version)
    TextView tvVersion;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        return intent;
    }

    @Override
    protected boolean isBack() {
        return true;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        collapsingToolbar.setTitle("关于");

        tvVersion.setText("Version " + BuildConfig.VERSION_NAME);
    }

}
