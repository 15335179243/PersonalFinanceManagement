package com.chumu.jianzhimao.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;


/**
 * @author: hgb
 * @createTime: 2019/12/2
 * @description:
 * @changed by:
 */
public class VpHomeAdapter extends FragmentStateAdapter {
    private ArrayList<Fragment> mFragments;

    public VpHomeAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<Fragment> fragments) {
        super(fragmentActivity);
        mFragments = fragments;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragments.size();
    }

}
