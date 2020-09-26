package com.chumu.jianzhimao.ui.mvp;

import com.example.common_base.ApiConfig;
import com.example.common_base.base.ICommonModel;
import com.example.common_base.base.ICommonView;
import com.example.common_base.base.NetManager;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * JianZhiMao
 * <p>
 * Created by ChuMu on 2020/9/6
 * Copyright © 2020年 ChuMu. All rights reserved.
 * <p>
 * Describe:
 */

public class HomeModle implements ICommonModel {

    NetManager mManager = NetManager.getNetManager();

    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        switch (whichApi) {
            default:
                break;
            case ApiConfig.GET_HOME_TAB:
                mManager.method(mManager.getNetService().getHomeTab(), view, whichApi, t);
                break;
            case ApiConfig.APP_LIST:
                mManager.method(mManager.getNetService().getAppList((int) t[0], (int) t[1], (int) t[2]), view, whichApi, (int) t[3]);

                break;
            case ApiConfig.GET_BANNER_LIST:
                mManager.method(mManager.getNetService().getBannerList(), view, whichApi, (int) t[0]);
                break;
            case ApiConfig.GET_STATISTICS_NUM:
                mManager.method(mManager.getNetService().getStatisticsNum((int) t[0], (int) t[1], (int) t[2]), view, whichApi, t[0]);

                break;
        }
    }

}