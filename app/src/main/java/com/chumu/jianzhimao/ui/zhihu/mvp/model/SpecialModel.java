package com.chumu.jianzhimao.ui.zhihu.mvp.model;




import com.chumu.jianzhimao.ui.zhihu.ApiService;
import com.chumu.jianzhimao.ui.zhihu.DataService;
import com.chumu.jianzhimao.ui.zhihu.base.BaseCallback;
import com.chumu.jianzhimao.ui.zhihu.base.BaseModel;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanSpecial;
import com.chumu.jianzhimao.ui.zhihu.mvp.contract.SpecialContract;
import com.chumu.jianzhimao.ui.zhihu.net.net.BaseObserver;
import com.chumu.jianzhimao.ui.zhihu.net.net.RxUtils;

import java.util.List;

import io.reactivex.disposables.Disposable;



public class SpecialModel extends BaseModel implements SpecialContract.Model {
    @Override
    public void getDataM( int id, final BaseCallback<List<BeanSpecial.StoriesBean>> callBack) {
        DataService.getData(ApiService.Apizhihu).getDataNewSecial(id)

                .compose(RxUtils.<BeanSpecial>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BeanSpecial>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BeanSpecial beanSpecial) {
                        List<BeanSpecial.StoriesBean> recent = beanSpecial.getStories();
                        if (recent.size()>0) {
                            callBack.onSucceess(recent);
                        }else {
                            callBack.onFailed("请求失败");
                        }
                    }
                }) ;
    }
}
