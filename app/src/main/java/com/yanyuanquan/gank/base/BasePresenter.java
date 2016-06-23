package com.yanyuanquan.gank.base;

public class BasePresenter<BV extends IBaseView> {

    protected BV mView;

    public BasePresenter(BV view) {
        mView = view;
    }
}
