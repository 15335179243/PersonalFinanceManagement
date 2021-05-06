package com.chumu.jianzhimao.ui.zhihu.Fragment;


import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.View;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.zhihu.activity.SpecialActivity;
import com.chumu.jianzhimao.ui.zhihu.adapter.RlvSpecialColumnAdapter;
import com.chumu.jianzhimao.ui.zhihu.base.BaseModel;
import com.chumu.jianzhimao.ui.zhihu.base.BaseMvpFragment;
import com.chumu.jianzhimao.ui.zhihu.base.BaseView;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanSecialColumn;
import com.chumu.jianzhimao.ui.zhihu.mvp.contract.DailyContract;
import com.chumu.jianzhimao.ui.zhihu.mvp.contract.SpecialColumnContract;
import com.chumu.jianzhimao.ui.zhihu.mvp.model.SpecialColumnModel;
import com.chumu.jianzhimao.ui.zhihu.mvp.presenter.SpecialColumnPresenter;
import com.elvishew.xlog.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * A 
 */
public class SpecialColumnFragment extends BaseMvpFragment<SpecialColumnPresenter, SpecialColumnModel, DailyContract.View> implements SpecialColumnContract.View {

    @BindView(R.id.rlv)
    RecyclerView mRlv;

    private RlvSpecialColumnAdapter mRlvSpecialColumnAdapter;
    private Context mContext;

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_zhihu_column;
    }

    @Override
    public void initView(View inflate) {
        mContext = inflate.getContext();
        mRlv.setLayoutManager(new GridLayoutManager(mContext,2));
        mRlvSpecialColumnAdapter = new RlvSpecialColumnAdapter(mContext);
        mRlv.setAdapter(mRlvSpecialColumnAdapter);
        mRlvSpecialColumnAdapter.setOnClickListener(new RlvSpecialColumnAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int i) {
                Intent intent = new Intent(getActivity(), SpecialActivity.class);
                intent.putExtra("id",mRlvSpecialColumnAdapter.mList.get(i).getId());
                intent.putExtra("title",mRlvSpecialColumnAdapter.mList.get(i).getName());
                startActivity(intent);
            }
        });

    }

    @Override
    public void initData() {
        mPresenter.getDataP();
    }

    @Override
    protected BaseModel initMvpmodel() {
        return new SpecialColumnModel();
    }

    @Override
    protected BaseView initMvpView() {
        return this;
    }

    @Override
    protected SpecialColumnPresenter initMvpPresenter() {
        return new SpecialColumnPresenter();
    }


    @Override
    public void onSuccess(List<BeanSecialColumn.DataBean> datas) {
        mRlvSpecialColumnAdapter.addlist(datas);
    }

    @Override
    public void onFail(String msg) {
        Log.d("SpecialColumnFragment",msg);
    }
}
