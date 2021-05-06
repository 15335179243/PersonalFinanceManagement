package com.chumu.jianzhimao.ui.zhihu.mvp.model;

import com.chumu.jianzhimao.ui.zhihu.ApiService;
import com.chumu.jianzhimao.ui.zhihu.DataService;
import com.chumu.jianzhimao.ui.zhihu.base.BaseCallback;
import com.chumu.jianzhimao.ui.zhihu.base.BaseModel;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanNewsLatest;
import com.chumu.jianzhimao.ui.zhihu.mvp.contract.DailyContract;
import com.google.gson.Gson;


import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class DailyModel extends BaseModel implements DailyContract.Model {
    @Override
    public void getDataM(final BaseCallback<BeanNewsLatest> callBack) {
        DataService.getData(ApiService.Apizhihu).getDataDaily()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            if (string!=null) {
                                if (string.length()>0){
                                    BeanNewsLatest newsLatest = new Gson().fromJson(string, BeanNewsLatest.class);
                                    callBack.onSucceess(newsLatest);
                                }
                            }
                        } catch (IOException e) {
                            callBack.onFailed(e.getMessage());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
