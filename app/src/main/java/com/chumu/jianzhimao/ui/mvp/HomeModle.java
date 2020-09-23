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
        }
    }

}