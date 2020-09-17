package com.chumu.jianzhimao.ui.mvp.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.common_base.ChuMuData;

/**
 * JianZhiMao
 * <p>
 * Created by ChuMu on 2020/9/15
 * Copyright © 2020年 ChuMu. All rights reserved.
 * <p>
 * Describe:
 */

public class HomeChildInfo extends ChuMuData implements MultiItemEntity {
    public static final int PAGE_TYPE_BANNER = 0;
    public static final int PAGE_TYPE_LIST = 1;


    private int itemType;

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return getItemType();
    }
}
