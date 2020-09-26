package com.chumu.jianzhimao.ui.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.mvp.bean.BeanBannerList;
import com.chumu.jianzhimao.ui.mvp.bean.HomeChildInfo;
import com.example.common_base.utils.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import org.jetbrains.annotations.NotNull;

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

public class HomeChildAdapter extends BaseMultiItemQuickAdapter<HomeChildInfo, BaseViewHolder> {


    private LifecycleOwner mLifecycleOwner;

    public HomeChildAdapter(LifecycleOwner lifecycleOwner) {
        super(null);
        mLifecycleOwner = lifecycleOwner;
        addItemType(PAGE_TYPE_BANNER, R.layout.home_child_banner_itme);
        addItemType(PAGE_TYPE_LIST, R.layout.home_child_list_itme);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, HomeChildInfo item) {
        switch (holder.getItemViewType()) {
            default:
                break;
            case PAGE_TYPE_LIST:
                Glide.with(holder.itemView.getContext()).load(item.getHomeData().getHeadPhoto())
                        .error(R.drawable.common_base_no_login_head)
                        .placeholder(R.drawable.common_base_no_login_head)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into((ImageView) holder.getView(R.id.img_head));
                if (!TextUtils.isEmpty(item.getHomeData().getPicture())) {
                    Glide.with(holder.itemView.getContext()).load(item.getHomeData().getPicture()).into((ImageView) holder.getView(R.id.for_larger_iv));
                } else {
                    holder.setGone(R.id.for_larger_iv, true);
                }
                if (item.getHomeData().getLabelList() != null && item.getHomeData().getLabelList().size() > 0) {
                    RecyclerView typeRlv = holder.getView((R.id.type_rlv));
                    typeRlv.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), RecyclerView.HORIZONTAL, false));
                    HomeChildTypeAdapter homeChildTypeAdapter = new HomeChildTypeAdapter(R.layout.home_child_list_type_itme);
                    typeRlv.setAdapter(homeChildTypeAdapter);
                    homeChildTypeAdapter.setNewData(item.getHomeData().getLabelList());
                }
                holder.setText(R.id.tv_title, item.getHomeData().getName())
                        .setText(R.id.tv_monmey_everyday, item.getHomeData().getDigest())
                        .setText(R.id.content_tv, item.getHomeData().getContact())
                        .setText(R.id.tv_fenxiang_num, String.valueOf(item.getHomeData().getShareNum()))
                        .setText(R.id.tv_shoucang_num, String.valueOf(item.getHomeData().getCollectNum()))
                        .setText(R.id.tv_service_name, item.getHomeData().getServiceName())
                        .setText(R.id.tv_days, item.getHomeData().getCreateDate())
                        .addOnClickListener(R.id.img_shoucang)
                        .addOnClickListener(R.id.img_fenxiang)
                        .addOnClickListener(R.id.item_cl);
                ImageView favorite = holder.getView(R.id.img_shoucang);
                favorite.setSelected(item.getHomeData().isHaveCollect());


                break;
            case PAGE_TYPE_BANNER:
                Banner banner = holder.getView(R.id.banner);

                banner.setAdapter(new BannerImageAdapter<BeanBannerList.DataBean>(item.getBanner()) {
                    @Override
                    public void onBindView(BannerImageHolder holder, BeanBannerList.DataBean data, int position, int size) {
                        //图片加载自己实现
                        Glide.with(holder.itemView)
                                .load(data.getUrl())
                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                                .into(holder.imageView);
                    }
                }).addBannerLifecycleObserver(mLifecycleOwner)//添加生命周期观察者
                        .setIndicator(new CircleIndicator(holder.itemView.getContext())).setOnBannerListener(new OnBannerListener<BeanBannerList.DataBean>() {
                    @Override
                    public void OnBannerClick(BeanBannerList.DataBean data, int position) {
                        ToastUtil.toastShortMessage(data.getLink());
                    }
                });
                break;


        }

    }


}
