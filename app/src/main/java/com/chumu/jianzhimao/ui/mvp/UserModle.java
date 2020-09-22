package com.chumu.jianzhimao.ui.mvp;

import com.example.common_base.ApiConfig;
import com.example.common_base.base.ICommonModel;
import com.example.common_base.base.ICommonView;
import com.example.common_base.base.NetManager;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

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

public class UserModle implements ICommonModel {

    NetManager mManager = NetManager.getNetManager();

    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {


        switch (whichApi) {

            default:
                break;
            case ApiConfig.UPLOADING:
                RequestBody from = (RequestBody) t[0];
                MultipartBody.Part filePart = (MultipartBody.Part) t[1];
                if (from != null && filePart != null) {
                    mManager.method(mManager.getNetService().getUpLodeImg(from, filePart), view, whichApi, t);
                }
            case ApiConfig.USER_LOGIN:

                mManager.method(mManager.getNetService().getLoginVerification((String) t[0], (int) t[1], (int) t[2],(String) t[3]), view, whichApi, t);

                break;
            case ApiConfig.USER_PASSWORD_LOGIN:
                mManager.method(mManager.getNetService().getLoginPassword((String) t[0], (String) t[1], (String) t[2],(int) t[3]), view, whichApi, t);
                break;
            case ApiConfig.GET_V_CODE:
                mManager.method(mManager.getNetService().getVCode((String) t[0]), view, whichApi, t);

                break;

        }
    }
}