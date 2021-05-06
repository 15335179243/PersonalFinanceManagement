package com.chumu.jianzhimao.ui.zhihu.base;


public interface BaseView<T>{
    void onSuccess(T datas);
    void onFail(String msg);
}
