package com.yanyuanquan.gank.main.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;


import com.yanyuanquan.android.automvp.annotation.Presenter;
import com.yanyuanquan.gank.Image.activity.ImagePagerActivity;
import com.yanyuanquan.gank.R;
import com.yanyuanquan.gank.entity.GirlBean;
import com.yanyuanquan.gank.main.adapter.WelfareAdapter;
import com.yanyuanquan.gank.main.model.GankCategory;
import com.yanyuanquan.gank.main.presenter.WelfarePresenter;
import com.yanyuanquan.gank.main.view.IWelfareView;
import com.yanyuanquan.gank.util.animator.recyclerview.adapter.AlphaAnimatorAdapter;
import com.yanyuanquan.gank.util.animator.recyclerview.itemanimator.SlideInOutBottomItemAnimator;
import com.yanyuanquan.gank.web.WebActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
@Presenter(WelfarePresenter.class)
public class WelfareListFragment extends LazyFragment<WelfarePresenter> implements IWelfareView<GirlBean> {

    public static final String BUNDLE_KEY_TYPE="BUNDLE_KEY_TYPE";
    @Bind(R.id.recycle_view)
    RecyclerView recycleView;
    private List<GirlBean> mData = new ArrayList<>();
    private WelfareAdapter welfareAdapter;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private LinearLayoutManager mLinearLayoutManager;
    private String mType;
    private int pageIndex = 1;

    private boolean isPrepared;

    @Override
    protected void lazyLoad() {
        if((!isPrepared || !isVisible)) {
            return;
        }

        if(mData.isEmpty()){
            initView();
            onRefresh();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_welfare_list;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化view的各控件
        isPrepared = true;
        lazyLoad();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mType = args.getString(BUNDLE_KEY_TYPE);
        }
    }

    private void initView() {
        recycleView.setHasFixedSize(true);
        recycleView.setItemAnimator(new SlideInOutBottomItemAnimator(recycleView));
        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(mType.equals(GankCategory.福利.name())){
                    int[] positions = new int[mStaggeredGridLayoutManager.getSpanCount()];
                    mStaggeredGridLayoutManager.findLastVisibleItemPositions(positions);
                    for (int position : positions) {
                        lastVisibleItem=position;
                        break;
                    }
                }else {
                    lastVisibleItem =mLinearLayoutManager.findLastVisibleItemPosition();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == welfareAdapter.getItemCount()
                        && welfareAdapter.isShowFooter()) {
                    presenter.loadNews(mType, pageIndex );
                }
            }
        });
    }

    private WelfareAdapter.OnItemClickListener mOnItemClickListener = new WelfareAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            final ActivityOptionsCompat options;

            if (Build.VERSION.SDK_INT >= 21) {
                options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(), view,  welfareAdapter.getItem(position).get_id());
            } else {
                options = ActivityOptionsCompat.makeScaleUpAnimation(
                        view,
                        view.getWidth()/2, view.getHeight()/2,//拉伸开始的坐标
                        0, 0);//拉伸开始的区域大小，这里用（0，0）表示从无到全屏
            }

            if(mType.equals(GankCategory.福利.name())){

                ActivityCompat.startActivity(getActivity(), ImagePagerActivity.newIntent(view.getContext(),position,welfareAdapter.getData()),options.toBundle());
            }else {
                GirlBean resultsBean = welfareAdapter.getItem(position);

                ActivityCompat.startActivity(getActivity(), WebActivity.newIntent(view.getContext(),resultsBean.getUrl(),resultsBean.getDesc()),options.toBundle());
            }
        }
    };

    @Override
    public void onRefresh() {
        pageIndex=1;
        if(mData != null) {
            mData.clear();
        }
        presenter.loadNews(mType,pageIndex);
    }


    @Override
    public void addData(List<GirlBean> girlList) {
        mData.addAll(girlList);
        if(pageIndex ==1) {
            welfareAdapter = new WelfareAdapter(getActivity().getApplicationContext(),mType,mData);
            if(mType.equals(GankCategory.福利.name())){
                mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                recycleView.setLayoutManager(mStaggeredGridLayoutManager);
                recycleView.setAdapter(welfareAdapter);
            }  else {
                mLinearLayoutManager = new LinearLayoutManager(getActivity());
                recycleView.setLayoutManager(mLinearLayoutManager);
                AlphaAnimatorAdapter animatorAdapter = new AlphaAnimatorAdapter(welfareAdapter, recycleView);
                recycleView.setAdapter(animatorAdapter);
            }

            welfareAdapter.setOnItemClickListener(mOnItemClickListener);
        } else {
            //如果没有更多数据了,则隐藏footer布局
            if(girlList == null || girlList.size() == 0) {
                welfareAdapter.isShowFooter(false);
            }

            welfareAdapter.setDate(mData);
            welfareAdapter.notifyDataSetChanged();
//            int count=welfareAdapter.getItemCount();
//            for (int i=count;i< mData.size();i++) {
//                welfareAdapter.add(mData.get(i),i);
//            }
        }
        pageIndex += 1;
    }


    @Override
    public void showLoadFailMsg() {
        if(pageIndex == 0) {
            welfareAdapter.isShowFooter(false);
            welfareAdapter.notifyDataSetChanged();
        }
        View view = getActivity() == null ? recycleView.getRootView() : getActivity().findViewById(R.id.coordinator_layout);
        Snackbar.make(view, getString(R.string.load_fail), Snackbar.LENGTH_SHORT).show();
    }
}
