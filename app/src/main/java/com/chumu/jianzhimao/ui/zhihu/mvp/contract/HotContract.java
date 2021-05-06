package com.chumu.jianzhimao.ui.zhihu.mvp.contract;


import com.chumu.jianzhimao.ui.zhihu.base.BaseCallback;
import com.chumu.jianzhimao.ui.zhihu.base.BaseView;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanHot;

import java.util.List;

public interface HotContract {
    interface Model {
        void getDataM(BaseCallback<List<BeanHot.RecentBean>> callBack);
    }

    interface View extends BaseView<List<BeanHot.RecentBean>> {
    }

    interface Presenter {
        void getDataP();
    }

}
