package com.yanyuanquan.gank.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;


import com.yanyuanquan.gank.R;
import com.yanyuanquan.gank.base.BaseFragment;
import com.yanyuanquan.gank.base.BasePresenter;
import com.yanyuanquan.gank.base.IBaseSwipeRefreshView;

import butterknife.Bind;

public abstract class BaseSwipeRefreshFragment <P extends BasePresenter> extends BaseFragment<P> implements IBaseSwipeRefreshView,SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.swipe_refresh_widget)
    SwipeRefreshLayout swipeRefreshWidget;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initSwipeLayout();
    }


    private void initSwipeLayout(){
        swipeRefreshWidget.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light,
                R.color.accent);
        swipeRefreshWidget.setOnRefreshListener(this);
    }

    @Override
    public void showRefresh() {
        swipeRefreshWidget.setRefreshing(true);
    }

    @Override
    public void hideRefresh() {
        // 防止刷新消失太快，让子弹飞一会儿. do not use lambda!!
        swipeRefreshWidget.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(swipeRefreshWidget != null){
                    swipeRefreshWidget.setRefreshing(false);
                }
            }
        },1000);
    }


}
