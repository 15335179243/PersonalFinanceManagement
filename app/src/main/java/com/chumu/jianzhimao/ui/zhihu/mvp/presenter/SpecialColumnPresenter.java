package com.chumu.jianzhimao.ui.zhihu.mvp.presenter;


import com.chumu.jianzhimao.ui.zhihu.base.BaseCallback;
import com.chumu.jianzhimao.ui.zhihu.base.BasePresenter;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanSecialColumn;
import com.chumu.jianzhimao.ui.zhihu.mvp.contract.SpecialColumnContract;
import com.chumu.jianzhimao.ui.zhihu.mvp.model.SpecialColumnModel;

import java.util.List;

public class SpecialColumnPresenter extends BasePresenter<SpecialColumnModel, SpecialColumnContract.View> implements SpecialColumnContract.Presenter {
    @Override
    public void getDataP() {
        mModel.getDataM(new BaseCallback<List<BeanSecialColumn.DataBean>>() {
            @Override
            public void onSucceess(List<BeanSecialColumn.DataBean> data) {
                if (mView!=null) {
                    mView.onSuccess(data);
                }
            }

            @Override
            public void onFailed(String errre) {
                if (mView!=null) {
                    mView.onFail(errre);
                }
            }
        });
    }
}
