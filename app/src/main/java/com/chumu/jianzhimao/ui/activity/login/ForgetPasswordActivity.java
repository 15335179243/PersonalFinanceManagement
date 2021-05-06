package com.chumu.jianzhimao.ui.activity.login;

import android.content.Intent;
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
import com.chumu.jianzhimao.ui.activity.webview.AgreementActivity;
import com.chumu.jianzhimao.ui.mvp.UserModle;
import com.chumu.jianzhimao.ui.mvp.bean.BeanLogin;
import com.example.common_base.AppConfig;
import com.example.common_base.RoutePath;
import com.example.common_base.SPConstant;
import com.example.common_base.base.BaseMvpActivity;
import com.example.common_base.utils.SpannableStringAttach;
import com.example.common_base.utils.ToastUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

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
        hide();
        String str = (String) t[0];
        switch (whichApi) {
            default:
                break;

            case USER_PASSWORD_LOGIN:
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
                GoToLogin(mEdPhone.getText().toString().trim());

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

    private void GoToLogin(String email) {
        if (null == email || "".equals(email)) {
            Toast.makeText(this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(email);
        if (m.matches()) {

            String password = mEnterPasswordEd.getText().toString().trim();
            if (!TextUtils.isEmpty(password)) {
                JSONObject jsonObject = new JSONObject();
                try {

                    jsonObject.put("passWord", password);
                    jsonObject.put("userName", email);
                    String o = (String) JSON.toJSON(jsonObject.toString());
                    Log.e("chumu", "GoToLogin: j" + o);
                    mPresenter.getData(USER_PASSWORD_LOGIN, o);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                showToast("密码不能为空");
            }
        } else {
            showToast("请确保输入邮箱正确");
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
