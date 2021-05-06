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
            case ApiConfig.GET_STATISTICS_NUM:
                mManager.method(mManager.getNetService().getStatisticsNum((int) t[0], (int) t[1], (int) t[2]), view, whichApi, t[0]);

                break;
            case ApiConfig.QUERY_STATISTICS_TOTAL:
                mManager.method(mManager.getNetService().getQueryStatisticsTotal((String) t[0]), view, whichApi, t[0]);

                break;
            case ApiConfig.FINANCE_LIST:
                mManager.method(mManager.getNetService().getFinanceList((String) t[0]), view, whichApi, t[0]);

                break;
            case ApiConfig.ADDFINANCE:
                mManager.method(mManager.getNetService().getAddFinance((String) t[0]), view, whichApi, t[0]);

                break;
            case ApiConfig.CREATEGROUP:
                mManager.method(mManager.getNetService().getCreateGroup((String) t[0],(String) t[1]), view, whichApi, t[0]);

                break;

            case ApiConfig.UPLOADING:

                mManager.method(mManager.getNetService().getUpLodeImg((String) t[0]), view, whichApi, t);

                break;
            case ApiConfig.QUERY_GROUP:

                mManager.method(mManager.getNetService().getQueryGroup((int) t[0],(int) t[1]), view, whichApi, t);
                break;
            case ApiConfig.ADD_GROUP:

                mManager.method(mManager.getNetService().getAddGroup((int) t[0], (String) t[1]), view, whichApi, t);
                break;
            case ApiConfig.LOGOUT_GROUP:

                mManager.method(mManager.getNetService().getLogouTGroup((int) t[0], (String) t[1]), view, whichApi, t);

                break;
        }
    }

}