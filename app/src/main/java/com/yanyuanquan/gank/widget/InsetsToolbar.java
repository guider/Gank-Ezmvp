package com.yanyuanquan.gank.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.yanyuanquan.gank.R;
import com.yanyuanquan.gank.view.WindowInsetsHandler;


public class InsetsToolbar extends Toolbar implements WindowInsetsHandler {

    public InsetsToolbar(Context context) {
        this(context, null);
    }

    public InsetsToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.toolbarStyle);
    }

    public InsetsToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ViewCompat.setOnApplyWindowInsetsListener(this, new android.support.v4.view.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                final int l = insets.getSystemWindowInsetLeft();
                final int t = insets.getSystemWindowInsetTop();
                final int r = insets.getSystemWindowInsetRight();
                setPadding(l, t, r, 0);
                return insets.consumeSystemWindowInsets();
            }
        });
    }

    @Override
    public boolean onApplyWindowInsets(Rect insets) {
        setPadding(insets.left, insets.top, insets.right, 0);
        return true;
    }

}