package com.chumu.jianzhimao.ui.activity.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.activity.eys.EmailUtils;
import com.chumu.jianzhimao.ui.activity.webview.AgreementActivity;
import com.chumu.jianzhimao.ui.mvp.UserModle;
import com.example.common_base.ApiConfig;
import com.example.common_base.AppConfig;
import com.example.common_base.base.BaseMvpActivity;
import com.example.common_base.utils.SpannableStringAttach;
import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
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
    @BindView(R.id.tv_agreement)
    TextView mTvAgreement;
    @BindView(R.id.Verification_code_login_tv)
    TextView mVerificationCodeLoginTv;
    private CountDownTimer mCountDownTimer;
    private String mPhone;
    private String mSmsCode;

    @Override
    protected int onCreateContentView() {
        return R.layout.activity_retrieve_password;
    }

    @Override
    public void initView() {
        getTitleView().mBackBtn.setVisibility(View.GONE);
        mode1();
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
                break;
            case R.id.tv_free_login:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            case R.id.tv_getcode:
                mPhone = mEdPhone.getText().toString().trim();
                GoToGetSms(mPhone);
                break;
            case R.id.Verification_code_login_tv:
                startActivity(new Intent(this, RegisterAndPhoneLoginActivity.class));
                finish();
                break;
        }
    }


    public boolean GoToGetSms(String email) {
        if (null == email || "".equals(email)) {
            Toast.makeText(this, "?????????????????????", Toast.LENGTH_SHORT).show();
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(email);
        if (m.matches()) {
            show();
            EmailUtils.get163()
                    .setEmailName("?????????????????????????????????")
                    .setReceiveEmailAccounts(email, "?????????")
                    .sendMail("???????????????????????????????????????", "?????????????????????????????????????????????????????????" + random5(), new EmailUtils.onCallBack() {
                        @Override
                        public void CallS() {
                            showToast("???????????????????????????");
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
                            showToast("?????????????????????");
                            hide();
                        }

                        @Override
                        public void CallF() {
                            mTvGetcode.setClickable(true);
                            mTvGetcode.setVisibility(View.VISIBLE);
                            mTvCountDown.setVisibility(View.GONE);
                            if (mCountDownTimer != null) {
                                mCountDownTimer = null;
                            }
                            showToast("????????????,?????????????????????");
                            hide();

                        }
                    });
            return true;
        } else {
            showToast("?????????????????????");
            return false;
        }
    }

    public String random5() {
        String source = "0123456789";
        Random random = new Random();
        StringBuffer flag = new StringBuffer();
        for (int j = 0; j < 6; j++) {
            flag.append(source.charAt(random.nextInt(10)));
        }
        mSmsCode = flag.toString();
        return mSmsCode;

    }


    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }

    }

    private void GoToLogin() {
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        mPhone = mEdPhone.getText().toString().trim();

        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(mPhone);
        boolean isMatch = m.matches();
        if (isMatch) {

            if (!TextUtils.isEmpty(mSmsCode)) {
                if (!mSmsCode.equals(mEdVerificationCode.getText().toString().trim())) {
                    showToast("???????????????");
                    return;
                }

                Intent intent = new Intent(this, SetPasswordActivity.class);
                intent.putExtra("userName", mPhone);
                startActivity(intent);
                finish();
            } else {
                showToast("?????????????????????");
            }
        } else {
            showToast("?????????????????????");
        }

    }


    private void mode1() {
        String userProtocol = "????????????";
        String privacy = "????????????";
        String protocolTipText = "?????????????????????????????? ???????????? ??? ????????????";
        int linkColor = getResources().getColor(R.color.app_theme_color);
        SpannableStringAttach.create(protocolTipText)
                .addClickableSpan(userProtocol, false, SpannableStringAttach.MATCH_MODE_ALL, linkColor, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        agreementOnCilck(AppConfig.WebView.USER_AGREEMENT);
                    }
                }).addClickableSpan(privacy, false, SpannableStringAttach.MATCH_MODE_ALL, linkColor, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agreementOnCilck(AppConfig.WebView.PRIVACY_AGREEMENT);
            }
        }).attach(mTvAgreement);
    }

    private void agreementOnCilck(int type) {

        Intent intent = new Intent(this, AgreementActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
