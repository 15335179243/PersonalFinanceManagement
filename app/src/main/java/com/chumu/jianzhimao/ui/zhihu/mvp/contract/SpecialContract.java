package com.chumu.jianzhimao.ui.zhihu.mvp.contract;


import com.chumu.jianzhimao.ui.zhihu.base.BaseCallback;
import com.chumu.jianzhimao.ui.zhihu.base.BaseView;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanSpecial;

import java.util.List;

public interface SpecialContract {
   interface Model {
           void getDataM(int id, BaseCallback<List<BeanSpecial.StoriesBean>> callBack);
       }

       interface View extends BaseView<List<BeanSpecial.StoriesBean>> {

       }

       interface Presenter  {
           void getDataP(int id);
       }
}
