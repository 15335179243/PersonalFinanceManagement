package com.chumu.jianzhimao.ui.zhihu.base;

import android.app.Activity;

import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;


import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.zhihu.RxFragment;


import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFramgent extends RxFragment {

    private Unbinder mBind;
    private BaseActivity mBaseActivity;

    public int enter() {
        return R.anim.common_page_right_in;
    }

    public int exit() {
        return R.anim.common_page_left_out;
    }

    public int popEnter() {
        return R.anim.common_page_left_in;
    }

    public int popExit() {
        return R.anim.common_page_right_out;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(getlayoutId(), container, false);
        mBind = ButterKnife.bind(this, inflate);

        initView(inflate);
        initMvp();
        initData();
        initListener();
        return inflate;
    }

    public void initMvp() {
    }

    public void initListener() {
    }

    public void initData() {
    }

    public void initView(View inflate) {
    }

    protected abstract int getlayoutId();


    protected boolean isNeedToAddBackStack() {
        return false;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseActivity) {
            mBaseActivity = (BaseActivity) activity;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void addFragment(FragmentManager manager, Class<? extends BaseFramgent> aClass, int containerId, Bundle args) {
        if (mBaseActivity != null) {
            mBaseActivity.addFragment(manager, aClass, containerId, args);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }
}
