package com.yanyuanquan.gank.main.presenter;


import com.yanyuanquan.gank.base.BasePresenter;
import com.yanyuanquan.gank.entity.GirlBean;
import com.yanyuanquan.gank.main.model.WelfareModel;
import com.yanyuanquan.gank.main.view.IWelfareView;

import java.util.List;


public class WelfarePresenter extends BasePresenter<IWelfareView> implements WelfareModel.OnLoadDataListListener{

    private WelfareModel mWelfareModel;

    public WelfarePresenter(IWelfareView view) {
        super(view);
        mWelfareModel=new WelfareModel();
    }

    public void loadNews(String type, int pageIndex) {

        //只有第一页的或者刷新的时候才显示刷新进度条
        if(pageIndex == 1) {
            mView.showRefresh();
        }
        mWelfareModel.loadNews(type,pageIndex, this);
    }

    @Override
    public void onSuccess(List<GirlBean> list) {
        mView.hideRefresh();
        mView.addData(list);
    }

    @Override
    public void onFailure(String msg, Throwable e) {

        mView.hideRefresh();
        mView.showLoadFailMsg();
    }
}
