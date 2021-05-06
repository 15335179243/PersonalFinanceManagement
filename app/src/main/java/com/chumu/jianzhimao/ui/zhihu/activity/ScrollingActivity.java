package com.chumu.jianzhimao.ui.zhihu.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.zhihu.ApiService;
import com.chumu.jianzhimao.ui.zhihu.base.BaseModel;
import com.chumu.jianzhimao.ui.zhihu.base.BaseMvpActivity;
import com.chumu.jianzhimao.ui.zhihu.base.BaseView;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanDetails;
import com.chumu.jianzhimao.ui.zhihu.mvp.contract.DetailsContract;
import com.chumu.jianzhimao.ui.zhihu.mvp.model.DetailsModel;
import com.chumu.jianzhimao.ui.zhihu.mvp.presenter.DetailsPresenter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;


public class ScrollingActivity extends BaseMvpActivity<DetailsPresenter, DetailsModel, DetailsContract.View> implements DetailsContract.View {
    private static final String TAG = "ScrollingActivity";
    @BindView(R.id.img)
    ImageView mImg;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    private int mId;

    @Override
    protected int getlayoutViewID() {
        return R.layout.activity_scrolling;
    }

    @Override
    protected BaseModel initMvpmodel() {
        return new DetailsModel();
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mId = intent.getIntExtra("id", 0);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setSupportActionBar(mToolbar);
        mWebview.setWebViewClient(new WebViewClient());

    }

    @Override
    protected BaseView initMvpView() {
        return this;
    }

    @Override
    protected DetailsPresenter initMvpPresenter() {
        return new DetailsPresenter();
    }

    @Override
    protected void initData() {
        mPresenter.getDataP(mId);
    }

    @Override
    public void onSuccess(BeanDetails datas) {
        mWebview.loadDataWithBaseURL(ApiService.Apizhihu,datas.getBody(), "text/html", "utf-8", null);
        mToolbarLayout.setTitle(datas.getTitle());
        Glide.with(ScrollingActivity.this).load(datas.getImage()).into(mImg);
    }

    @Override
    public void onFail(String msg) {
        Log.d(TAG, "onFail: "+msg);
    }




}
