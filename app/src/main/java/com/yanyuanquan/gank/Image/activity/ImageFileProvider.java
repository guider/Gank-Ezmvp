package com.yanyuanquan.gank.Image.activity;

import android.net.Uri;
import android.support.v4.content.FileProvider;


public class ImageFileProvider extends FileProvider {

    @Override
    public String getType(Uri uri) {
        return "image/jpeg";
    }

}

