package com.chumu.jianzhimao.ui.zhihu;

import android.os.Environment;
import android.view.animation.RotateAnimation;

import com.chumu.jianzhimao.RootApplication;

import java.io.File;

public interface AppConstants {

    String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator + "code" + File.separator + "GeekNews";

    String FILE_PROVIDER_AUTHORITY="com.chumu.gerengcaiwuguanli.fileprovider";

    //网络缓存的地址
    String PATH_DATA = RootApplication.getApplication().getCacheDir().getAbsolutePath() +
            File.separator + "data";

    String PATH_CACHE = PATH_DATA + "/NetCache";

}
