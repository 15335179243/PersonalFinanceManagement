package com.chumu.jianzhimao.ui.zhihu.base;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePresenter<M extends BaseModel,V extends BaseView>{

    public List<BaseModel> list = new ArrayList<>();
    protected M mModel;
    protected V mView;

    public void initModel(M mModel){
        this.mModel = mModel;
    }

    public void attachView(V mView){
        this.mView = mView;
    }


    public void destroy() {
        if (mView!=null){
            mView = null;
        }

        if (list!=null&&list.size()>0){
            for (BaseModel baseModel:list) {
                baseModel.clear();
            }
        }

        if (mModel!=null){
            mModel = null;
        }
    }
}
