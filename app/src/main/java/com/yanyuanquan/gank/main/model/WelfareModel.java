package com.yanyuanquan.gank.main.model;


import com.yanyuanquan.gank.entity.GirlBean;
import com.yanyuanquan.gank.http.HttpMethods;
import com.yanyuanquan.gank.util.log.L;

import java.util.List;

import rx.Subscriber;


public class WelfareModel {


    public void loadNews(String type,int pageIndex, final OnLoadDataListListener listener) {
        Subscriber subscriber = new Subscriber<List<GirlBean>>() {
            @Override
            public void onCompleted() {
                if (!isUnsubscribed()) {
                    unsubscribe();
                }
            }
            @Override
            public void onError(Throwable e) {
                L.e(e);
                listener.onFailure("load news list failure.", e);
            }

            @Override
            public void onNext(List<GirlBean> girlBean) {

                listener.onSuccess(girlBean);
            }
        };

        HttpMethods.getInstance().getData(subscriber, type, pageIndex);

    }

    public interface OnLoadDataListListener {
        void onSuccess(List<GirlBean> list);
        void onFailure(String msg, Throwable e);
    }
}
