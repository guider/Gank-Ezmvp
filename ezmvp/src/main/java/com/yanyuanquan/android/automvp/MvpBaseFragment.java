package com.yanyuanquan.android.automvp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanyuanquan.android.automvp.presenter.BasePresenter;

/**
 * Created by guider on 16/6/23.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public abstract class MvpBaseFragment<P extends BasePresenter> extends BaseFragment {
    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        presenter = BasePresenter.getInstance(this.getClass());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onPostCreate(this);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroyView();
        presenter.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSave(outState);

    }


    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onResult(requestCode, resultCode, data);
    }

}
