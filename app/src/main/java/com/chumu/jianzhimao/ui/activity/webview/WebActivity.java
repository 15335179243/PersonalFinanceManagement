package com.chumu.jianzhimao.ui.activity.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.common_base.base.BaseActivity;
import com.example.x5webview.R;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.smtt.utils.TbsLog;


public class WebActivity extends BaseActivity {
    public boolean isStart;
    private boolean doubleBack = false; //双击退出应用
    private Context mContext = null;
    private FrameLayout mWebView;
    private X5WebView mTenWebView;
    private CharSequence mIntentUrl;
    private ImageView mImageView;


    private JSCallback jsCallback;


    @Override
    protected int onCreateContentView() {
        return R.layout.activity_web;
    }

    @Override
    public void initView() {
        mWebView = (FrameLayout) findViewById(R.id.webView);
        mContext = this;
        mIntentUrl = getIntent().getStringExtra("webUrl");
        WebInit();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

    }


    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onStart() {
        super.onStart();


    }

    @SuppressLint("AddJavascriptInterface")
    private void WebInit() {
        mTenWebView = new X5WebView(this, null);

        mWebView.addView(mTenWebView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));


        mTenWebView.setWebViewClient(new MyWebViewClient());

        mTenWebView.setWebChromeClient(new WebChromeClient() {


            @Override
            public boolean onJsConfirm(WebView arg0, String arg1, String arg2,
                                       JsResult arg3) {
                return super.onJsConfirm(arg0, arg1, arg2, arg3);
            }

            // /////////////////////////////////////////////////////////
            //
            @Override
            public boolean onJsAlert(WebView arg0, String arg1, String arg2,
                                     JsResult arg3) {
                /**
                 * 这里写入你自定义的window alert
                 */
                return super.onJsAlert(arg0, arg1, arg2, arg3);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);


            }


            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }


            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                //            showpop(view, icon);
            }

            @Override
            public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
                super.onReceivedTouchIconUrl(view, url, precomposed);
            }

            @Override
            public boolean onJsTimeout() {
                //                showToast("加载超时");
                return super.onJsTimeout();
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String s, GeolocationPermissionsCallback geolocationPermissionsCallback) {
                super.onGeolocationPermissionsShowPrompt(s, geolocationPermissionsCallback);
            }


            @Override
            public void onGeolocationPermissionsHidePrompt() {
                super.onGeolocationPermissionsHidePrompt();
            }

        });
        long time = System.currentTimeMillis();
        mTenWebView.loadUrl(mIntentUrl.toString());
        getTitleView().setTitle(mTenWebView.getTitle());

        TbsLog.d("time-cost", "cost time: "
                + (System.currentTimeMillis() - time));

        mTenWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s1, String s2, String s3, long l) {
                downloadByBrowser(s);
            }

        });


    }

    private void setAndroidCallJavascript(String functionName, String value) {
        Log.e("chumu", "setAndroidCallJavascript: " + "=========" + value);
        String js;
        if (value != null) {
            js = "javascript:" + functionName + "(" + value + ")";
        } else {
            js = "javascript:" + functionName + "()";
        }

        mTenWebView.evaluateJavascript(js, new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {

            }
        });
    }


    @Override
    public void initData() {

    }


    public class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.loadUrl(request.getUrl().toString());
            } else {
                view.loadUrl(request.toString());
            }
            return true;
        }

        @Override
        public void onLoadResource(WebView webView, String s) {
            super.onLoadResource(webView, s);
        }


    }


    @Override
    protected void onStop() {
        super.onStop();

    }

    //webview打开浏览器下载
    private void downloadByBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTenWebView != null)
            //            mTenWebView.loadUrl("about:blank");
            mTenWebView.destroy();

    }
}





