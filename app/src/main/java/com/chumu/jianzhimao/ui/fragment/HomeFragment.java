package com.chumu.jianzhimao.ui.fragment;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.adapter.VpHomeAdapter;
import com.chumu.jianzhimao.ui.mvp.HomeModle;
import com.example.common_base.base.BaseMvpFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseMvpFragment<HomeModle> {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.vp_page)
    ViewPager2 mVpPage;
    private VpHomeAdapter mVpHomeAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        ArrayList<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new HomeChildFragment());
        mFragments.add(new HomeChildFragment());
        mFragments.add(new HomeChildFragment());

        mVpHomeAdapter = new VpHomeAdapter(getActivity(), mFragments);
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
                tab.setText("Tab" + position);
            }
        }).attach();


    }

    @Override
    public void initData() {
        show();
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

    }

//    @Override
//    public void onResponse(int whichApi, Object[] t) {
//        hide();
//        switch (whichApi) {
//            default:
//                break;
//            case ApiConfig.GET_DATA_HOME_MENUS:
//
//                BeanHomeList beanHomeList = (BeanHomeList) t[0];
//                if (beanHomeList.getCode() == 10000) {
//                    if (beanHomeList.getRes().getMenus() != null && beanHomeList.getRes().getMenus().size() > 0) {
//
//                        List<BeanHomeList.ResBean.MenusBean> menus = beanHomeList.getRes().getMenus();
//                        ArrayList<Fragment> fragments = new ArrayList<>();
//                        for (int i = 0; i < menus.size(); i++) {
//                            HomeChildFragment homeChildFragment = new HomeChildFragment();
//                            Bundle bundle = new Bundle();
//                            bundle.putInt("menuId", menus.get(i).getId());
//                            homeChildFragment.setArguments(bundle);
//                            fragments.add(homeChildFragment);
//                        }
//                        mVpHoemAdapter.addframgent(fragments);
//                        mVpHoemAdapter.setlist(beanHomeList.getRes().getMenus());
//                        mVpHoemAdapter.notifyDataSetChanged();
//
//
//                    }
//                } else {
//                    showToast(beanHomeList.getRes().getMessage());
//                }
//
//
//                break;
//
//        }
//
//    }
}
