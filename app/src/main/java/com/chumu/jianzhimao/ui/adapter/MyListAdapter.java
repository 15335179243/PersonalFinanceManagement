package com.chumu.jianzhimao.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.mvp.bean.MyLisInfo;

import org.jetbrains.annotations.NotNull;

/**
 * JianZhiMao
 * <p>
 * Created by ChuMu on 2020/9/16
 * Copyright © 2020年 ChuMu. All rights reserved.
 * <p>
 * Describe:
 */

public class MyListAdapter extends BaseQuickAdapter<MyLisInfo, BaseViewHolder> {
    public MyListAdapter() {
        super(R.layout.my_list_fagment_item);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, MyLisInfo myLisInfo) {

    }
}
