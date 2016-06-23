package com.yanyuanquan.android.automvp.model;

import android.content.Context;

/**
 */
public class BaseModel extends UtilModel{

    private Context context;

    public static final <T extends  BaseModel> T getInstance(Class<T> clz){
        return getModel(clz);

    }
    protected void onCreate(Context context){
        this.context =context;
    }


}
