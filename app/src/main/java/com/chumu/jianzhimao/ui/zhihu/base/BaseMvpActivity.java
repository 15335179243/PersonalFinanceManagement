package com.chumu.jianzhimao.ui.zhihu.base;

public abstract class BaseMvpActivity<P extends BasePresenter, M extends BaseModel, V extends BaseView> extends BaseActivity {
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
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null) {
            mPresenter.destroy();
            mPresenter=null;
        }
    }
}
