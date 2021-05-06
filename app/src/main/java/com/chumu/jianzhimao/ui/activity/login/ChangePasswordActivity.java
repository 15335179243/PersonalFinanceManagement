package com.chumu.jianzhimao.ui.activity.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chumu.dt.v24.magicbox.swipeback.ChuMuSwipeBack;
import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.activity.HomeActivity;
import com.chumu.jianzhimao.ui.mvp.UserModle;
import com.chumu.jianzhimao.ui.mvp.bean.BeanLogin;
import com.example.common_base.ApiConfig;
import com.example.common_base.AppConfig;
import com.example.common_base.SPConstant;
import com.example.common_base.base.BaseMvpActivity;
import com.example.common_base.base.NotSignException;
import com.example.common_base.utils.ToastUtil;
import com.google.gson.Gson;


import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;

import static com.example.common_base.ApiConfig.UPDATE_PASSWORD;
import static com.example.common_base.ApiConfig.USER_LOGIN;

@ChuMuSwipeBack(true)
public class ChangePasswordActivity extends BaseMvpActivity<UserModle> {


    @BindView(R.id.ed_old_password)
    EditText mEdOldPassword;
    @BindView(R.id.ed_new_password)
    EditText mEdNewPassword;
    @BindView(R.id.ed_retype)
    EditText mEdRetype;
    @BindView(R.id.btn_confirm)
    Button mBtnConfirm;
    private String mMobile;


    @Override
    protected int onCreateContentView() {
        return R.layout.activity_change_password;
    }

    @Override
    public void initView() {
        mMobile = (String) mChuMuSharedPreferences.getValue(SPConstant.Login.MOBILE, "");
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
        hide();
        String str = (String) t[0];
        switch (whichApi) {
            default:
                break;
            case UPDATE_PASSWORD:
                    BeanLogin beanLogin = new Gson().fromJson(str, BeanLogin.class);

                    if (beanLogin.getCode() == 200) {

                     throw  new NotSignException("设置密码成功请重新登录");

                    }else {
                        ToastUtil.toastShortMessage(beanLogin.getDesc());
                    }

                break;

        }
    }


    @OnClick(R.id.btn_confirm)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_confirm:

                if (TextUtils.isEmpty(mEdOldPassword.getText().toString()) || TextUtils.isEmpty(mEdNewPassword.getText().toString()) || TextUtils.isEmpty(mEdRetype.getText().toString())) {
                    ToastUtil.toastShortMessage("输入不能为空");
                    return;
                }
                if (mEdNewPassword.getText().toString().trim().equals(mEdRetype.getText().toString().trim())) {
                    mPresenter.getData(UPDATE_PASSWORD, mMobile, mEdOldPassword.getText().toString(), mEdNewPassword.getText().toString().trim());
                } else {
                    ToastUtil.toastShortMessage("两次输入不一致");

                }
                break;
        }
    }


}
