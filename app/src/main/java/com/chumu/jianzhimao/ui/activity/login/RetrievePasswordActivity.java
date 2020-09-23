package com.chumu.jianzhimao.ui.activity.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chumu.dt.v24.magicbox.klog.ChuMuKLog;
import com.chumu.dt.v24.magicbox.klog.ChuMuKLogUtil;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.ResponseBody;

import static com.example.common_base.ApiConfig.GET_V_CODE;
import static com.example.common_base.ApiConfig.USER_LOGIN;

public class RetrievePasswordActivity extends BaseMvpActivity<UserModle> {


    @BindView(R.id.bt_login)
    Button mBtLogin;
    @BindView(R.id.vi_noe)
    View mViNoe;
    @BindView(R.id.vi_tow)
    View mViTow;
    @BindView(R.id.ed_phone)
    EditText mEdPhone;
    @BindView(R.id.ed_verification_code)
    EditText mEdVerificationCode;
    @BindView(R.id.tv_count_down)
    TextView mTvCountDown;
    @BindView(R.id.tv_free_login)
    TextView mTvFreeLogin;
    @BindView(R.id.tv_getcode)
    TextView mTvGetcode;
    @BindView(R.id.Verification_code_login_tv)
    TextView mVerificationCodeLoginTv;
    private CountDownTimer mCountDownTimer;
    private String mPhone;

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

        ResponseBody str = (ResponseBody) t[0];
        switch (whichApi) {

            default:
                break;

            case GET_V_CODE:
                try {
                    JSONObject jsonObject = new JSONObject(str.string());
                    if (jsonObject.optInt("code") == 200) {
                        mTvGetcode.setClickable(false);
                        mTvGetcode.setVisibility(View.GONE);
                        mTvCountDown.setVisibility(View.VISIBLE);
                        mCountDownTimer = new CountDownTimer(60000, 1000) {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onTick(long l) {
                                mTvCountDown.setText(String.valueOf(l / 1000) + "s");
                            }

                            @Override
                            public void onFinish() {
                                mTvGetcode.setClickable(true);
                                mTvGetcode.setVisibility(View.VISIBLE);
                                mTvCountDown.setVisibility(View.GONE);
                                if (mCountDownTimer != null) {
                                    mCountDownTimer = null;
                                }

                            }
                        }.start();
                        showToast("验证码发送成功");
                    } else {
                        showToast(jsonObject.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

        }
    }

    @OnClick({R.id.bt_login, R.id.tv_free_login, R.id.tv_getcode, R.id.Verification_code_login_tv})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_login:
                GoToLogin();
                finish();
                break;
            case R.id.tv_free_login:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            case R.id.tv_getcode:

                GoToGetSms();
                break;
            case R.id.Verification_code_login_tv:
                startActivity(new Intent(this, RegisterAndPhoneLoginActivity.class));
                finish();
                break;
        }
    }


    private void GoToGetSms() {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0-9])|(16[013678])|(19[0136789]))\\d{8}$";
        String phone = mEdPhone.getText().toString().trim();

        if (phone.length() != 11) {
            Toast.makeText(this, "手机号长度为11位", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (isMatch) {
                show();
                mPresenter.getData(ApiConfig.GET_V_CODE, phone);
            } else {
                showToast("正确输入手机号");
            }

        }

    }






    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }

    }

    private void GoToLogin() {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0-9])|(16[013678])|(19[0136789]))\\d{8}$";
        mPhone = mEdPhone.getText().toString().trim();
        String smsCode = mEdVerificationCode.getText().toString().trim();
        if (mPhone.length() != 11) {
            showToast("手机号长度为11位");
            return;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(mPhone);
            boolean isMatch = m.matches();
            if (isMatch) {
                if (!TextUtils.isEmpty(smsCode)) {
                    mPresenter.getData(USER_LOGIN, mPhone, "chumuya", AppConfig.User.Verification_Code_login,smsCode);
                } else {
                    showToast("验证码不能为空");
                }
            } else {
                showToast("正确输入手机号");
            }
        }
    }
}
