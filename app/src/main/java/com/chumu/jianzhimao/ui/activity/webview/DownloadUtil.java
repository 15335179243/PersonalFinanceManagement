package com.chumu.jianzhimao.ui.activity.webview;

import android.annotation.TargetApi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;


import com.chumu.jianzhimao.BuildConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.chumu.jianzhimao.ui.activity.webview.JSCallback.getPackageName;


/**
 * @author: hgb
 * @createTime: 2019/10/14
 * @description:
 * @changed by:
 */
public class DownloadUtil {

    private static DownloadUtil downloadUtil;
    private final OkHttpClient okHttpClient;
    private static ArrayList<String> mList;


    public static DownloadUtil get() {
        if (downloadUtil == null) {
            downloadUtil = new DownloadUtil();
            mList = new ArrayList<>();
        }
        return downloadUtil;
    }

    private DownloadUtil() {
        okHttpClient = new OkHttpClient();
    }

    /**
     * @param url      下载连接
     * @param saveDir  储存下载文件的SDCard目录
     * @param listener 下载监听
     */
    public void download(final String url, final Activity activity, final String saveDir, final String appId, final OnDownloadListener listener) {


        long downLoadLength = 0;
        File file = null;
        final long contentLength = getContentLength(url);
        try {
            String savePath = isExistDir(saveDir);
            file = new File(savePath, getNameFromUrl(url));
            if (file.exists()) {
                downLoadLength = file.length();

                if (contentLength == 0) {
                    listener.onDownloadFailed(appId);
                    return;
                } else if (contentLength == downLoadLength) {
                    //如果已下载的字节和文件总字节相等，说明已经下载完成了
                    DownloadUtil.get().installApk(activity, file.getPath(), "download");
                    listener.onDownloadSuccess(file, appId);
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mList.contains(appId)) {
            mList.add(appId);
            Toast.makeText(activity, "当前网络连接较慢!请检查网络连接", Toast.LENGTH_SHORT).show();
            return;
        }
        Request request = new Request.Builder().url(url)
                .addHeader("RANGE", "bytes=" + downLoadLength + "-" + contentLength) //断点继续下载
                .tag(activity).build();
        final long finalDownLoadLength = downLoadLength;
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                listener.onDownloadFailed(appId);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = null;
                byte[] buf = new byte[2048];

                RandomAccessFile randomAccessFile = null;
                // 储存下载文件的目录
                String savePath = isExistDir(saveDir);
                File file = new File(savePath, getNameFromUrl(url));
                int total = 0;
                int len = 0;

                try {
                    inputStream = response.body().byteStream();
                    randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(finalDownLoadLength);//跳过已下载的字节
                    while ((len = inputStream.read(buf)) != -1) {
                        total += len;
                        randomAccessFile.write(buf, 0, len);
                        int progress = (int) ((total + finalDownLoadLength) * 100 / contentLength);
                        // 下载中
                        listener.onDownloading(progress, appId);
                    }
                    // 下载完成
                    listener.onDownloadSuccess(file, appId);
                } catch (Exception e) {
                    listener.onDownloadFailed(appId);
                } finally {
                    try {
                        if (inputStream != null)
                            inputStream.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (randomAccessFile != null)
                            randomAccessFile.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    /**
     * @param saveDir
     * @return
     * @throws IOException 判断下载目录是否存在
     */
    private String isExistDir(String saveDir) throws IOException {
        // 下载位置
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            File downloadFile = new File(Environment.getExternalStorageDirectory(), saveDir);
            if (!downloadFile.mkdirs()) {
                downloadFile.createNewFile();
            }
            String savePath = downloadFile.getAbsolutePath();
            Log.e("savePath", savePath);
            return savePath;
        }
        return null;
    }

    /**
     * @param url
     * @return 从下载连接中解析出文件名
     */
    private String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }


    /*
     * 下载完成以后安装apk
     *
     * */
    public void installApk(Context context, String fileUri, String saveDir) {
        try {
            Intent paramIntent = new Intent("android.intent.action.VIEW");
            paramIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri;
            // 7.0+ 需要将file://的uri转换为更安全的content://,并且增加读取uri权限
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                String path = "";
                if (fileUri != null) {
                    path = Uri.parse(fileUri).getPath();
                }
                uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", new File(path));
                paramIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                //            //兼容8.0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (!context.getPackageManager().canRequestPackageInstalls()) {
                        startInstallPermissionSettingActivity(context);
                    }
                }
            } else {
                uri = Uri.fromFile(new File(fileUri));
            }
            //设置打开apk格式文件
            paramIntent.setDataAndType(uri, "application/vnd.android.package-archive");
            handleDownloadedApk(context, paramIntent, saveDir);
        } catch (Exception e) {
            e.printStackTrace();
            openLocalDir(context, saveDir);
        }
    }

