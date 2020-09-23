package com.example.x5webview;

import android.content.Context;
import android.webkit.JavascriptInterface;

/**
 * @author: hgb
 * @createTime: 2019/9/26
 * @description:
 * @changed by:
 */
public class WebAppInterface {
    private Context context;

    public WebAppInterface(Context context) {
        this.context = context;
    }
    //参数名这个自己定义，和网页js传参对应即可

    @JavascriptInterface

    public void show(String url) {

        //这里就进行软件内部处理了，

    }

    @JavascriptInterface
    public String getToken() {
        return "APPAplication.token";

    }


}
