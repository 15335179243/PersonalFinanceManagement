package com.chumu.jianzhimao.ui.activity.webview;

import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.chumu.jianzhimao.R;
import com.example.common_base.AppConfig;
import com.example.common_base.base.BaseActivity;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;


public class AgreementActivity extends BaseActivity {

    private FrameLayout mWebView;
    private X5WebView mTenWebView;
    private ImageView mImg;
    private Toolbar mToolbar;
    /**
     * 用户协议
     */
    private TextView mTvTitle;
    private View mErrorView;
    private boolean mIsErrorPage;


    @Override
    protected int onCreateContentView() {
        return R.layout.activity_agreement;
    }

    @Override
    public void initView() {

        mWebView = (FrameLayout) findViewById(R.id.webView);
        mTenWebView = new X5WebView(this, null);
        mWebView.addView(mTenWebView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        initWeb();
    }

    private void initWeb() {
        mTenWebView.setWebViewClient(new MyWebViewClient());
        int type = getIntent().getIntExtra("type", -1);
        if (type == AppConfig.WebView.USER_AGREEMENT) {
            getTitleView().setTitle("用户协议");
            mTenWebView.loadUrl("https://tanrice.top/xieyi/xieyi.html");
        } else {
            getTitleView().setTitle("隐私协议");
            mTenWebView.loadUrl("https://tanrice.top/xieyi/privacyProtocol.html");
        }
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
        public void onReceivedError(WebView webView, int i, String s, String s1) {
            showErrorPage(webView);
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            if (!mIsErrorPage) {
                hideErrorPage(webView);

            }
            mIsErrorPage = false;
        }
    }


    protected void showErrorPage(WebView webView) {
        FrameLayout webParentView = (FrameLayout) webView.getParent();
        initErrorPage(webView);//初始化自定义页面
        while (webParentView.getChildCount() > 1) {
            webParentView.removeViewAt(1);
        }
        @SuppressWarnings("deprecation")
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        webParentView.addView(mErrorView, 1, lp);

    }

    protected void hideErrorPage(WebView webView) {
        FrameLayout webParentView = (FrameLayout) webView.getParent();
        while (webParentView.getChildCount() > 2) {
            webParentView.removeViewAt(1);

        }
    }

    protected void initErrorPage(final WebView webView) {
        if (mErrorView == null) {
            mErrorView = View.inflate(this, R.layout.activity_url_error, null);
            Button button = (Button) mErrorView.findViewById(R.id.btn_refresh);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    webView.onResume();
                    webView.reload();

                }
            });
            mErrorView.setOnClickListener(null);
        }

    }
}
