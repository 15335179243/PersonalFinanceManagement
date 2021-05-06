package com.chumu.jianzhimao.ui.zhihu.mvp.model;


import com.chumu.jianzhimao.ui.zhihu.ApiService;
import com.chumu.jianzhimao.ui.zhihu.DataService;
import com.chumu.jianzhimao.ui.zhihu.base.BaseCallback;
import com.chumu.jianzhimao.ui.zhihu.base.BaseModel;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanSecialColumn;
import com.chumu.jianzhimao.ui.zhihu.mvp.contract.SpecialColumnContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SpecialColumnModel extends BaseModel implements SpecialColumnContract.Model {
    @Override
    public void getDataM(final BaseCallback<List<BeanSecialColumn.DataBean>> callBack) {
        DataService.getData(ApiService.Apizhihu)
                .getDataSecialColumn()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BeanSecialColumn>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BeanSecialColumn beanSecialColumn) {
                        if (beanSecialColumn!=null&&beanSecialColumn.getData().size()>0) {
                            callBack.onSucceess(beanSecialColumn.getData());
                        }else {
                            callBack.onFailed("加载数据失败");
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
