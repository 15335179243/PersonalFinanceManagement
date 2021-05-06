package com.chumu.jianzhimao.ui.zhihu.mvp.presenter;

import com.chumu.jianzhimao.ui.zhihu.base.BaseCallback;
import com.chumu.jianzhimao.ui.zhihu.base.BasePresenter;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanNewsLatest;
import com.chumu.jianzhimao.ui.zhihu.mvp.contract.DailyContract;
import com.chumu.jianzhimao.ui.zhihu.mvp.model.DailyModel;

public class DailyPresenter extends BasePresenter<DailyModel, DailyContract.View> implements DailyContract.Presenter {
    @Override
    public void getDataP() {
        mModel.getDataM(new BaseCallback<BeanNewsLatest>() {
            @Override
            public void onSucceess(BeanNewsLatest data) {
                if (mView!=null){
                    mView.onSuccess(data);
                }
            }

            @Override
            public void onFailed(String errre) {
                if (mView!=null){
                    mView.onFail(errre);
                }
            }
        });
    }
}
