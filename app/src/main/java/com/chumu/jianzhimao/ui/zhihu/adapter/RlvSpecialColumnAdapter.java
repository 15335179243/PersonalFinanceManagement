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
import com.chumu.jianzhimao.ui.zhihu.bean.BeanSecialColumn;

import java.util.ArrayList;
import java.util.List;

public class RlvSpecialColumnAdapter extends RecyclerView.Adapter {
    private Context mContext;
    public ArrayList<BeanSecialColumn.DataBean> mList = new ArrayList<>();
    private OnClickListener mOnClickListener;
    private OnLongClickListener mOnLongClickListener;

    public RlvSpecialColumnAdapter(Context context) {
        mContext = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_zhihu_sppc, viewGroup, false);
        return new ViewHolder(inflate);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        Glide.with(mContext).load(mList.get(i).getThumbnail()).into(holder.mImgTupian);
        holder.mTvName.setText(mList.get(i).getName());
        holder.mTvDec.setText(mList.get(i).getDescription());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(v, i);

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addlist(List<BeanSecialColumn.DataBean> list) {
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
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvName;
        TextView mTvDec;
        ImageView mImgTupian;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            this.mTvDec = (TextView) itemView.findViewById(R.id.tv_dec);
            this.mImgTupian = (ImageView) itemView.findViewById(R.id.img_tupian);
        }
    }


}
