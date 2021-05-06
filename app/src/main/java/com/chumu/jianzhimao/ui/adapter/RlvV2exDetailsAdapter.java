package com.chumu.jianzhimao.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.mvp.bean.BeanHomeList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvV2exDetailsAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<BeanHomeList.DataBean.RowsBean> mList = new ArrayList<BeanHomeList.DataBean.RowsBean>();
    private OnClickListener mOnClickListener;

    public ArrayList<BeanHomeList.DataBean.RowsBean> getList() {
        return mList;
    }

    public RlvV2exDetailsAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_vtoewex_details, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        BeanHomeList.DataBean.RowsBean dataBean = mList.get(i);

        if (dataBean.getStatus()==1) {
            holder.tvJinge.setTextColor(Color.RED);
            holder.tvType.setText("支出:");
        }else {
            holder.tvType.setText("收入:");
            holder.tvJinge.setTextColor(Color.BLACK);
        }
        holder.tvJinge.setText(dataBean.getMoney()+"");
        holder.beizhu.setText(dataBean.getDesc()+"");



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addlist(final List<BeanHomeList.DataBean.RowsBean> datas) {
        if (mList!=null) {
            mList.clear();
        }
        mList.addAll(datas);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.beizhu)
        TextView beizhu;
        @BindView(R.id.tv_jinge)
        TextView tvJinge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnClickListener(OnClickListener OnClickListener) {
        mOnClickListener = OnClickListener;
    }

    public interface OnClickListener {
        void onClick(View v, int i, int j);
    }


}
