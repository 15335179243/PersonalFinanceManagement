package com.chumu.jianzhimao.ui.zhihu.mvp.presenter;


import com.chumu.jianzhimao.ui.zhihu.base.BaseCallback;
import com.chumu.jianzhimao.ui.zhihu.base.BasePresenter;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanHot;
import com.chumu.jianzhimao.ui.zhihu.mvp.contract.HotContract;
import com.chumu.jianzhimao.ui.zhihu.mvp.model.HotModel;

import java.util.List;

public class HotPresenter extends BasePresenter<HotModel, HotContract.View> implements HotContract.Presenter {
    @Override
    public void getDataP() {
        mModel.getDataM(new BaseCallback<List<BeanHot.RecentBean>>() {
            @Override
            public void onSucceess(List<BeanHot.RecentBean> data) {
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
