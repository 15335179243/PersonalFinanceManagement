package com.chumu.jianzhimao.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chumu.jianzhimao.R;


import org.jetbrains.annotations.NotNull;

/**
 * JianZhiMao
 * <p>
 * Created by ChuMu on 2020/9/25
 * Copyright © 2020年 ChuMu. All rights reserved.
 * <p>
 * Describe:
 */

public class HomeChildTypeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public HomeChildTypeAdapter(int layoutId) {
        super(layoutId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, String item) {
        holder.setText(R.id.home_type_tv,item);

    }
}
