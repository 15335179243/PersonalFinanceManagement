package com.chumu.jianzhimao.ui.activity.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
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


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;

import static com.example.common_base.ApiConfig.FIND_PASSWORD;
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
        getTitleView().mBackBtn.setVisibility(View.GONE);
        mMobile = getIntent().getStringExtra("userName");

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

            case FIND_PASSWORD:
                BeanLogin beanLogin = new Gson().fromJson(str, BeanLogin.class);
                ToastUtil.toastShortMessage(beanLogin.getDesc());
                if (beanLogin.getCode() == 200) {
                    if (beanLogin.getData().getToken() != null) {
                        mChuMuSharedPreferences.putValue(SPConstant.Login.TOKEN, beanLogin.getData().getToken());
                    }
                    if (beanLogin.getData().getNickName() != null) {
                        mChuMuSharedPreferences.putValue(SPConstant.Login.NICKNAME, beanLogin.getData().getNickName());
                    }
                    if (beanLogin.getData().getHeadPhoto() != null) {
                        mChuMuSharedPreferences.putValue(SPConstant.Login.HEAD_PICTURE, beanLogin.getData().getHeadPhoto());
                    }

                    mChuMuSharedPreferences.putValue(SPConstant.Login.moneyLimit,beanLogin.getData().getMoneyLimit());
//                    mChuMuSharedPreferences.putValue((SPConstant.Login.HEAD_PICTURE,beanLogin.getData().getMoneyLimit());
                    startActivity(new Intent(this, HomeActivity.class));
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
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("userName", mMobile);
                        jsonObject.put("nickName", "体验官");
                        jsonObject.put("headPhoto", "");
                        jsonObject.put("passWord", mEdNewPassword.getText().toString().trim());
                        Gson gson = new Gson();
                        String s = gson.toJson(jsonObject);
                        String o = (String) JSON.toJSON(jsonObject.toString());
                        Log.e("chumu", "GoToLogin: j" + o);
                        mPresenter.getData(FIND_PASSWORD, o);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ToastUtil.toastShortMessage("两次输入不一致");

                }
                break;
        }
    }
}
