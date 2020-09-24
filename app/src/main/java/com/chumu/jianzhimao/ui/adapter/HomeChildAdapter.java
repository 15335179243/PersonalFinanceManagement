package com.chumu.jianzhimao.ui.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.fragment.HomeChildFragment;
import com.chumu.jianzhimao.ui.mvp.bean.HomeChildInfo;


import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.chumu.jianzhimao.ui.mvp.bean.HomeChildInfo.PAGE_TYPE_BANNER;
import static com.chumu.jianzhimao.ui.mvp.bean.HomeChildInfo.PAGE_TYPE_LIST;

/**
 * JianZhiMao
 * <p>
 * Created by ChuMu on 2020/9/15
 * Copyright © 2020年 ChuMu. All rights reserved.
 * <p>
 * Describe:
 */

public class HomeChildAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private int PageType;

    public HomeChildAdapter() {

        addItemType(PAGE_TYPE_BANNER, R.layout.home_child_banner_itme);

        addItemType(PAGE_TYPE_LIST, R.layout.home_child_list_itme);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder holder, MultiItemEntity multiItemEntiy) {
        switch (holder.getItemViewType()) {
            default:
                break;
            case PAGE_TYPE_LIST:


                break;
            case PAGE_TYPE_BANNER:
                break;


        }

    }


}
