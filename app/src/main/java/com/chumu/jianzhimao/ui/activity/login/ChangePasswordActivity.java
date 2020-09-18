package com.chumu.jianzhimao.ui.activity.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chumu.dt.v24.magicbox.swipeback.ChuMuSwipeBack;
import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.mvp.UserModle;
import com.example.common_base.base.BaseMvpActivity;

import butterknife.BindView;
import butterknife.OnClick;
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


    @Override
    protected int onCreateContentView() {
        return R.layout.activity_change_password;
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




    @OnClick(R.id.btn_confirm)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_confirm:
                break;
        }
    }
}
