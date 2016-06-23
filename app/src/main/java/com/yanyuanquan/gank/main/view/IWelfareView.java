package com.yanyuanquan.gank.main.view;


import com.yanyuanquan.gank.base.IBaseSwipeRefreshView;
import com.yanyuanquan.gank.entity.BaseBean;

import java.util.List;


public interface IWelfareView<T extends BaseBean> extends IBaseSwipeRefreshView {


    void addData(List<T> data);

    void showLoadFailMsg();
}
