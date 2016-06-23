package com.yanyuanquan.android.automvp.annotation;

import com.yanyuanquan.android.automvp.model.BaseModel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by apple on 16/6/22.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Model {
    Class<? extends BaseModel> value();

}
