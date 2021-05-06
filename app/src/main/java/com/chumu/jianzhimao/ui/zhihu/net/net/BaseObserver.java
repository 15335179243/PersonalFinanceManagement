package com.chumu.jianzhimao.ui.zhihu.net.net;

import android.util.Log;


import com.chumu.jianzhimao.ui.utils.Logger;
import com.chumu.jianzhimao.ui.utils.ToastUtil;

import io.reactivex.Observer;

public abstract class BaseObserver<T>  implements Observer<T> {

    private static final String TAG = "BaseObserver";
    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "onError: "+e.toString());
        ToastUtil.showShort("数据加载失败");
    }

    @Override
    public void onComplete() {
        Logger.logD(TAG,"onComplete");
    }
}
