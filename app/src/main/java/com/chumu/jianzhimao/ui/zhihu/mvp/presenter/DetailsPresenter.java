package com.chumu.jianzhimao.ui.zhihu.mvp.presenter;


import com.chumu.jianzhimao.ui.zhihu.base.BaseCallback;
import com.chumu.jianzhimao.ui.zhihu.base.BasePresenter;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanDetails;
import com.chumu.jianzhimao.ui.zhihu.mvp.contract.DetailsContract;
import com.chumu.jianzhimao.ui.zhihu.mvp.model.DetailsModel;

public class DetailsPresenter extends BasePresenter<DetailsModel, DetailsContract.View> implements DetailsContract.Presenter {


    @Override
    public void getDataP(int id) {
        mModel.getDataM(id, new BaseCallback<BeanDetails>() {
            @Override
            public void onSucceess(BeanDetails data) {
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
