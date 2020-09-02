package com.example.x5webview.utils;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JSCallback extends WebViewJsInterface.BaseWebViewJsInterface {
    private static setJSCallbackImg mOnJSCallbackimg;
    String tag = "JSCallback";
    public List<String> mImageList;
    public int mIndex;
    public X5WebView mX5WebView;

    public JSCallback(X5WebView mX5WebView) {
        this.mX5WebView = mX5WebView;
    }



    @JavascriptInterface
    public void adRedirect(String url) {
        Log.e(tag, "adRedirect:" + url);
//        try {
//            Intent intent = AppSchemeHelper.getInstance().dealScheme(getContext(), URLDecoder.decode(url, "utf-8"));
//            startActivity(intent);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
    }

    @JavascriptInterface
    public void setGalleries(String response) {
        try {
            JSONObject jo = new JSONObject(response);
            if (jo.has("images")) {
                mImageList = new ArrayList<>();
                JSONArray images = jo.getJSONArray("images");
                for (int i = 0; i < images.length(); i++) {
                    mImageList.add(images.getString(i));
                }
            }
            Log.e(tag, mImageList.size() + ":::setGalleries");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @JavascriptInterface
    public void gallery(String response) {
//        try {
//            JSONObject jo = new JSONObject(response);
//            mIndex = jo.getInt("index");
//            if (mImageList != null && mImageList.size() > 0) {
//                final String[] urls = mImageList.toArray(new String[mImageList.size()]);
//                mOnJSCallbackimg.onJSCallbackimg(mIndex, mImageList);
//                Toast.makeText(BaseApplication.getAppContext(), "这个地方显示图片", Toast.LENGTH_SHORT).show();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @JavascriptInterface
    public void play(String type, String src, String stream) {
        play(type, src, stream, null);
    }



    @JavascriptInterface
    public void play(final String type, final String src, String stream, final String refer) {
//        Log.e(tag, "type  =  " + type + "  src  =" + src + "  stream  = " + stream);
//        if (!TextUtils.isEmpty(stream)) {
//            if (!stream.startsWith("http")) {
//                stream = NetConfig.BaseUrl + "/video/play/" + stream;
//                if (stream.startsWith("https")) {
//                    stream = stream.replaceAll("https", "http");
//                }
//            }
//
//        }
//        final String videoStream = stream;
////        playVideo(type, src, videoStream, refer);
//        Toast.makeText(BaseApplication.getAppContext(), "播放视频把", Toast.LENGTH_SHORT).show();
    }

    /**
     * @param url    浏览器的连接
     * @param target 0:打开应用内浏览器   1:打开外部浏览器
     * @param title  应用内浏览器标题
     */
    @JavascriptInterface
    public void startBrowser(final String url, final int target, final String title) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
//        playVideoByBrowser(url, target, title);
//        Toast.makeText(BaseApplication.getAppContext(), "打开浏览器", Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void jumpInnerPage(final String url) {
       /* mWebContent.post(new Runnable() {
            @Override
            public void run() {
                mNewsUrl = url;
                loadWithUrl();
            }
        });*/
//        Toast.makeText(BaseApplication.getAppContext(), "打开一个什么地址", Toast.LENGTH_SHORT).show();
    }

    /**
     * js调用分享
     */
    @JavascriptInterface
    public void appShare(final String json) {
        /*if (mWebViewJSCallBack != null) {
            mWebViewJSCallBack.appShare(json);
        }*/
//        Toast.makeText(BaseApplication.getAppContext(), "这是个分享功能", Toast.LENGTH_SHORT).show();
    }

//    @JavascriptInterface
//    public void getAuthorization() {
//        Toast.makeText(BaseApplication.getAppContext(), "这个地方调用了js里的一个方法，和token、UUID有关", Toast.LENGTH_SHORT).show();
       /* mWebContent.post(new Runnable() {
            @Override
            public void run() {
                JSONObject jo = new JSONObject();
                try {
                    UserEntity user = DatabaseHelper.getLocalUser(getContext());
                    if (user != null && user.getAccess_token() != null) {
                        jo.put("Authorization", user.getAccess_token());
                    } else {
                        jo.put("Authorization", "");
                    }
                    jo.put("UUID", AppUtils.getUUID(getContext()));
                } catch (org.json.JSONException e) {
                    e.printStackTrace();
                }
                String js = "javascript: set_Authorization( " + jo.toString() + ")";
                mWebContent.loadUrl(js);
            }
        });*/
//    }

    @JavascriptInterface
    public void payMoney(final String json) {
//        Toast.makeText(BaseApplication.getAppContext(), "支付功能", Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void shareUrl(final String url, final String title, final String desc, final String shareTitle) {
        /*if (mWebViewJSCallBack != null) {
            mWebViewJSCallBack.shareUrl(url, title, desc, shareTitle);
        }*/
//        Toast.makeText(BaseApplication.getAppContext(), "fenxiang功能", Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void shareArticle(final String json) {
//        Toast.makeText(BaseApplication.getAppContext(), "分享文章", Toast.LENGTH_SHORT).show();
    }


    @Override
    @JavascriptInterface
    public void cancel_order(final String orderNo) {
        if (TextUtils.isEmpty(orderNo))
            return;
//        Toast.makeText(BaseApplication.getAppContext(), "取消命令", Toast.LENGTH_SHORT).show();
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                cancelOrder(orderNo);
//            }
//        });
    }

    @Override
    @JavascriptInterface
    public void pay(final String orderNo, final String orderPrice) {
//        Toast.makeText(BaseApplication.getAppContext(), "又是支付功能", Toast.LENGTH_SHORT).show();
      /*  getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(orderNo)) {
                    ToastUtils.showToast(AppCore.app(), getString(R.string.pay_order_no_empty));
                    return;
                }
                mH5PayOrderNo = orderNo;
                final ProgressDialog dialog = new ProgressDialog(getContext());
                dialog.show();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        NewsDetailFragment.this.pay(dialog, orderNo, orderPrice);
                    }
                });
            }
        });*/
    }

    public  interface setJSCallbackImg {
        void onJSCallbackimg(int index, List<String> list);
    }

    public static void getSCallbackImg(setJSCallbackImg onJSCallbackimg) {
        mOnJSCallbackimg = onJSCallbackimg;
    }

}
