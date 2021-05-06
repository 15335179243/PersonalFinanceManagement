package com.chumu.jianzhimao.ui.zhihu.Fragment;



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
import com.chumu.jianzhimao.ui.zhihu.adapter.RlvDailyAdapter;
import com.chumu.jianzhimao.ui.zhihu.base.BaseModel;
import com.chumu.jianzhimao.ui.zhihu.base.BaseMvpFragment;
import com.chumu.jianzhimao.ui.zhihu.base.BaseView;
import com.chumu.jianzhimao.ui.zhihu.bean.BeanNewsLatest;
import com.chumu.jianzhimao.ui.zhihu.mvp.contract.DailyContract;
import com.chumu.jianzhimao.ui.zhihu.mvp.model.DailyModel;
import com.chumu.jianzhimao.ui.zhihu.mvp.presenter.DailyPresenter;

import com.example.common_base.utils.ToastUtil;
import com.xm.asus.umenglibr.Share;

import butterknife.BindView;

/**
 *
 */
public class DailyFragment extends BaseMvpFragment<DailyPresenter, DailyModel, DailyContract.View> implements DailyContract.View {


    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private RlvDailyAdapter mRlvDailyAdapter;
    private int position;


    @Override
    protected int getlayoutId() {
        return R.layout.fragment_zhihu_daily;
    }
    @Override
    protected BaseModel initMvpmodel() {
        return new DailyModel();
    }

    @Override
    protected BaseView initMvpView() {
        return this;
    }

    @Override
    protected DailyPresenter initMvpPresenter() {
        return new DailyPresenter();
    }

    @Override
    public void initData() {
        mPresenter.getDataP();
    }

    @Override
    public void initView(View inflate) {
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRlv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRlvDailyAdapter = new RlvDailyAdapter(getContext());
        mRlv.setAdapter(mRlvDailyAdapter);
        mRlvDailyAdapter.setOnClickListener(new RlvDailyAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int i) {
                Intent intent = new Intent(getActivity(), ScrollingActivity.class);
                intent.putExtra("id",mRlvDailyAdapter.mList.get(i).getId());
                startActivity(intent);
            }
        });
        mRlvDailyAdapter.setOnLongClickListener(new RlvDailyAdapter.OnLongClickListener() {
            @Override
            public void onLongClick(View v, int i) {
                position = i;
                registerForContextMenu(v);
            }
        });
    }


    @Override
    public void onSuccess(BeanNewsLatest datas) {
        if (datas.getStories()!=null&&datas.getStories().size()>0&&datas.getTop_stories()!=null&&datas.getTop_stories().size()>0) {
            mRlvDailyAdapter.addlist(datas.getStories());
            mRlvDailyAdapter.addlistTop(datas.getTop_stories());
            mRlvDailyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFail(String msg) {
        Logger.logD("DailyFragment",msg);
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
        BeanNewsLatest.StoriesBean recentBean = mRlvDailyAdapter.mList.get(position);
        switch (item.getItemId()) {
            default:
                break;
            case 1:

//                DButils.insert(new BeanDb(null,recentBean.getTitle(),recentBean.getId()+"",null,"http://news-at.zhihu.com/api/2/news/"+recentBean.getId(),recentBean.getImages().get(0)));
//                ToastUtil.showShort("收藏成功");
                break;
            case 2:
                CopyButtonLibrary copyButtonLibrary = new CopyButtonLibrary();
                copyButtonLibrary.init("http://news-at.zhihu.com/api/2/news/"+mRlvDailyAdapter.mList.get(position).getId());
                break;
            case 3:
                Share share = new Share(getActivity());
                share.init();
                share.shareLinkAndPic(recentBean.getImages().get(0),recentBean.getTitle(),"http://news-at.zhihu.com/api/2/news/"+recentBean.getId(),recentBean.getTitle());
                break;
        }
        return super.onContextItemSelected(item);
    }
}
