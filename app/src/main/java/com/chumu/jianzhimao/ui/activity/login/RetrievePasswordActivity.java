package com.chumu.jianzhimao.ui.activity.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.mvp.UserModle;
import com.example.common_base.base.BaseMvpActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RetrievePasswordActivity extends BaseMvpActivity<UserModle> {





    @Override
    protected int onCreateContentView() {
        return R.layout.activity_retrieve_password;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public UserModle getModel() {
        return new UserModle();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }




}
