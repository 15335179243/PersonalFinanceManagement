package com.chumu.jianzhimao.ui.fragment;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.chumu.dt.v24.magicbox.livedatabus.ChuMuLiveDataBus;
import com.chumu.dt.v24.magicbox.wiget.ChuMuNormalDecoration;
import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.adapter.RlvV2exDetailsAdapter;
import com.chumu.jianzhimao.ui.mvp.HomeModle;
import com.chumu.jianzhimao.ui.mvp.bean.BeanHomeList;
import com.chumu.jianzhimao.ui.mvp.bean.GroutBean;
import com.example.common_base.base.BaseApplication;
import com.example.common_base.base.BaseMvpFragment;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;

import static com.example.common_base.ApiConfig.FINANCE_LIST;
import static com.example.common_base.ApiConfig.GET_HOME_TAB;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseMvpFragment<HomeModle> {


    @BindView(R.id.list_rlv)
    RecyclerView listRlv;
    private int page = 1;
    private RlvV2exDetailsAdapter mRlvV2exDetailsAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {

        listRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRlvV2exDetailsAdapter = new RlvV2exDetailsAdapter(getContext());
        listRlv.setAdapter(mRlvV2exDetailsAdapter);
        mRlvV2exDetailsAdapter.setOnClickListener(new RlvV2exDetailsAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int i, int j) {

            }
        });

        ChuMuLiveDataBus.INSTANCE.with("jiyibi").observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object o) {

                initData();
            }
        });
    }


    @Override
    public void initData() {
        show();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", BaseApplication.mToken);

            jsonObject.put("pageNo", page);
            jsonObject.put("pageSize", 20);
            String o = (String) JSON.toJSON(jsonObject.toString());
            Log.e("chumu", "GoToLogin: j" + o);
            mPresenter.getData(FINANCE_LIST, o);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
        switch (whichApi) {
            default:
                break;
            case FINANCE_LIST:
                BeanHomeList beanHomeList = new Gson().fromJson(str, BeanHomeList.class);
                if (beanHomeList != null && beanHomeList.getCode() == 200) {
                    if (beanHomeList.getData().getRows() != null) {


                        ChuMuNormalDecoration decoration = new ChuMuNormalDecoration() {
                            @Override
                            public String getHeaderName(int pos) {

                                if (beanHomeList.getData().getRows().get(pos).getCreatedTime() != null) {
                                    return beanHomeList.getData().getRows().get(pos).getCreatedTime();
                                } else {
                                    return "数据为空";
                                }
                            }};

                        listRlv.addItemDecoration(decoration);
                        mRlvV2exDetailsAdapter.addlist(beanHomeList.getData().getRows());
                    }

                    break;
                }
        }


    }
}
