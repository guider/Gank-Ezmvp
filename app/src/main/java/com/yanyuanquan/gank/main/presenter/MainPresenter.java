package com.yanyuanquan.gank.main.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.yanyuanquan.android.automvp.annotation.Model;
import com.yanyuanquan.android.automvp.presenter.BasePresenter;
import com.yanyuanquan.gank.App;
import com.yanyuanquan.gank.main.activity.MainActivity;
import com.yanyuanquan.gank.main.model.MainModel;
import com.yanyuanquan.gank.main.view.IMainView;
import com.yanyuanquan.gank.util.NetWorkUtils;


@Model(MainModel.class)
public class MainPresenter extends BasePresenter<MainActivity,MainModel> {


    @Override
    public void onPostCreate(@NonNull MainActivity view) {
        super.onPostCreate(view);
        if (NetWorkUtils.isConnectedByState(view)) {
            model.getSplashImage(view,1);
        }else {
            Toast.makeText(view, "没有网络连接!", Toast.LENGTH_LONG).show();
        }

    }

}
