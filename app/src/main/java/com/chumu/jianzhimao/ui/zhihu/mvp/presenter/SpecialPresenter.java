package com.chumu.jianzhimao.ui.zhihu.mvp.presenter;


import com.chumu.jianzhimao.ui.zhihu.base.BaseCallback;
import com.chumu.jianzhimao.ui.zhihu.base.BasePresenter;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanSpecial;
import com.chumu.jianzhimao.ui.zhihu.mvp.contract.SpecialContract;
import com.chumu.jianzhimao.ui.zhihu.mvp.model.SpecialModel;

import java.util.List;

public class SpecialPresenter extends BasePresenter<SpecialModel, SpecialContract.View> implements SpecialContract.Presenter {


    @Override
    public void getDataP(int id) {
        mModel.getDataM( id, new BaseCallback<List<BeanSpecial.StoriesBean>>() {
            @Override
            public void onSucceess(List<BeanSpecial.StoriesBean> data) {
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
