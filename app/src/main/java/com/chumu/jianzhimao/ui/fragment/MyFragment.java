package com.chumu.jianzhimao.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.activity.EditNicknameActivity;
import com.chumu.jianzhimao.ui.adapter.MyListAdapter;
import com.chumu.jianzhimao.ui.mvp.UserModle;
import com.chumu.jianzhimao.ui.mvp.bean.BeanUpload;
import com.chumu.jianzhimao.ui.mvp.bean.MyLisInfo;
import com.example.common_base.ApiConfig;
import com.example.common_base.SPConstant;
import com.example.common_base.base.BaseApplication;
import com.example.common_base.base.BaseMvpFragment;
import com.example.common_base.design.RoundImage;
import com.example.common_base.utils.GlideEngine;
import com.example.common_base.utils.ToastUtil;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.CompressionPredicate;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.compress.OnCompressListener;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;
import static com.chumu.jianzhimao.ui.activity.eys.ImageUtils.uploadBitMap;
import static com.example.common_base.ApiConfig.GET_PERFECT_INFO;
import static com.example.common_base.ApiConfig.UPLOADING;


public class MyFragment extends BaseMvpFragment<UserModle> {
    @BindView(R.id.head_portrait_iv)
    RoundImage mHeadPortraitIv;
    @BindView(R.id.nickname_tv)
    TextView mNicknameTv;
    @BindView(R.id.compile_iv)
    ImageView mCompileIv;
    @BindView(R.id.imageView2)
    ImageView mImageView2;
    @BindView(R.id.list_rlv)
    RecyclerView mListRlv;
    private MyListAdapter mAdapter;
    private List<LocalMedia> selectList;
    private String mPic;
    private String mNickName;
    private String mImgUrl;
    ;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {
        String headPicture = (String) mChuMuSharedPreferences.getValue(SPConstant.Login.HEAD_PICTURE, "");
        mNickName = (String) mChuMuSharedPreferences.getValue(SPConstant.Login.NICKNAME, "");
        mNicknameTv.setText(mNickName);
        Glide.with(this).load(headPicture)
                .error(R.drawable.common_base_no_login_head)
                .placeholder(R.drawable.common_base_no_login_head)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mHeadPortraitIv);
        mListRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MyListAdapter();
        mListRlv.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                List<MyLisInfo> data = adapter.getData();

