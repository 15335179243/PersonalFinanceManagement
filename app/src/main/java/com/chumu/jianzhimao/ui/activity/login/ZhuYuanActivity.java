package com.chumu.jianzhimao.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.mvp.HomeModle;
import com.example.common_base.base.BaseMvpActivity;
import com.example.common_base.design.RoundImage;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * JianZhiMao
 * <p>
 * Created by ChuMu on 2021/5/5
 * Copyright © 2021年 ChuMu. All rights reserved.
 * <p>
 * Describe:
 */

public class ZhuYuanActivity extends BaseMvpActivity<HomeModle> {
    @BindView(R.id.img)
    RoundImage img;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_yuzhi)
    TextView tvYuzhi;
    @BindView(R.id.tv_get)
    TextView tvGet;


    @Override
    public HomeModle getModel() {
        return new HomeModle();
    }

    @Override
    protected int onCreateContentView() {
        return R.layout.activity_zhuyuan;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");

        double moneyLimit = intent.getDoubleExtra("MoneyLimit", 0.0);
        String headPhoto = intent.getStringExtra("HeadPhoto");
        double finance = intent.getDoubleExtra("Finance", 0.0);
        Glide.with(this).load(headPhoto+"")
                .error(R.drawable.common_base_no_login_head)
                .placeholder(R.drawable.common_base_no_login_head)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img);
        tvGet.setText(finance+"");
        tvYuzhi.setText(moneyLimit+"");
        tvName.setText(name+"");



    }

    @Override
    public void initData() {

    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }


}
