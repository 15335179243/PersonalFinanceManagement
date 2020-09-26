package com.chumu.jianzhimao.ui.activity.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.activity.HomeActivity;
import com.chumu.jianzhimao.ui.mvp.UserModle;
import com.chumu.jianzhimao.ui.mvp.bean.BeanLogin;
import com.example.common_base.ApiConfig;
import com.example.common_base.AppConfig;
import com.example.common_base.SPConstant;
import com.example.common_base.base.BaseMvpActivity;
import com.example.common_base.utils.ToastUtil;
import com.google.gson.Gson;
import com.tanrice.unmengapptrack.UMengInit;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;

import static com.example.common_base.ApiConfig.USER_LOGIN;
import static com.example.common_base.ApiConfig.USER_Set_PASSWORD_LOGIN;

public class SetPasswordActivity extends BaseMvpActivity<UserModle> {


    @BindView(R.id.ed_password)
    EditText mEdNewPassword;
    @BindView(R.id.ed_retype)
    EditText mEdRetype;
    @BindView(R.id.btn_confirm)
    Button mBtnConfirm;
    private String mMobile;
    private String v_Code;


    @Override
    protected int onCreateContentView() {
        return R.layout.activity_set_password;
    }

    @Override
    public void initView() {
        mMobile = getIntent().getStringExtra(SPConstant.Login.MOBILE);
        v_Code = getIntent().getStringExtra(SPConstant.Login.V_CODE);
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

            case USER_Set_PASSWORD_LOGIN:
                    BeanLogin beanLogin = new Gson().fromJson(str, BeanLogin.class);
                        ToastUtil.toastShortMessage(beanLogin.getDesc());
                    if (beanLogin.getCode() == 200) {
                            mChuMuSharedPreferences.putObject(SPConstant.Login.HEAD_PICTURE, beanLogin.getData().getHeadPicture());
                            mChuMuSharedPreferences.putObject(SPConstant.Login.MOBILE, beanLogin.getData().getMobile());
                            mChuMuSharedPreferences.putObject(SPConstant.Login.NICKNAME, beanLogin.getData().getNickName());
                            mChuMuSharedPreferences.putObject(SPConstant.Login.SIGNATURE, beanLogin.getData().getSignature());
                            mChuMuSharedPreferences.putObject(SPConstant.Login.TOKEN, beanLogin.getData().getToken());
                            mChuMuSharedPreferences.putObject(SPConstant.Login.TOKEN, beanLogin.getData().getToken());
                            mChuMuSharedPreferences.putObject(SPConstant.Login.ID, beanLogin.getData().getId());
                            startActivity(new Intent(this, HomeActivity.class));
                            finish();
                    } else {
                        startActivity(new Intent(this, RegisterAndPhoneLoginActivity.class));
                        finish();
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
                if (mEdNewPassword.getText() == null || mEdRetype.getText() == null) {
                    ToastUtil.toastShortMessage("请输入密码");
                    return;
                }
                if (mEdNewPassword.getText().toString().trim().equals(mEdRetype.getText().toString().trim())) {
                    mPresenter.getData(USER_Set_PASSWORD_LOGIN,mMobile, mEdNewPassword.getText().toString().trim(), UMengInit.getIntChannel(), AppConfig.User.register,v_Code);
                } else {
                    ToastUtil.toastShortMessage("两次输入不一致");

                }
                break;
        }
    }
}
