package com.chumu.jianzhimao.ui.activity.login;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.chumu.dt.v24.magicbox.klog.ChuMuKLog;
import com.chumu.dt.v24.magicbox.klog.ChuMuKLogUtil;
import com.chumu.dt.v24.magicbox.klog.base.ChuMuBaseLog;
import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.activity.HomeActivity;
import com.chumu.jianzhimao.ui.activity.OnSendMessageHandler;
import com.chumu.jianzhimao.ui.mvp.UserModle;
import com.chumu.jianzhimao.ui.mvp.bean.BeanLogin;
import com.example.common_base.ApiConfig;
import com.example.common_base.AppConfig;
import com.example.common_base.SPConstant;
import com.example.common_base.base.BaseMvpActivity;
import com.example.common_base.utils.SpannableStringAttach;
import com.example.common_base.utils.ToastUtil;
import com.google.gson.Gson;
import com.jzp.rotate3d.Rotate3D;
import com.mob.MobSDK;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import okhttp3.ResponseBody;

import static com.example.common_base.ApiConfig.GET_V_CODE;
import static com.example.common_base.ApiConfig.USER_LOGIN;

public class RegisterAndPhoneLoginActivity extends BaseMvpActivity<UserModle> implements View.OnClickListener {
    public static final int INIT_TYPE_FIND_PASSWORD = 0;
    public static final int INIT_TYPE = 1;
    @BindView(R.id.bt_login)
    Button mBtLogin;
    @BindView(R.id.tv_free_login)
    TextView mTvFreeLogin;
    @BindView(R.id.tv_agreement)
    TextView mTvAgreement;
    @BindView(R.id.tv_getcode)
    TextView mTvGetcode;
    @BindView(R.id.retrieve_password_tv)
    TextView mRetrievePasswordTv;
    @BindView(R.id.ed_phone)
    EditText mEdPhone;
    @BindView(R.id.ed_verification_code)
    EditText mEdVerificationCode;
    @BindView(R.id.tv_count_down)
    TextView mTvCountDown;
    private CountDownTimer mCountDownTimer;
    private Rotate3D rotate;
    private int mType;
    private String mPhone;


    @Override
    public UserModle getModel() {
        return new UserModle();
    }

    @Override
    protected int onCreateContentView() {
        return R.layout.activity_login_register_and_phone;
    }

    @Override
    public void initView() {
        MobSDK.submitPolicyGrantResult(true, null);
        SMSSDK.registerEventHandler(eh); //注册短信回调
        SMSSDK.registerEventHandler(eh);
        mode1();
        mType = getIntent().getIntExtra("type", -1);
        if (mType == INIT_TYPE_FIND_PASSWORD) {
            mRetrievePasswordTv.setText("验证码登录");
            getTitleView().setTitle("找回密码");
        } else {
            mRetrievePasswordTv.setText("找回密码");
            getTitleView().setTitle("登录/注册");
        }
    }


//注册一个事件回调监听，用于处理SMSSDK接口请求的结果

    EventHandler eh = new EventHandler() {

        @Override
        public void afterEvent(int event, int result, Object data) {
            ChuMuKLogUtil.printLine("chumu", true);
            ChuMuKLog.e(result + "++++++++++" + data);
            ChuMuKLogUtil.printLine("chumu", false);
            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mPresenter.getData(USER_LOGIN, mPhone, 1, AppConfig.User.Verification_Code_login);
                        }
                    });

                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hide();
                            onCountDown();
                        }
                    });

                    //获取验证码成功
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                } else {
                    ToastUtil.toastShortMessage("验证码错误");
                }
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
    };

    @Override
    public void initData() {


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

            case USER_LOGIN:

                try {
                    Log.e("chumu", "onResponse: "+str.string() );
                    BeanLogin beanLogin = null;
                    if (str.string()!=null) {
                        beanLogin = new Gson().fromJson(str.string(), BeanLogin.class);

//                    ToastUtil.toastShortMessage(beanLogin.getDesc());
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

                    }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    @OnClick({R.id.bt_login, R.id.tv_free_login, R.id.tv_getcode, R.id.retrieve_password_tv})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_login:
                GoToLogin();
//                startActivity(new Intent(this, HomeActivity.class));
//                finish();
                break;
            case R.id.tv_free_login:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                finish();
                break;
            case R.id.tv_getcode:
                GoToGetSms();
                break;
            case R.id.retrieve_password_tv:
                if (mType == INIT_TYPE_FIND_PASSWORD) {
                    startActivity(new Intent(this, RegisterAndPhoneLoginActivity.class).putExtra("type", INIT_TYPE));
                } else {
                    startActivity(new Intent(this, RegisterAndPhoneLoginActivity.class).putExtra("type", INIT_TYPE_FIND_PASSWORD));
                }

                finish();
                break;
        }
    }



    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
        SMSSDK.unregisterEventHandler(eh);
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
                    mPresenter.getData(USER_LOGIN, mPhone, 1, AppConfig.User.Verification_Code_login,smsCode);
                } else {
                    showToast("验证码不能为空");
                }
            } else {
                showToast("正确输入手机号");
            }
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

    private void onCountDown() {
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
    }

    private void mode1() {
        String userProtocol = "用户协议";
        String privacy = "隐私政策";
        String protocolTipText = "注册及表示您已经同意 用户协议 和 隐私政策";
        int linkColor = getResources().getColor(R.color.app_theme_color);
        SpannableStringAttach.create(protocolTipText)
                .addClickableSpan(userProtocol, false, SpannableStringAttach.MATCH_MODE_ALL, linkColor, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        agreementOnCilck();
                    }
                }).addClickableSpan(privacy, false, SpannableStringAttach.MATCH_MODE_ALL, linkColor, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agreementOnCilck();
            }
        }).attach(mTvAgreement);
    }

    private void agreementOnCilck() {

//        Intent intent = new Intent(PhoneLoginActivity.this, AgreementActivity.class);
//
//        startActivity(intent);
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}