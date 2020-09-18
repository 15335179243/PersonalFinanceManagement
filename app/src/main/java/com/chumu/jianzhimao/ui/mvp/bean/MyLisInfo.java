package com.chumu.jianzhimao.ui.mvp.bean;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;

import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.activity.SettingActivity;
import com.example.common_base.ChuMuData;

import java.util.ArrayList;

/**
 * JianZhiMao
 * <p>
 * Created by ChuMu on 2020/9/16
 * Copyright © 2020年 ChuMu. All rights reserved.
 * <p>
 * Describe:
 */

public class MyLisInfo extends ChuMuData {
    public static final int ML_LIST_BROWSE = 0;//我的浏览
    public static final int ML_LIST_FEEDBACK = 1;//意见反馈
    public static final int ML_LIST_USER_AGREEMENT = 2;//用户协议
    public static final int ML_LIST_PRIVACY_AGREEMENT = 3;//隐私协议
    public static final int ML_LIST_SETTING = 4;//设置
    public static final int ML_LIST_ABOUT_US = 5;//关于我们
    private int itemId;
    private String itemName;

    private int resPic;


    public int getItemId() {
        return itemId;


    }

    public static ArrayList<MyLisInfo> getMyListInfoData() {
        ArrayList<MyLisInfo> mList = new ArrayList<>();
        mList.add(new MyLisInfo(ML_LIST_BROWSE, "我的浏览", R.drawable.ic_user));
        mList.add(new MyLisInfo(ML_LIST_FEEDBACK, "意见反馈", R.drawable.ic_user));
        mList.add(new MyLisInfo(ML_LIST_USER_AGREEMENT, "用户协议", R.drawable.ic_user));
        mList.add(new MyLisInfo(ML_LIST_PRIVACY_AGREEMENT, "隐私协议", R.drawable.ic_user));
        mList.add(new MyLisInfo(ML_LIST_SETTING, "设置", R.drawable.ic_user));
        mList.add(new MyLisInfo(ML_LIST_ABOUT_US, "关于我们", R.drawable.ic_user));

        return mList;
    }

    public static void onSwitchPage(Activity activity, int itemId) {
        switch (itemId) {
            default:
                break;
            case ML_LIST_BROWSE:
//                startPage(activity,Class.class);
                break;
            case ML_LIST_FEEDBACK:
                break;
            case ML_LIST_USER_AGREEMENT:
                break;
            case ML_LIST_PRIVACY_AGREEMENT:
                break;
            case ML_LIST_SETTING:
                startPage(activity, SettingActivity.class);
                break;
            case ML_LIST_ABOUT_US:
                break;


        }
    }

    private static void startPage(Activity activity, Class classClass) {
        activity.startActivity(new Intent(activity,classClass));
    }


    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getResPic() {
        return resPic;
    }

    public void setResPic(int resPic) {
        this.resPic = resPic;
    }

    public MyLisInfo(int itemId, String itemName, int resPic) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.resPic = resPic;
    }

    @Override
    public String toString() {
        return "MyLisInfo{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", resPic=" + resPic +
                '}';
    }
}
