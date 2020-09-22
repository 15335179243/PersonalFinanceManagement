package com.example.common_base.base;


import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;

public abstract class BaseMvpActivity <M> extends BaseActivity implements ICommonView {

    public CommonPresenter mPresenter;
    public M mModel;



    @Override
    public void initmvp() {
        mPresenter = getPresenter();
        mModel = getModel();
        if (mPresenter != null) mPresenter.attach(this, (ICommonModel) mModel);
    }

    public CommonPresenter getPresenter(){
        return new CommonPresenter();
    }


    public abstract M getModel();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

    public void netErrorToast(Throwable e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
