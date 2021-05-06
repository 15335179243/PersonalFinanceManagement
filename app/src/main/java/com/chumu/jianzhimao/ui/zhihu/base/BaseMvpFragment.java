package com.chumu.jianzhimao.ui.zhihu.base;

public abstract class BaseMvpFragment <P extends BasePresenter, M extends BaseModel, V extends BaseView>extends BaseFramgent{
    protected P mPresenter;

    @Override
    public void initMvp() {
        mPresenter = initMvpPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(initMvpView());
            mPresenter.initModel(initMvpmodel());
        }
    }

    protected abstract BaseModel initMvpmodel();

    protected abstract BaseView initMvpView();

    protected abstract P initMvpPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter!=null) {
            mPresenter.destroy();
            mPresenter=null;
        }
    }
}
