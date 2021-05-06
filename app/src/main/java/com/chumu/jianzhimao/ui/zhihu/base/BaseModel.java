package com.chumu.jianzhimao.ui.zhihu.base;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseModel {
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    //切换、清空容器
    public void clear(){
        compositeDisposable.clear();
    }
}
