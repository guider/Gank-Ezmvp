package com.yanyuanquan.gank.Image.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;


import com.yanyuanquan.gank.Image.fragment.ImageViewFragment;
import com.yanyuanquan.gank.entity.GirlBean;

import java.util.List;


public class ImagePagerAdapter extends FragmentStatePagerAdapter {

    private ViewPager viewPager;
    private List<GirlBean> resultsBeanList;
    private int index;
    public ImagePagerAdapter(FragmentManager fm, ViewPager viewPager, List<GirlBean> resultsBeanList, int index) {
        super(fm);
        this.viewPager=viewPager;
        this.resultsBeanList=resultsBeanList;
        this.index=index;
    }

    @Override
    public int getCount() {
        return resultsBeanList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return ImageViewFragment.newFragment(
                resultsBeanList.get(position).getUrl(),
                position == index);
    }

    public ImageViewFragment getCurrent() {
        return (ImageViewFragment) instantiateItem(viewPager, viewPager.getCurrentItem());
    }
}
