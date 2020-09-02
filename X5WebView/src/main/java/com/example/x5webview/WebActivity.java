package com.example.x5webview;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.example.x5webview.utils.JSCallback;
import com.example.x5webview.utils.X5WebView;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.smtt.utils.TbsLog;

public class WebActivity extends Activity {
    private boolean doubleBack = false; //双击退出应用


    private Context mContext = null;
    private FrameLayout mWebView;
    private X5WebView mTenWebView;
    private CharSequence mIntentUrl;
    private static final String mHomeUrl = "http://app.html5.qq.com/navi/index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mIntentUrl = getIntent().getStringExtra("weburl");
        initView();
        WebInit();
        mContext = this;

    }
    @SuppressLint("AddJavascriptInterface")
    private void WebInit() {
        mTenWebView = new X5WebView(this, null);

        mWebView.addView(mTenWebView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.FILL_PARENT,
                FrameLayout.LayoutParams.FILL_PARENT));

        mTenWebView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                return super.shouldOverrideUrlLoading(webView, s);
            }

        });

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
                Toast.makeText(mContext, "弹窗", Toast.LENGTH_SHORT).show();
                return super.onJsAlert(arg0, arg1, arg2, arg3);
            }
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
//                showLog("onProgressChanged:" + newProgress);
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

        WebSettings webSetting = mTenWebView.getSettings();
        // 设置允许JS弹窗
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0)
                .getPath());
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // webSetting.setPreFectch(true);
        //        mTenWebView.setWebViewClient(new MyWebViewClient());
        mTenWebView.addJavascriptInterface(new JSCallback(mTenWebView), "Android");
        long time = System.currentTimeMillis();
        if (mIntentUrl == null) {
            mTenWebView.loadUrl(mHomeUrl);
            //            mTenWebView.loadUrl("javascript:receiveCallFromOc("+APPAplication.token+")");
            //            mTenWebView.evaluateJavascript("javascript:androidtojsargs("+APPAplication.token+")", new ValueCallback<String>() {
            //                @Override
            //                public void onReceiveValue(String s) {
            //
            //                }
            //            });
        } else {

            mTenWebView.loadUrl(mIntentUrl.toString());

        }
        TbsLog.d("time-cost", "cost time: "
                + (System.currentTimeMillis() - time));



    }


    private void tbsSuiteExit() {
        // exit TbsSuite?
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setTitle("畅玩盒子");
        dialog.setNegativeButton("取消", null);
        dialog.setPositiveButton("残忍退出", new AlertDialog.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Process.killProcess(Process.myPid());
            }
        });
        dialog.setMessage("是否需要帮您退出!");
        dialog.create().show();
    }

    private void initView() {
        mWebView = (FrameLayout) findViewById(R.id.webView);
    }

    public class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);

        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);

        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

            return super.onJsAlert(view, url, message, result);
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

            return super.onJsTimeout();
        }



        @Override
        public void onGeolocationPermissionsHidePrompt() {
            super.onGeolocationPermissionsHidePrompt();
        }
    }


    public class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Uri url = request.getUrl();
            }
            return super.shouldOverrideUrlLoading(view, request);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            String url = mTenWebView.getUrl();
            if (mTenWebView != null && mTenWebView.canGoBack()) {
                //返回上一页
                mTenWebView.goBack();

            } else {
                //双击退出
                if (doubleBack) {
                    finish();
                } else {
                    Toast.makeText(this, "再次点击返回键退出", Toast.LENGTH_SHORT).show();
                    doubleBack = true;
                    mTenWebView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            doubleBack = false;
                        }
                    }, 3000);
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
