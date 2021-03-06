package com.yanyuanquan.gank.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanyuanquan.android.automvp.annotation.Presenter;
import com.yanyuanquan.android.automvp.presenter.*;
import com.yanyuanquan.android.automvp.presenter.BasePresenter;

import butterknife.ButterKnife;


public abstract class BaseFragment<P extends com.yanyuanquan.android.automvp.presenter.BasePresenter> extends Fragment {

   protected  P presenter;
    protected View rootView;

    protected abstract int getLayout();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayout(), container, false);
            ButterKnife.bind(this, rootView);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        ButterKnife.bind(this, rootView);
        presenter = BasePresenter.getInstance(this.getClass());
        presenter.onPostCreate(this);
        return rootView;
    }



    protected String getName(){
        return BaseFragment.class.getName();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //取消请求
        ButterKnife.unbind(getName());
    }
}
