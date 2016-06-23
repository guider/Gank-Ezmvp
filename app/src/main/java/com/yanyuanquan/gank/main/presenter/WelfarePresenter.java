package com.yanyuanquan.gank.main.presenter;


import android.view.View;

import com.yanyuanquan.android.automvp.annotation.Model;
import com.yanyuanquan.android.automvp.presenter.BasePresenter;
import com.yanyuanquan.gank.entity.GirlBean;
import com.yanyuanquan.gank.main.fragment.WelfareListFragment;
import com.yanyuanquan.gank.main.model.WelfareModel;
import com.yanyuanquan.gank.main.view.IWelfareView;

import java.util.List;

@Model(WelfareModel.class)
public class WelfarePresenter extends BasePresenter<WelfareListFragment,WelfareModel> implements WelfareModel.OnLoadDataListListener{

    public void loadNews(String type, int pageIndex) {

        //只有第一页的或者刷新的时候才显示刷新进度条
        if(pageIndex == 1) {
            view.showRefresh();
        }
        model.loadNews(type,pageIndex, this);
    }

    @Override
    public void onSuccess(List<GirlBean> list) {
        view.hideRefresh();
        view.addData(list);
    }

    @Override
    public void onFailure(String msg, Throwable e) {

        view.hideRefresh();
        view.showLoadFailMsg();
    }
}
