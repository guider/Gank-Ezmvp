package com.yanyuanquan.gank;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.yanyuanquan.gank.util.PreferencesUtils;
import com.yanyuanquan.gank.util.log.L;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        PreferencesUtils preferencesUtils = new PreferencesUtils(this);
        if(preferencesUtils.getBoolean(R.string.action_day_night, false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            //设置该app的主题根据时间不同显示
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);

        }

        //初始化日志工具
        L.init(BuildConfig.DEBUG);

    }
}
