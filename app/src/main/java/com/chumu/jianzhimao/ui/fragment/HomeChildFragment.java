package com.chumu.jianzhimao.ui.fragment;

import androidx.recyclerview.widget.RecyclerView;

import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.adapter.HomeChildAdapter;
import com.chumu.jianzhimao.ui.mvp.HomeModle;
import com.chumu.jianzhimao.ui.mvp.bean.HomeChildInfo;
import com.example.common_base.base.BaseMvpFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;

/**
 * JianZhiMao
 * <p>
 * Created by ChuMu on 2020/9/6
 * Copyright © 2020年 ChuMu. All rights reserved.
 * <p>
 * Describe:
 */

public class HomeChildFragment extends BaseMvpFragment<HomeModle> {

    @BindView(R.id.list_recyclerview)
    RecyclerView mListRecyclerview;
    @BindView(R.id.sm_refres)
    SmartRefreshLayout mSmRefres;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_child;
    }

    @Override
    public void initView() {
        initRecycleView(mListRecyclerview, mSmRefres);
        HomeChildAdapter homeChildAdapter = new HomeChildAdapter(HomeChildInfo.PAGE_TYPE_BANNER);
        mListRecyclerview.setAdapter(homeChildAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void refresh() {
        super.refresh();
    }

    @Override
    public void loadMore() {
        super.loadMore();

    }

    @Override
    public HomeModle getModel() {
        return new HomeModle();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

}
