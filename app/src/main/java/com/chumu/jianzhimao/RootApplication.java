package com.chumu.jianzhimao;

import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common_base.base.BaseApplication;
import com.tanrice.unmengapptrack.UMengInit;
;

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
        UMengInit.initUmeng(this);
        if (BuildConfig.DEBUG) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }
        ARouter.init(this);
    }
}

