package com.chumu.jianzhimao.ui.zhihu.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanNewsLatest;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class RlvDailyAdapter extends RecyclerView.Adapter {
    private Context mContext;
    public ArrayList<BeanNewsLatest.StoriesBean> mList = new ArrayList<>();
    private ArrayList<BeanNewsLatest.TopStoriesBean> mLists = new ArrayList<>();
    private OnClickListener mOnClickListener;
    private OnLongClickListener mOnLongClickListener;

    public RlvDailyAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position == 1) {
            return 1;
        } else {
            return 2;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (getItemViewType(i) == 0) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_zhihu_daily_banner, null, false);
            return new ViewHolderBanner(inflate);
        } else if (getItemViewType(i) == 1) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_zhihu_daily_text, null, false);
            return new ViewHolderText(inflate);
        } else {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_zhihu_daily, viewGroup, false);
            return new ViewHolderCV(inflate);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        if (getItemViewType(i) == 0) {
            ArrayList<String> strings = new ArrayList<>();
            for (int j = 0; j < mLists.size(); j++) {
                strings.add(mLists.get(j).getTitle());
            }
            ViewHolderBanner holderBanner = (ViewHolderBanner) viewHolder;
            holderBanner.mBanner.setImages(mLists).setBannerTitles(strings).setIndicatorGravity(BannerConfig.CENTER).setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    BeanNewsLatest.TopStoriesBean path1 = (BeanNewsLatest.TopStoriesBean) path;
                    Glide.with(context).load(path1.getImage()).into(imageView);
                }
            }).start();

        } else if (getItemViewType(i) == 1) {
            ViewHolderText holderText = (ViewHolderText) viewHolder;
            holderText.mDtodaysnews.setText(R.string.dtodaysnews);

        } else {
            ViewHolderCV holder = (ViewHolderCV) viewHolder;
            Glide.with(mContext).load(mList.get(i - 2).getImages().get(0)).into(holder.mImg);
            holder.mTvTitle.setText(mList.get(i - 2).getTitle());
        }
        if (i > 1) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnClickListener != null) {
                        mOnClickListener.onClick(v, i-2);
                    }
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mOnLongClickListener!=null) {
                        mOnLongClickListener.onLongClick(v,i-2);
                    }
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mList.size() + 2;
    }

    public void addlist(List<BeanNewsLatest.StoriesBean> stories) {
        mList.addAll(stories);
    }

    public void addlistTop(List<BeanNewsLatest.TopStoriesBean> top_stories) {
        mLists.addAll(top_stories);

    }

    class ViewHolderBanner extends RecyclerView.ViewHolder {
        Banner mBanner;

        public ViewHolderBanner(@NonNull View itemView) {
            super(itemView);
            this.mBanner = (Banner) itemView.findViewById(R.id.banner);
        }
    }

    class ViewHolderText extends RecyclerView.ViewHolder {
        TextView mDtodaysnews;

        public ViewHolderText(@NonNull View itemView) {
            super(itemView);
            this.mDtodaysnews = (TextView) itemView.findViewById(R.id.dtodaysnews);
        }
    }

    public void setOnClickListener(OnClickListener OnClickListener) {
        mOnClickListener = OnClickListener;
    }

    public interface OnClickListener {
        void onClick(View v, int i);
    }

    public void setOnLongClickListener(OnLongClickListener OnLongClickListener) {
        mOnLongClickListener = OnLongClickListener;
    }

    public interface OnLongClickListener {
        void onLongClick(View v, int i);
    }

    class ViewHolderCV extends RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mTvTitle;


        public ViewHolderCV(@NonNull View itemView) {
            super(itemView);
            this.mImg = (ImageView) itemView.findViewById(R.id.img);
            this.mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

}
