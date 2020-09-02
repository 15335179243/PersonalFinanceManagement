package com.chumu.jianzhimao;

import android.util.Log;

import com.example.common_base.base.BaseApplication;

import cn.jiguang.verify.MyApp;
import cn.jiguang.verifysdk.api.JVerificationInterface;
import cn.jiguang.verifysdk.api.RequestCallback;

/**
 * JianZhiMao
 * <p>
 * Created by ChuMu on 2020/8/31
 * Copyright © 2020年 ChuMu. All rights reserved.
 * <p>
 * Describe: 主Application
 */

public class RootApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
//        JVerificationInterface.setDebugMode(true);
//        final long start = System.currentTimeMillis();
//        JVerificationInterface.init(this, new RequestCallback<String>() {
//            @Override
//            public void onResult(int code, String result) {
//                Log.d("MyApp", "[init] code = " + code + " result = " + result + " consists = " + (System.currentTimeMillis() - start));
//            }
//        });
    }
}

