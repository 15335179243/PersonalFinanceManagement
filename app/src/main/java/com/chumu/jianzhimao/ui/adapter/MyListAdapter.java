package com.chumu.jianzhimao.ui.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.mvp.bean.MyLisInfo;

import org.jetbrains.annotations.NotNull;

import static com.chumu.jianzhimao.ui.mvp.bean.MyLisInfo.ML_LIST_ABOUT_US;

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
        holder.setText(R.id.title_tv, myLisInfo.getItemName()).setImageResource(R.id.img_pic, myLisInfo.getResPic());
        addChildClickViewIds(R.id.con);

            holder.setGone(R.id.view_lins,myLisInfo.getItemId()==ML_LIST_ABOUT_US);




    }


}