                MyLisInfo.onSwitchPage(getActivity(), data.get(position).getItemId());
            }
        });
    }

    @Override
    public void initData() {
        mAdapter.setNewData(MyLisInfo.getMyListInfoData());
    }

    @Override
    public UserModle getModel() {
        return new UserModle();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Log.e("chumu", "onError: " + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        hide();
        String str = (String) t[0];
        JSONObject jsonObject = null;
        try {
            if (str != null) {

                jsonObject = new JSONObject(str);
            }
            if (jsonObject == null) {
                return;
            }
            switch (whichApi) {
                default:
                    break;
                case ApiConfig.UPLOADING:
                    if (jsonObject.opt("code") != null && jsonObject.getInt("code") == 200) {
                        mChuMuSharedPreferences.putValue(SPConstant.Login.NICKNAME, mNickName);
                        mChuMuSharedPreferences.putValue(SPConstant.Login.HEAD_PICTURE, mImgUrl);
                    } else {
                        ToastUtil.toastShortMessage(jsonObject.getString("desc"));


                    }
                    break;
                case ApiConfig.GET_PERFECT_INFO:
                    try {


                        if (jsonObject.getInt("code") == 200) {
                            mChuMuSharedPreferences.putValue(SPConstant.Login.HEAD_PICTURE, mNickName);

                        } else {
                            ToastUtil.toastShortMessage(jsonObject.getString("desc"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;


            }

        } catch (
                JSONException e) {
            e.printStackTrace();
        }

    }

    @OnClick({R.id.head_portrait_iv, R.id.nickname_tv, R.id.compile_iv, R.id.imageView2})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.head_portrait_iv:
            case R.id.imageView2:
                pictureSelector();
                break;
            case R.id.nickname_tv:
            case R.id.compile_iv:
                startActivityForResult(new Intent(getContext(), EditNicknameActivity.class).putExtra(SPConstant.Login.NICKNAME, mNickName), 11);

                break;
        }
    }

    private void pictureSelector() {

        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .selectionMode(PictureConfig.SINGLE)
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .selectionData(selectList)// 是否传入已选图片
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    mPic = getPath(selectList.get(0));
                    Glide.with(this).load(mPic).into(mHeadPortraitIv);
                    Log.e("chumu", "onActivityResult: " + selectList.get(0).getPath());
                    onPicZip(selectList);
                    break;
                case 11:
                    mNickName = data.getStringExtra(SPConstant.Login.NICKNAME);
                    mNicknameTv.setText(mNickName.trim());
                    mChuMuSharedPreferences.putValue(SPConstant.Login.NICKNAME, mNickName.trim());
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("token", BaseApplication.mToken);
                        jsonObject.put("nickName", mNickName.trim());
                        String o = (String) JSON.toJSON(jsonObject.toString());
                        Log.e("chumu", "onResponse: " + o);
                        mPresenter.getData(ApiConfig.UPLOADING, o);
                        show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }
    }

    private void onPicZip(List<LocalMedia> pic) {
        Luban.with(getContext())
                .loadMediaData(pic)
                .setTargetDir(getPath())
                .ignoreBy(100)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(List<LocalMedia> list) {
//                        Log.e("chumu", "onSuccess: "+  ) );
                        upLoadImg(new File(getPath(list.get(0))));

                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.e("chumu", "onError: " + e.getMessage());
                        // TODO 当压缩过程出现问题时调用
                    }
                }).launch();
    }

    private String getPath() {
        return getContext().getFilesDir().getPath();
    }


    public static String getPath(LocalMedia media) {
        if (media == null) return null;
        if (!TextUtils.isEmpty(media.getAndroidQToPath())) {
            return media.getAndroidQToPath();
        }
        return media.getPath();
    }

    private void uplodePic(File file) {
        Log.e("chumu", "uplodePic: " + file.getPath());
        MediaType parse = MediaType.parse("multipart/form-data");
        RequestBody body = RequestBody.create(parse, file);//上传的文件
        MediaType formatType = MediaType.parse("*/*");
        RequestBody form = RequestBody.create(formatType, "head");//服务器保存文件的文件夹
        MultipartBody.Part filePart = null;
        try {
            filePart = MultipartBody.Part.createFormData("file", URLEncoder.encode(file.getName(), "UTF-8"), body);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public void upLoadImg(File file) {
        show();
        Log.e("chumu", "uplodePic: " + file.getPath());
        OkHttpClient okHttpClient = new OkHttpClient();
        OkHttpClient client = okHttpClient.newBuilder().readTimeout(10000, TimeUnit.MILLISECONDS).build();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", "chumuemail@163.com")
                .addFormDataPart("password", "qq123456..")
                .build();
        Request request = new Request.Builder()
                .url("https://pic.liesio.com/api/token")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("chumu", "onResponse: t  " + e);
                hide();
                ToastUtil.toastShortMessage("上传失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String token = null;
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    int code = jsonObject.getInt("code");
                    if (code == 200) {
                        token = jsonObject.getJSONObject("data").getString("token");
                        String finalToken = token;

                        uploadImg(file, finalToken);

                        Log.e("chumu", "onResponse: " + token);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void uploadImg(File file, String token) {

        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        MultipartBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", file.getName(), fileBody)
                .build();
        Request uploadRequest = new Request.Builder()
                .url("https://pic.liesio.com/api/upload")
                .post(requestBody)

                .addHeader("token", token)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        OkHttpClient client = okHttpClient.newBuilder().readTimeout(10000, TimeUnit.MILLISECONDS).build();
        client.newCall(uploadRequest).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("chumu", "onResponse: " + e);
                hide();
                ToastUtil.toastShortMessage("上传失败");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                JSONObject jsonObject = null;
                try {
                    String string = response.body().string();
                    Log.e("chumu1", "onResponse: " + string);
                    jsonObject = new JSONObject(string);

                    int code = jsonObject.getInt("code");
                    if (code == 200) {
                        mImgUrl = jsonObject.getJSONObject("data").getString("url");
                        //todo上传成功以后的链接
                        mChuMuSharedPreferences.putValue(SPConstant.Login.HEAD_PICTURE, mImgUrl);
                        ToastUtil.toastShortMessage("修改头像成功");
                        JSONObject jsonObject1 = new JSONObject();
                        jsonObject1.put("token", BaseApplication.mToken);
                        jsonObject1.put("headPhoto", mImgUrl);
                        String o = (String) JSON.toJSON(jsonObject1.toString());
                        Log.e("chumu", "onResponse: " + o);
                        mPresenter.getData(ApiConfig.UPLOADING, o);
                        show();
                        Log.e("chumu", "onResponse: " + mImgUrl);

                    } else {
                        hide();
                        ToastUtil.toastShortMessage("上传失败");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @SuppressLint("NewApi")
    private static String getRealPathFromUriAboveApi19(Activity activity, Uri uri) {
        String filePath = null;
        if (DocumentsContract.isDocumentUri(activity, uri)) {
            // 如果是document类型的 uri, 则通过document id来进行处理
            String documentId = DocumentsContract.getDocumentId(uri);
            if (isMediaDocument(uri)) {
                // MediaProvider
                // 使用':'分割
                String id = documentId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = {id};
                filePath = getDataColumn(activity, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection, selectionArgs);
            } else if (isDownloadsDocument(uri)) {
                // DownloadsProvider
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                filePath = getDataColumn(activity, contentUri, null, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是 content 类型的 Uri
            filePath = getDataColumn(activity, uri, null, null);
        } else if ("file".equals(uri.getScheme())) {
            // 如果是 file 类型的 Uri,直接获取图片对应的路径
            filePath = uri.getPath();
        }
        return filePath;
    }

    /**
     * 获取数据库表中的 _data 列，即返回Uri对应的文件路径
     *
     * @return
     */
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        String path = null;
        String[] projection = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
                path = cursor.getString(columnIndex);
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
        }
        return path;
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


}
