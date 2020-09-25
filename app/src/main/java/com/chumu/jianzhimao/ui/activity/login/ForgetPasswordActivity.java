package com.chumu.jianzhimao.ui.activity.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.activity.HomeActivity;
import com.chumu.jianzhimao.ui.mvp.UserModle;
import com.chumu.jianzhimao.ui.mvp.bean.BeanLogin;
import com.example.common_base.AppConfig;
import com.example.common_base.RoutePath;
import com.example.common_base.SPConstant;
import com.example.common_base.base.BaseMvpActivity;
import com.example.common_base.utils.ToastUtil;
import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.common_base.ApiConfig.USER_PASSWORD_LOGIN;

@Route(path = RoutePath.Login.LOGIN_PWD)
public class ForgetPasswordActivity extends BaseMvpActivity<UserModle> {

    @BindView(R.id.bt_login)
    Button mBtLogin;
    @BindView(R.id.vi_noe)
    View mViNoe;
    @BindView(R.id.vi_tow)
    View mViTow;
    @BindView(R.id.ed_phone)
    EditText mEdPhone;
    @BindView(R.id.enter_password_ed)
    EditText mEnterPasswordEd;
    @BindView(R.id.tv_count_down)
    TextView mTvCountDown;
    @BindView(R.id.verification_login_tv)
    TextView mVerificationLoginTv;
    @BindView(R.id.textView2)
    TextView mTextView2;
    @BindView(R.id.tv_agreement)
    TextView mTvAgreement;
    @BindView(R.id.retrieve_password_tv)
    TextView mRetrievePasswordTv;
    private String mPhone;

    @Override
    protected int onCreateContentView() {
        return R.layout.activity_forget_password;
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
        hide();
        String str = (String) t[0];
        switch (whichApi) {
            default:
                break;

            case USER_PASSWORD_LOGIN:


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

                    }


                break;

        }
    }

    @OnClick({R.id.bt_login, R.id.verification_login_tv, R.id.retrieve_password_tv})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_login:
                GoToLogin();

                break;
            case R.id.verification_login_tv:
                startActivity(new Intent(this, RegisterAndPhoneLoginActivity.class));
                finish();
                break;
            case R.id.retrieve_password_tv:
                startActivity(new Intent(this, RetrievePasswordActivity.class));
                finish();
                break;
        }
    }

    private void GoToLogin() {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0-9])|(16[013678])|(19[0136789]))\\d{8}$";
        mPhone = mEdPhone.getText().toString().trim();
        String password = mEnterPasswordEd.getText().toString().trim();
        if (mPhone.length() != 11) {
            showToast("手机号长度为11位");
            return;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(mPhone);
            boolean isMatch = m.matches();
            if (isMatch) {
                if (!TextUtils.isEmpty(password)) {
                    mPresenter.getData(USER_PASSWORD_LOGIN, mPhone,password, 1, AppConfig.User.Account_Password_login);
                } else {
                    showToast("密码不能为空");
                }
            } else {
                showToast("正确输入手机号");
            }
        }
    }
}
