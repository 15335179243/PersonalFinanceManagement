package com.chumu.jianzhimao.ui.utils;

import android.widget.Toast;

import com.chumu.jianzhimao.ui.zhihu.base.BaseApplication;



/**
 * Created by $lzj on 2019/5/5.
 */
public class ToastUtil {
    public static void showShort(String msg){
        //避免内存泄漏的一个方法,用到上下文的地方,能用application的就application
        Toast.makeText(BaseApplication.getApplication(),msg,Toast.LENGTH_SHORT).show();
    }
    public static void showLong(String msg){
        //避免内存泄漏的一个方法,用到上下文的地方,能用application的就application
        Toast.makeText(BaseApplication.getApplication(),msg,Toast.LENGTH_LONG).show();
    }
}
