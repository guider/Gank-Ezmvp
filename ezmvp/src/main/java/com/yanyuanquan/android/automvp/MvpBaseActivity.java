package com.yanyuanquan.android.automvp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.yanyuanquan.android.automvp.presenter.BasePresenter;


public abstract class MvpBaseActivity<P extends BasePresenter> extends BaseActivity {
    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = BasePresenter.getInstance(this.getClass());
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        presenter.onPostCreate(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroyView();
        if (isFinishing())
            presenter.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        presenter.onSave(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onResult(requestCode,resultCode,data);
    }
}
