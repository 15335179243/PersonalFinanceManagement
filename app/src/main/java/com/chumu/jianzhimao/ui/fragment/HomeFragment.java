package com.chumu.jianzhimao.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.activity.HomeActivity;
import com.chumu.jianzhimao.ui.activity.login.SetPasswordActivity;
import com.chumu.jianzhimao.ui.adapter.VpHomeAdapter;
import com.chumu.jianzhimao.ui.mvp.HomeModle;
import com.chumu.jianzhimao.ui.mvp.bean.BeanHomeTab;
import com.chumu.jianzhimao.ui.mvp.bean.BeanLogin;
import com.example.common_base.AppConfig;
import com.example.common_base.SPConstant;
import com.example.common_base.base.BaseMvpFragment;
import com.example.common_base.utils.ToastUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

import static com.example.common_base.ApiConfig.GET_HOME_TAB;
import static com.example.common_base.ApiConfig.USER_LOGIN;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseMvpFragment<HomeModle> {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.vp_page)
    ViewPager2 mVpPage;
    private VpHomeAdapter mVpHomeAdapter;
    private List<BeanHomeTab.DataBean> mData;
    private ArrayList<Fragment> mMFragments;
    private TabLayoutMediator mTabLayoutMediator;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        mMFragments = new ArrayList<>();


    }

    private void initTab() {
        mVpHomeAdapter = new VpHomeAdapter(getActivity(), mMFragments);
        mVpPage.setAdapter(mVpHomeAdapter);
        mVpPage.setCurrentItem(0);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVpPage.setCurrentItem(tab.getPosition());

                View view = tab.getCustomView();
                if (null == view) {
                    tab.setCustomView(R.layout.tab_layout_text);
                }
                TextView textView = Objects.requireNonNull(tab.getCustomView()).findViewById(android.R.id.text1);
                textView.setTextAppearance(getActivity(), R.style.TabLayoutTextSize);


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                mVpPage.setCurrentItem(tab.getPosition());
                View view = tab.getCustomView();
                if (null == view) {
                    tab.setCustomView(R.layout.tab_layout_text);
                }
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                textView.setTextAppearance(getActivity(), R.style.TabLayoutTextSizeTow);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

       new TabLayoutMediator(mTabLayout, mVpPage, new TabLayoutMediator.OnConfigureTabCallback() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (mData != null) {
                    tab.setText(mData.get(position).getIndexName());
                }

            }
        }).attach();
    }


    @Override
    public void initData() {
        show();
        mPresenter.getData(GET_HOME_TAB);
//        HashMap<String, String> map = new HashMap<>();
//        map.put("menuId", String.valueOf(MainAplication.menuid));
//        mPresenter.getData(ApiConfig.GET_DATA_HOME_MENUS, MainAplication.menuid, Encryption.formatUrlParam(map));
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
            case GET_HOME_TAB:
                BeanHomeTab beanHomeTab = new Gson().fromJson(str, BeanHomeTab.class);
                if (beanHomeTab.getCode() == 200) {
                    mData = beanHomeTab.getData();
                    for (BeanHomeTab.DataBean datum : mData) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(AppConfig.DataTag.PLACE, datum.getIndexType());
                        HomeChildFragment homeChildFragment = new HomeChildFragment();
                        homeChildFragment.setArguments(bundle);
                        mMFragments.add(homeChildFragment);

                    }
                    initTab();

                }else {
                    ToastUtil.toastShortMessage(beanHomeTab.getDesc());
                }
                break;
        }
    }


}
