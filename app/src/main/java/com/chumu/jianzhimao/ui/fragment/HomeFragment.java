package com.chumu.jianzhimao.ui.fragment;


import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
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
import com.chumu.jianzhimao.ui.activity.SetAllActivity;
import com.chumu.jianzhimao.ui.adapter.RlvV2exDetailsAdapter;
import com.chumu.jianzhimao.ui.mvp.HomeModle;
import com.chumu.jianzhimao.ui.mvp.bean.BeanHomeList;
import com.chumu.jianzhimao.ui.mvp.bean.GroutBean;
import com.example.common_base.base.BaseApplication;
import com.example.common_base.base.BaseMvpFragment;
import com.example.common_base.utils.ToastUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;

import static com.example.common_base.ApiConfig.ADD_GROUP;
import static com.example.common_base.ApiConfig.FINANCE_LIST;
import static com.example.common_base.ApiConfig.GET_HOME_TAB;
import static com.example.common_base.ApiConfig.LOGOUT_GROUP;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseMvpFragment<HomeModle> {


    @BindView(R.id.list_rlv)
    RecyclerView listRlv;
    private int page = 1;
    private RlvV2exDetailsAdapter mRlvV2exDetailsAdapter;
    private BeanHomeList mBeanHomeList;
    private int mID;

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
             mID=   mBeanHomeList.getData().getRows().get(i).getId();
                registerForContextMenu(v);

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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.menu_hoem, menu);
    }

    //当ContextMenu被选中的时候处理具体的响应事件
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action1:
//                mPresenter.getData(ADD_GROUP, mTestBean.getData().getRows().get(mGroupPosition).getId(), BaseApplication.mToken);
                ToastUtil.toastShortMessage("删除成功");
                return false;
            case R.id.action2:

//                mPresenter.getData(LOGOUT_GROUP, mTestBean.getData().getRows().get(mGroupPosition).getId(), BaseApplication.mToken);

                Intent intent = new Intent(requireActivity(), SetAllActivity.class);
                intent.putExtra("title", "修改");
                intent.putExtra("type", 4);
                intent.putExtra("id", mID);
                startActivity(intent);
                return false;

            default:

        }
        return false;
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
                mBeanHomeList = new Gson().fromJson(str, BeanHomeList.class);
                if (mBeanHomeList != null && mBeanHomeList.getCode() == 200) {
                    if (mBeanHomeList.getData().getRows() != null) {


                        ChuMuNormalDecoration decoration = new ChuMuNormalDecoration() {
                            @Override
                            public String getHeaderName(int pos) {

                                if (mBeanHomeList.getData().getRows().get(pos).getCreatedTime() != null) {
                                    return mBeanHomeList.getData().getRows().get(pos).getCreatedTime();
                                } else {
                                    return "数据为空";
                                }
                            }
                        };

                        listRlv.addItemDecoration(decoration);
                        mRlvV2exDetailsAdapter.addlist(mBeanHomeList.getData().getRows());
                    }

                    break;
                }
        }


    }
}
