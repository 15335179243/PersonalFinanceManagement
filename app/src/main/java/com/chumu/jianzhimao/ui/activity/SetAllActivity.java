package com.chumu.jianzhimao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.chumu.dt.v24.magicbox.livedatabus.ChuMuLiveDataBus;
import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.mvp.HomeModle;
import com.chumu.jianzhimao.ui.utils.ToastUtil;
import com.example.common_base.ApiConfig;
import com.example.common_base.base.BaseApplication;
import com.example.common_base.base.BaseMvpActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.common_base.ApiConfig.ADDFINANCE;
import static com.example.common_base.ApiConfig.CREATEGROUP;
import static com.example.common_base.ApiConfig.GET_HOME_TAB;
import static com.example.common_base.ApiConfig.UPLOADING;

public class SetAllActivity extends BaseMvpActivity<HomeModle> {


    @BindView(R.id.ed_beizhu)
    EditText edBeizhu;
    @BindView(R.id.bt_queding)
    Button btQueding;
    @BindView(R.id.ed_text)
    EditText edText;
    @BindView(R.id.vi_noe)
    View viNoe;
    private int mType;
    private String mYuzhi;

    @Override
    protected int onCreateContentView() {
        return R.layout.activity_set_all;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        mType = intent.getIntExtra("type", 0);
        getTitleView().setTitle(title);
        switch (mType) {
            default:

                break;

            case 1:
                edText.setInputType(EditorInfo.TYPE_CLASS_TEXT);
                edText.setHint("需要创建的群名称");

                break;
            case 2:
                edBeizhu.setVisibility(View.VISIBLE);
                edText.setHint("记账,填正负值");
                break;


        }

    }

    @Override
    public void initData() {

    }

    @Override
    public HomeModle getModel() {
        return new HomeModle();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        hide();
        String str = (String) t[0];

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jsonObject == null) return;
        switch (whichApi) {
            default:
                break;
            case CREATEGROUP:
                if (jsonObject.optInt("code") == 200) {
                    showToast("添加成功");
                    ChuMuLiveDataBus.INSTANCE.send("quznhu",mYuzhi);
                    finish();
                }


                break;
            case ADDFINANCE:
                if (jsonObject.optInt("code") == 200) {
                    showToast("修改成功");
                    ChuMuLiveDataBus.INSTANCE.send("jiyibi",mYuzhi);
                    finish();
                }

                break;
            case UPLOADING:
                if (jsonObject.optInt("code") == 200) {
                    showToast("修改阈值成功");
                    ChuMuLiveDataBus.INSTANCE.send("yuzhi",mYuzhi);
                    finish();
                }

                break;
        }
    }


    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    @OnClick(R.id.bt_queding)
    public void onClick() {
        show();
        try {
            JSONObject jsonObject = new JSONObject();
            switch (mType) {
                default:
                    if (edText.getText() != null) {
                        mYuzhi = edText.getText().toString().trim();
                        if (isInteger(mYuzhi)) {

                            jsonObject.put("token", BaseApplication.mToken);
                            jsonObject.put("moneyLimit", mYuzhi);
                            String o = (String) JSON.toJSON(jsonObject.toString());
                            Log.e("chumu", "onResponse: " + o);
                            mPresenter.getData(UPLOADING, o);
                            show();
                        }
                    } else {

                        ToastUtil.showShort("请输入阈值");
                    }


                    break;

                case 1:
                    if (edText.getText() != null) {

                        String yuzhi = edText.getText().toString().trim();

                        mPresenter.getData(CREATEGROUP, BaseApplication.mToken, yuzhi);
                    } else {

                        ToastUtil.showShort("请输入值");
                    }

                    break;
                case 2:
                    if (edText.getText() != null) {
                        String yuzhi = edText.getText().toString().trim();
                        if (edBeizhu.getText() != null) {
                            if (isInteger(yuzhi)) {
                                int i = Integer.parseInt(yuzhi);
                                if (i > 0) {
                                    jsonObject.put("status", 2);
                                } else {
                                    jsonObject.put("status", 1);
                                }
                                jsonObject.put("token", BaseApplication.mToken);
                                jsonObject.put("money", yuzhi);
                                jsonObject.put("desc", edBeizhu.getText().toString().trim());

                                String o = (String) JSON.toJSON(jsonObject.toString());
                                Log.e("chumu", "GoToLogin: j" + o);
                                mPresenter.getData(ADDFINANCE, o);
                            } else {
                                ToastUtil.showShort("请正确输入金额");
                            }
                        } else {
                            ToastUtil.showShort("请输入备注");
                        }
                    } else {

                        ToastUtil.showShort("请输入金额");
                    }
                    break;


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
