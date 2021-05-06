package com.chumu.jianzhimao.ui.zhihu.mvp.contract;


import com.chumu.jianzhimao.ui.zhihu.base.BaseCallback;
import com.chumu.jianzhimao.ui.zhihu.base.BaseView;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanSecialColumn;

import java.util.List;

public interface SpecialColumnContract {
    interface Model {
        void getDataM(BaseCallback<List<BeanSecialColumn.DataBean>> callBack);
    }

    interface View extends BaseView<List<BeanSecialColumn.DataBean>> {
    }

    interface Presenter {
        void getDataP();
    }


}
