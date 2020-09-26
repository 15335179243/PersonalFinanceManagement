package com.chumu.jianzhimao.ui.activity.webview;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.common_base.AppConfig;
import com.example.common_base.base.NotSignException;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.CALL_PHONE;


public class JSCallback extends WebViewJsInterface.BaseWebViewJsInterface {
    private static setJSCallbackImg mOnJSCallbackimg;
    String tag = "JSCallback";
    public List<String> mImageList;
    public int mIndex;
    public View mWebActivity;
    private static final String WeiXin_PACKAGE_NAME = "com.tencent.mm";
    private DownloadClickCallBack onDownloadcallBack;
    private JSCallback.setAppIdCallBack setAppIdCallBack;

    public JSCallback(X5WebView mWebActivity) {
        this.mWebActivity = mWebActivity;
    }

    @JavascriptInterface
    @Override
    public void startAndroidNumber(int type, String number) {
        switch (type) {
            default:
                break;
            case AppConfig.WebView.QQ:
                startQQ(number);
                break;
            case AppConfig.WebView.WEI_XIN:
                startWeiXin();
                break;
            case AppConfig.WebView.PHONE:
                startPhone(number);
                break;


        }

    }

    private void startQQ(String number) {
        if (checkApkExist(mWebActivity.getContext(), "com.tencent.mobileqq")) {
            mWebActivity.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + number + "&version=1")));
        } else {
            Snackbar.make(mWebActivity.getRootView(), "木有检测到QQ客户端 T T", Snackbar.LENGTH_SHORT).show();
        }

    }

    private void startPhone(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri parse = Uri.parse("tel:" + number);
        intent.setData(parse);
        if (ActivityCompat.checkSelfPermission(mWebActivity.getContext(), CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mWebActivity.getContext().startActivity(intent);
    }


    @JavascriptInterface
    public boolean onGoTodownload(String downloadUrl, String appId) {
        Log.e("chumu", "onGoTodownload: " + downloadUrl + "   " + appId);
        if (onDownloadcallBack != null) {
            onDownloadcallBack.SetDownloadClick(downloadUrl, appId);
        }
        return true;
    }

    @JavascriptInterface
    public void isStartCall(boolean tag) {
        Log.e("chumu", "isStartCall: " + tag);

    }

    @JavascriptInterface
    public void getDownload(String appID) {
        Log.e("appaid", "getDownload: " + appID);
        if (setAppIdCallBack != null) {
            setAppIdCallBack.setAppId(appID);
        }

    }

    @JavascriptInterface
    public String getAppVersion() {
        return getVersionName(mWebActivity.getContext());
    }

    public String getVersionName(Context context) {
        // 获取packagemanager的实例
        String version = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            version = packInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPackageName() {
        try {
            Class var0 = Class.forName("android.app.ActivityThread");
            Method var1 = var0.getDeclaredMethod("currentPackageName");
            var1.setAccessible(true);
            return (String) var1.invoke((Object) null);
        } catch (Exception var2) {
            return null;
        }
    }


    @JavascriptInterface
    public void startWeiXin() {
        if (hasInstalledAlipayClient(mWebActivity.getContext())) {
            Intent intent = new Intent();
            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            mWebActivity.getContext().startActivity(intent);

        } else {
            Snackbar.make(mWebActivity.getRootView(), "木有检测到微信客户端 T T", Snackbar.LENGTH_SHORT).show();
        }

    }

    public boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


    @JavascriptInterface
    public void getInvitationPictures(String pic) {
        super.getInvitationPictures(pic);
        Log.e("chumuya", "getInvitationPictures: " + pic);

        decoderBase64File(pic, DownloadUtil.get().getPathFile("/分享赚钱计划.png"));

    }


    public void decoderBase64File(String base64Code, File file) {
        String[] split = base64Code.split(",");

        FileOutputStream out = null;
        try {

            byte[] buffer = Base64.decode(split[1], Base64.DEFAULT);
            out = new FileOutputStream(file);
            out.write(buffer);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            MediaStore.Images.Media.insertImage(  mWebActivity.getContext().getContentResolver(),
                    file.getAbsolutePath(), "分享赚钱计划", null);
            //            Toast.makeText(mWebActivity, "保存成功！"+file.getPath(), Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(  mWebActivity.getContext(), "保存失败！！" + file.getPath(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        mWebActivity.getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.parse("file://" + file.getPath())));
        Toast.makeText(  mWebActivity.getContext(), "保存成功！" + file.getPath(), Toast.LENGTH_SHORT).show();
    }


    @JavascriptInterface
    public void logout() {
        throw new NotSignException("Token过期");
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

    public interface setJSCallbackImg {
        void onJSCallbackimg(int index, List<String> list);
    }

    public static void getSCallbackImg(setJSCallbackImg onJSCallbackimg) {
        mOnJSCallbackimg = onJSCallbackimg;
    }

    //判断本机是否安装了微信
    public boolean hasInstalledAlipayClient(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(

                    WeiXin_PACKAGE_NAME, 0);
            return info != null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    public interface DownloadClickCallBack {
        void SetDownloadClick(String downloadUrl, String appId);


    }

    public void setDownloadCallBack(DownloadClickCallBack onDownloadcallBack) {
        this.onDownloadcallBack = onDownloadcallBack;

    }

    public interface setAppIdCallBack {
        void setAppId(String appId);


    }

    public void setonAppIdCallBack(setAppIdCallBack setAppIdCallBack) {
        this.setAppIdCallBack = setAppIdCallBack;
    }

}
