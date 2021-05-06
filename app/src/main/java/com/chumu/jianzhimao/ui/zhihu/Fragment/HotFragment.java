package com.chumu.jianzhimao.ui.zhihu.Fragment;


import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;


import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.utils.CopyButtonLibrary;
import com.chumu.jianzhimao.ui.utils.Logger;
import com.chumu.jianzhimao.ui.zhihu.activity.ScrollingActivity;
import com.chumu.jianzhimao.ui.zhihu.adapter.RlvHotAdapter;
import com.chumu.jianzhimao.ui.zhihu.base.BaseModel;
import com.chumu.jianzhimao.ui.zhihu.base.BaseMvpFragment;
import com.chumu.jianzhimao.ui.zhihu.base.BaseView;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanHot;
import com.chumu.jianzhimao.ui.zhihu.mvp.contract.HotContract;
import com.chumu.jianzhimao.ui.zhihu.mvp.model.HotModel;
import com.chumu.jianzhimao.ui.zhihu.mvp.presenter.HotPresenter;
import com.example.common_base.utils.ToastUtil;
import com.xm.asus.umenglibr.Share;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 *
 */
public class HotFragment extends BaseMvpFragment<HotPresenter, HotModel, HotContract.View> implements HotContract.View {

    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private View view;
    private Unbinder unbinder;
    private RlvHotAdapter mRlvHotAdapter;
    private int position;
    private Context mContext;

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_zhihu_hot;
    }

    @Override
    public void initView(View inflate) {
        mContext = inflate.getContext();
        mRlv.setLayoutManager(new LinearLayoutManager(mContext));
        mRlv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mRlvHotAdapter = new RlvHotAdapter(mContext);
        mRlv.setAdapter(mRlvHotAdapter);
        mRlvHotAdapter.setOnLongClickListener(new RlvHotAdapter.OnLongClickListener() {
            @Override
            public void onLongClick(View v, int i) {
                position = i;
                registerForContextMenu(v);
            }
        });
        mRlvHotAdapter.setOnClickListener(new RlvHotAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int i) {
                Intent intent = new Intent(getActivity(), ScrollingActivity.class);
                intent.putExtra("id", mRlvHotAdapter.mList.get(i).getNews_id());
                startActivity(intent);
            }
        });
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
        BeanHot.RecentBean recentBean = mRlvHotAdapter.mList.get(position);
        switch (item.getItemId()) {
            default:
                break;
            case 1:

//                DBu tils.insert(new BeanDb(null, recentBean.getTitle(), recentBean.getNews_id() + "", null, recentBean.getUrl(), recentBean.getThumbnail()));
//                ToastUtil.showShort("收藏成功");
                break;
            case 2:
                CopyButtonLibrary copyButtonLibrary = new CopyButtonLibrary();
                copyButtonLibrary.init(mRlvHotAdapter.mList.get(position).getUrl());
                break;
            case 3:
                Share share = new Share(getActivity());
                share.init();
                share.shareLinkAndPic(recentBean.getThumbnail(), recentBean.getTitle(), recentBean.getUrl(), recentBean.getTitle());
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected BaseModel initMvpmodel() {
        return new HotModel();
    }

    @Override
    public void initData() {
        mPresenter.getDataP();
    }

    @Override
    protected BaseView initMvpView() {
        return this;
    }

    @Override
    protected HotPresenter initMvpPresenter() {
        return new HotPresenter();
    }


    @Override
    public void onSuccess(List<BeanHot.RecentBean> datas) {
        mRlvHotAdapter.addlist(datas);
    }

    @Override
    public void onFail(String msg) {
        Logger.logD("HotFragment", msg);
    }
}
