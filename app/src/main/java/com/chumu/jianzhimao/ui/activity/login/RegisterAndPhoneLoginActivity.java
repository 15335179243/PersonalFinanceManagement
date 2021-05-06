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

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.fastjson.JSON;
import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.activity.HomeActivity;
import com.chumu.jianzhimao.ui.activity.eys.EmailUtils;
import com.chumu.jianzhimao.ui.activity.webview.AgreementActivity;
import com.chumu.jianzhimao.ui.mvp.UserModle;
import com.chumu.jianzhimao.ui.mvp.bean.BeanLogin;
import com.example.common_base.ApiConfig;
import com.example.common_base.AppConfig;
import com.example.common_base.RoutePath;
import com.example.common_base.SPConstant;
import com.example.common_base.base.BaseMvpActivity;
import com.example.common_base.utils.SpannableStringAttach;
import com.example.common_base.utils.ToastUtil;
import com.google.gson.Gson;
import com.jzp.rotate3d.Rotate3D;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.common_base.ApiConfig.GET_V_CODE;
import static com.example.common_base.ApiConfig.USER_LOGIN;
@Route(path = RoutePath.Login.LOGIN_PWD)
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
    @BindView(R.id.ed_pwd)
    EditText mEdPwd;
    @BindView(R.id.ed_verification_code)
    EditText mEdVerificationCode;
    @BindView(R.id.tv_count_down)
    TextView mTvCountDown;
    private CountDownTimer mCountDownTimer;
    private Rotate3D rotate;
    private int mType;
    private String mPhone;
    private String mSmsCode;


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
        mode1();
        getTitleView().mBackBtn.setVisibility(View.GONE);
        mType = getIntent().getIntExtra("type", -1);
        if (mType == INIT_TYPE_FIND_PASSWORD) {
            mRetrievePasswordTv.setText("验证码登录");
            getTitleView().setTitle("找回密码");
        } else {
            mRetrievePasswordTv.setText("找回密码");
            getTitleView().setTitle("注册");
        }
    }


    @Override
    public void initData() {


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
            case USER_LOGIN:
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
            case GET_V_CODE:
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(str);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
                boolean email = isEmail(mEdPhone.getText().toString().trim());

                break;
            case R.id.retrieve_password_tv:
                startActivity(new Intent(this, RetrievePasswordActivity.class));
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

    }

    private void GoToLogin() {
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        mPhone = mEdPhone.getText().toString().trim();

        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(mPhone);
        boolean isMatch = m.matches();
        if (isMatch) {
            if (mEdPwd.getText().toString().trim().isEmpty()) {
                showToast("密码不能为空");
                return;
            }
            if (!TextUtils.isEmpty(mSmsCode)) {
                if (!mSmsCode.equals(mEdVerificationCode.getText().toString().trim())) {
                    showToast("验证码错误");
                    return;
                }
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("userName", mPhone);
                    jsonObject.put("passWord", mEdPwd.getText().toString().trim());
                    jsonObject.put("nickName", "体验官");
                    jsonObject.put("headPhoto", "");
                    String o = (String) JSON.toJSON(jsonObject.toString());
                    Log.e("chumu", "GoToLogin: j" + o);
                    mPresenter.getData(USER_LOGIN, o);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                showToast("验证码不能为空");
            }
        } else {
            showToast("请正确输入邮箱");
        }

    }

    public boolean isEmail(String email) {
        if (null == email || "".equals(email)) {
            Toast.makeText(this, "有邮箱不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(email);
        if (m.matches()) {
            show();
            EmailUtils.get163()
                    .setEmailName("管理员发送短信业务邮件")
                    .setReceiveEmailAccounts(email, "体验官")
                    .sendMail("您有新的验证码，请注意查收", "感谢您对我们工作的支持，您的验证码是：" + random5(), new EmailUtils.onCallBack() {
                        @Override
                        public void CallS() {
                            showToast("发送成功请查收邮箱");
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
                            showToast("发送失败,请确保网络链接");
                            hide();

                        }
                    });
            return true;
        } else {
            showToast("请正确输入邮箱");
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


    private void mode1() {
        String userProtocol = "用户协议";
        String privacy = "隐私政策";
        String protocolTipText = "注册及表示您已经同意 用户协议 和 隐私政策";
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
