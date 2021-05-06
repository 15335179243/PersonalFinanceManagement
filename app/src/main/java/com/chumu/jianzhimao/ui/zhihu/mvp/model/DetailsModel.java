package com.chumu.jianzhimao.ui.zhihu.mvp.model;



import com.chumu.jianzhimao.ui.zhihu.ApiService;
import com.chumu.jianzhimao.ui.zhihu.DataService;
import com.chumu.jianzhimao.ui.zhihu.base.BaseCallback;
import com.chumu.jianzhimao.ui.zhihu.base.BaseModel;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanDetails;
import com.chumu.jianzhimao.ui.zhihu.mvp.contract.DetailsContract;
import com.chumu.jianzhimao.ui.zhihu.net.net.BaseObserver;
import com.chumu.jianzhimao.ui.zhihu.net.net.RxUtils;

import io.reactivex.disposables.Disposable;



public class DetailsModel extends BaseModel implements DetailsContract.Model {
    @Override
    public void getDataM( int id, final BaseCallback<BeanDetails> callBack) {
        DataService.getData(ApiService.Apizhihu).getDataNewDetails(id)

                .compose(RxUtils.<BeanDetails>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BeanDetails>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                      compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BeanDetails beanDetails) {
                        if (beanDetails.getBody()!=null){

                            callBack.onSucceess(beanDetails);
                        }else {
                            callBack.onFailed("解析失败");
                        }
                    }
                });
    }
}
