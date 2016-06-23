package com.yanyuanquan.gank.main.fragment;


import com.yanyuanquan.gank.base.BaseSwipeRefreshFragment;
import com.yanyuanquan.gank.main.presenter.WelfarePresenter;

public abstract class LazyFragment extends BaseSwipeRefreshFragment<WelfarePresenter> {

    protected boolean isVisible;
    /**
     * 在这里实现Fragment数据的缓加载.
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible(){
        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible(){}
}
