package com.yanyuanquan.gank.util;

import android.text.TextUtils;

import com.google.gson.Gson;


public class GsonUtils {

    public static Object fromJson(String jsonStr,Class clazz){
        Object object = null;
        if (!TextUtils.isEmpty(jsonStr)) {
            try {
                object = new Gson().fromJson(jsonStr, clazz);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return object;
    }

}
