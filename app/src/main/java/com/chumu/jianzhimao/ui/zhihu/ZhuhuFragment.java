package com.chumu.jianzhimao.ui.zhihu;



import androidx.fragment.app.Fragment;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.zhihu.Fragment.DailyFragment;
import com.chumu.jianzhimao.ui.zhihu.Fragment.HotFragment;
import com.chumu.jianzhimao.ui.zhihu.Fragment.SpecialColumnFragment;
import com.chumu.jianzhimao.ui.zhihu.base.BaseFramgent;
import com.google.android.material.tabs.TabLayout;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * A s
 */
public class ZhuhuFragment extends BaseFramgent {
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    private View view;
    private Unbinder unbinder;
    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mTitles;

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_zhihu;
    }

    @Override
    public void initView(View inflate) {
        mFragments = new ArrayList<>();
        mTitles = new ArrayList<>();
        mFragments.add(new DailyFragment());
        mFragments.add(new SpecialColumnFragment());
        mFragments.add(new HotFragment());
        mTitles.add(getResources().getString(R.string.daily_news));
        mTitles.add(getResources().getString(R.string.specialcolumn));
        mTitles.add(getResources().getString(R.string.hot));
        VpAdapter vpAdapter = new VpAdapter(getChildFragmentManager(), mFragments, mTitles);
        mVp.setAdapter(vpAdapter);
        mTab.setupWithViewPager(mVp);
    }
}
