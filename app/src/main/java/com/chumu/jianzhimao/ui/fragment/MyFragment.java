package com.chumu.jianzhimao.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.adapter.MyListAdapter;
import com.chumu.jianzhimao.ui.mvp.UserModle;
import com.example.common_base.base.BaseMvpFragment;
import com.example.common_base.design.RoundImage;

import butterknife.BindView;
import butterknife.OnClick;


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

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {
        mListRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MyListAdapter();
        mListRlv.setAdapter(mAdapter);

    }

    @Override
    public void initData() {

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

                break;
            case R.id.nickname_tv:
            case R.id.compile_iv:
                break;
        }
    }
}
