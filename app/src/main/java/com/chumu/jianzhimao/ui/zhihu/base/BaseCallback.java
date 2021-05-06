package com.chumu.jianzhimao.ui.zhihu.base;

public interface BaseCallback<T> {

    void  onSucceess(T data);
    void onFailed(String errre);
}
