package com.chumu.jianzhimao.ui.zhihu.mvp.contract;


import com.chumu.jianzhimao.ui.zhihu.base.BaseCallback;
import com.chumu.jianzhimao.ui.zhihu.base.BaseView;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanNewsLatest;

public interface DailyContract {
    interface Model {
        void getDataM(BaseCallback<BeanNewsLatest> callBack);
    }

    interface View extends BaseView<BeanNewsLatest> {
    }

    interface Presenter {
        void getDataP();
    }

}
