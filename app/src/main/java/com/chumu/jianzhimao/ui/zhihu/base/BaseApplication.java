package com.chumu.jianzhimao.ui.zhihu.base;


import android.annotation.SuppressLint;
import android.app.Application;

import androidx.multidex.MultiDex;


/**
 * Created by $lzj on 2019/6/3.
 */

public class BaseApplication extends Application {

    private static BaseApplication application;





    public static BaseApplication getApplication() {
        return application;
    }
}
