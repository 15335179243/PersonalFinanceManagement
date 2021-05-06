package com.chumu.jianzhimao.ui.zhihu.mvp.contract;

import com.chumu.jianzhimao.ui.zhihu.base.BaseCallback;
import com.chumu.jianzhimao.ui.zhihu.base.BaseView;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanDetails;


public interface DetailsContract {
    interface Model {
            void getDataM(int id, BaseCallback<BeanDetails> callBack);
        }

        interface View extends BaseView<BeanDetails> {

        }

        interface Presenter {
            void getDataP(int id);
        }
}
