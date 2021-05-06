package com.chumu.jianzhimao.ui.zhihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;


import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.utils.CopyButtonLibrary;
import com.chumu.jianzhimao.ui.utils.ToastUtil;
import com.chumu.jianzhimao.ui.zhihu.activity.ScrollingActivity;
import com.chumu.jianzhimao.ui.zhihu.adapter.SpecialAdapter;
import com.chumu.jianzhimao.ui.zhihu.base.BaseModel;
import com.chumu.jianzhimao.ui.zhihu.base.BaseMvpActivity;
import com.chumu.jianzhimao.ui.zhihu.base.BaseView;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanSpecial;
import com.chumu.jianzhimao.ui.zhihu.mvp.contract.SpecialContract;
import com.chumu.jianzhimao.ui.zhihu.mvp.model.SpecialColumnModel;
import com.chumu.jianzhimao.ui.zhihu.mvp.model.SpecialModel;
import com.chumu.jianzhimao.ui.zhihu.mvp.presenter.SpecialPresenter;
import com.xm.asus.umenglibr.Share;

import java.util.List;

import butterknife.BindView;


public class SpecialActivity extends BaseMvpActivity<SpecialPresenter, SpecialColumnModel, SpecialContract.View> implements SpecialContract.View {
String url="http://news-at.zhihu.com/api/4/news/";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private SpecialAdapter mSpecialAdapter;
    private Intent mIntent;
    private int position;

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }


    @Override
    protected int getlayoutViewID() {
        return R.layout.activity_special;
    }

    @Override
    protected void initView() {
        mIntent = getIntent();
        String title = mIntent.getStringExtra("title");
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_action_navigation_arrow_back);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mSpecialAdapter = new SpecialAdapter(this);
        mRlv.setAdapter(mSpecialAdapter);
        mSpecialAdapter.setOnClickListener(new SpecialAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int i) {
                Intent intent = new Intent(SpecialActivity.this, ScrollingActivity.class);
                intent.putExtra("id", mSpecialAdapter.mList.get(i).getId());
                startActivity(intent);
            }
        });
        mSpecialAdapter.setOnLongClickListener(new SpecialAdapter.OnLongClickListener() {
            @Override
            public void onLongClick(View v, int i) {
                position = i;
                registerForContextMenu(v);
            }
        });
    }





    @Override
    protected void initData() {

        mPresenter.getDataP(mIntent.getIntExtra("id", 1));
    }

    @Override
    protected BaseModel initMvpmodel() {
        return new SpecialModel();
    }

    @Override
    protected BaseView initMvpView() {
        return this;
    }

    @Override
    protected SpecialPresenter initMvpPresenter() {
        return new SpecialPresenter();
    }

    private static final String TAG = "SpecialActivity";
    @Override
    public void onSuccess(List<BeanSpecial.StoriesBean> datas) {
        mSpecialAdapter.addlist(datas);
        mSpecialAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String msg) {
        Log.d(TAG, "onFail: "+msg);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(1, 1, 1, "收藏");
        menu.add(1, 2, 1, "复制链接");
        menu.add(1, 3, 1, "分享");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        BeanSpecial.StoriesBean recentBean = mSpecialAdapter.mList.get(position);
        switch (item.getItemId()) {
            default:
                break;
            case 1:

//                DButils.insert(new BeanDb(null, recentBean.getTitle(), recentBean.getId() + "", null, url+recentBean.getId(), recentBean.getImages().get(0)));
//                ToastUtil.showShort("收藏成功");
                break;
            case 2:
                CopyButtonLibrary copyButtonLibrary = new CopyButtonLibrary();
                copyButtonLibrary.init(url+mSpecialAdapter.mList.get(position).getId());
                break;
            case 3:
                Share share = new Share(SpecialActivity.this);
                share.init();
                share.shareLinkAndPic(recentBean.getImages().get(0) ,recentBean.getTitle(), url+recentBean.getId(), recentBean.getTitle());
                break;
        }
        return super.onContextItemSelected(item);
    }


}