    private static void handleDownloadedApk(Context context, Intent paramIntent, String saveDir) {
        if (paramIntent.resolveActivity(context.getPackageManager()) != null) {
            //存在安装apk的activity,则打开安装界面
            context.startActivity(paramIntent);

        } else {
            //不存在安装apk的activity，则打开apk所在目录
            openLocalDir(context, saveDir);

        }
    }

    private static void openLocalDir(Context context, String saveDir) {
        //调用系统文件管理器打开指定路径目录

        //获取到指定文件夹，这里为：/storage/emulated/0/Android/data/你的包	名/files/Download
        //        File file = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(Environment.getExternalStorageDirectory(), saveDir);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //7.0以上跳转系统文件需用FileProvider，参考链接：https://blog.csdn.net/growing_tree/article/details/71190741
        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", file);
        Log.e("uri", "openLocalDir: " + uri.getPath());
        intent.setDataAndType(uri, "file/*");
        //        intent.setData(uri);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转到设置-允许安装未知来源-页面
     */
    @TargetApi(Build.VERSION_CODES.O)
    private static void startInstallPermissionSettingActivity(Context context) {
        //后面跟上包名，可以直接跳转到对应APP的未知来源权限设置界面。使用startActivityForResult 是为了在关闭设置界面之后，获取用户的操作结果，然后根据结果做其他处理
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + BuildConfig.APPLICATION_ID));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    //根据路径获取文件
    public File getPathFile(String path) {
        String apkName = path.substring(path.lastIndexOf("/"));
        File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), apkName);
        Log.e("outputFile", "getPathFile: " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
        return outputFile;
    }

    //删除文件
    public void rmoveFile(String path) {
        File file = getPathFile(path);
        file.delete();
    }

    //获取当前客户端的版本号
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

    public interface OnDownloadListener {
        /**
         * 下载成功
         */
        void onDownloadSuccess(File str, String appid);

        /**
         * @param progress 下载进度
         */
        void onDownloading(int progress, String appid);

        /**
         * 下载失败
         */
        void onDownloadFailed(String appid);
    }

    //页面关闭时候主动切断网络请求
    public void cancelTag(Object tag) {
        for (Call call : okHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : okHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    //根据包名判断是否有没有装这个APP
    public boolean isAppInstalled(Context context, String packagename) {

        PackageManager pm = context.getPackageManager();

        boolean installed = false;

        try {

            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);

            installed = true;

        } catch (PackageManager.NameNotFoundException e) {

            installed = false;

        }

        return installed;

    }

    //通过包名启动APP
    public void startAPP(Context context, String appPackageName) {
        Log.e("chumu", "startAPP: ");
        try {
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(appPackageName);
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "请检查是否安装！！！", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 获取文件长度
     *
     * @param downLoadUrl
     * @return
     */
    private long getContentLength(final String downLoadUrl) {


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(downLoadUrl).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            response.body().close();
            return contentLength;
        } else

            return 0;
    }
}
