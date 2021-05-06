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
import com.chumu.jianzhimao.ui.zhihu.bean.BeanHot;

import java.util.ArrayList;
import java.util.List;


public class RlvHotAdapter extends RecyclerView.Adapter {
    private Context mContext;
    public ArrayList<BeanHot.RecentBean> mList = new ArrayList<>();
    private OnClickListener mOnClickListener;
    private OnLongClickListener mOnLongClickListener;

    public RlvHotAdapter(Context context) {
        mContext = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_zhihu_daily, viewGroup, false);
        return new ViewHolderCV(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHolderCV holder = (ViewHolderCV) viewHolder;
        Glide.with(mContext).load(mList.get(i).getThumbnail()).into(holder.mImg);
        holder.mTvTitle.setText(mList.get(i).getTitle());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(v, i);
                }
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnLongClickListener != null) {
                    mOnLongClickListener.onLongClick(v, i);
                }
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addlist(List<BeanHot.RecentBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
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
