package com.yanyuanquan.android.automvp.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.yanyuanquan.android.automvp.model.BaseModel;

/**
 * Created by guider on 16/6/24.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public class EzPresenter<V,M extends BaseModel> extends BasePresenter<V,M>{

    @Override
    public void onCreate(@NonNull V view, Bundle savedState) {
        super.onCreate(view, savedState);

    }

    @Override
    public void onPostCreate(@NonNull V view) {
        super.onPostCreate(view);

    }


}
