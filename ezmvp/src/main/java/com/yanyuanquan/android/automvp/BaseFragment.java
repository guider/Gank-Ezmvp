package com.yanyuanquan.android.automvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by guider on 16/6/23.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public abstract class BaseFragment extends UtilFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(getLayout(),container);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        initData();
        initView();
    }


    protected abstract void initView();
    protected abstract void initData();
    protected abstract  void init();

    public abstract int getLayout() ;
}
