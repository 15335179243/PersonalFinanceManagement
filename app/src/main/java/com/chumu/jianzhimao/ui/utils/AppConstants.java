package com.chumu.jianzhimao.ui.utils;

import android.os.Environment;


import com.chumu.jianzhimao.RootApplication;


import java.io.File;

/**
 * Created by $lzj on 2019/5/5.
 */
public interface AppConstants {

    String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator + "code" + File.separator + "GeekNews";

    String FILE_PROVIDER_AUTHORITY="com.baidu.geek.fileprovider";

    //网络缓存的地址
    String PATH_DATA = RootApplication.getApplication().getCacheDir().getAbsolutePath() +
            File.separator + "data";

    String PATH_CACHE = PATH_DATA + "/NetCache";

}
