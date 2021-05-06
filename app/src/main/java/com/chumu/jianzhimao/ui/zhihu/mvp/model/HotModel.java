package com.chumu.jianzhimao.ui.zhihu.mvp.model;


import com.chumu.jianzhimao.ui.zhihu.ApiService;
import com.chumu.jianzhimao.ui.zhihu.DataService;
import com.chumu.jianzhimao.ui.zhihu.base.BaseCallback;
import com.chumu.jianzhimao.ui.zhihu.base.BaseModel;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanHot;
import com.chumu.jianzhimao.ui.zhihu.mvp.contract.HotContract;


import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HotModel extends BaseModel implements HotContract.Model {


    @Override
    public void getDataM(final BaseCallback<List<BeanHot.RecentBean>> callBack) {
        DataService.getData(ApiService.Apizhihu)
                .getDataHot()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BeanHot>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BeanHot beanHot) {
                        if (beanHot!=null&&beanHot.getRecent().size()>0) {
                            callBack.onSucceess(beanHot.getRecent());
                        }else {
                            callBack.onFailed("加载失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e.getMessage()!=null) {
                            callBack.onFailed(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
