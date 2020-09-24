package com.chumu.jianzhimao.ui.mvp.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.common_base.ChuMuData;

import java.util.List;

/**
 * JianZhiMao
 * <p>
 * Created by ChuMu on 2020/9/24
 * Copyright © 2020年 ChuMu. All rights reserved.
 * <p>
 * Describe: 首页列表
 */

public class HomeChildInfo extends ChuMuData implements MultiItemEntity {
    public static final int PAGE_TYPE_BANNER = 0;
    public static final int PAGE_TYPE_LIST = 1;

    private int ItemType;

    private List<BeanBannerList.DataBean> banner;
    private BeanHomeList.DataBean.RowsBean homeData;


    public HomeChildInfo(int itemType, List<BeanBannerList.DataBean> banner) {
        ItemType = itemType;
        this.banner = banner;

    }

    public HomeChildInfo(int itemType, BeanHomeList.DataBean.RowsBean homeData) {
        ItemType = itemType;
        this.homeData = homeData;
    }

    public void setItemType(int itemType) {
        ItemType = itemType;
    }

    public List<BeanBannerList.DataBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BeanBannerList.DataBean> banner) {
        this.banner = banner;
    }

    public BeanHomeList.DataBean.RowsBean getHomeData() {
        return homeData;
    }

    public void setHomeData(BeanHomeList.DataBean.RowsBean homeData) {
        this.homeData = homeData;
    }

    @Override
    public String toString() {
        return "BeanHomeInfo{" +
                "ItemType=" + ItemType +
                ", banner=" + banner +
                ", homeData=" + homeData +
                '}';
    }

    @Override
    public int getItemType() {
        return ItemType;
    }

}
