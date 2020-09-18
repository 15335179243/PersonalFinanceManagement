package com.chumu.jianzhimao.ui.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.activity.EditNicknameActivity;
import com.chumu.jianzhimao.ui.adapter.MyListAdapter;
import com.chumu.jianzhimao.ui.mvp.UserModle;
import com.chumu.jianzhimao.ui.mvp.bean.MyLisInfo;
import com.example.common_base.ConstantConfig;
import com.example.common_base.base.BaseMvpFragment;
import com.example.common_base.design.RoundImage;
import com.example.common_base.utils.GlideEngine;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;


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
    ;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {
        mListRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MyListAdapter();
        mListRlv.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                MyLisInfo.onSwitchPage(getActivity(), position );
            }
        });
    }

    @Override
    public void initData() {
        mAdapter.setList(MyLisInfo.getMyListInfoData());
    }

    @Override
    public UserModle getModel() {
        return new UserModle();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

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
                startActivity(new Intent(getContext(), EditNicknameActivity.class).putExtra(ConstantConfig.User.NICKNAME,"123456"));
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
                    break;
            }
        }
    }

    public static String getPath(LocalMedia media) {
        if (media == null) return null;
        if (!TextUtils.isEmpty(media.getAndroidQToPath())) {
            return media.getAndroidQToPath();
        }
        return media.getPath();
    }

}
