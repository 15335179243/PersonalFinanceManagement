package com.chumu.jianzhimao.ui.fragment;


import android.content.Intent;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.chumu.dt.v24.magicbox.livedatabus.ChuMuLiveDataBus;
import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.activity.login.ZhuYuanActivity;
import com.chumu.jianzhimao.ui.adapter.ExpandableListviewAdapter;
import com.chumu.jianzhimao.ui.mvp.HomeModle;
import com.chumu.jianzhimao.ui.mvp.bean.GroutBean;

import com.example.common_base.utils.ToastUtil;
import com.example.common_base.base.BaseApplication;
import com.example.common_base.base.BaseMvpFragment;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.example.common_base.ApiConfig.ADD_GROUP;
import static com.example.common_base.ApiConfig.LOGOUT_GROUP;
import static com.example.common_base.ApiConfig.QUERY_GROUP;


/**
 * A simple {@link Fragment} 裙主.
 */
public class QunZhuFragment extends BaseMvpFragment<HomeModle> {


    private ArrayList<String> groups = new ArrayList<>();
    private ArrayList<ArrayList<GroutBean.DataBean.RowsBean.GroupUserRespBean.UsersBean>> childs = new ArrayList<>();


    //注意，字符数组不要写成{{"A1,A2,A3,A4"}, {"B1,B2,B3,B4，B5"}, {"C1,C2,C3,C4"}}
//    private String[][] childs = {{"赵珊珊", "钱丹丹", "孙可可", "李冬冬"}, {"周大福", "吴端口", "郑非", "王疯狂"}, {"冯程程", "陈类", "楚哦", "魏王"}};

    @BindView(R.id.expand_list_id)
    ExpandableListView expandListId;
    private int page = 1;
    private GroutBean mTestBean;
    private int mGroupPosition;
    private ExpandableListviewAdapter mAdapter;
    private List<GroutBean.DataBean.RowsBean> mRows;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_qunzhu;
    }

    @Override
    public void initView() {
        ChuMuLiveDataBus.INSTANCE.with("quznhu").observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object o) {

                initData();
            }
        });
        mAdapter = new ExpandableListviewAdapter(getContext(), groups, childs);
        expandListId.setAdapter(mAdapter);
        //默认展开第一个数组
        expandListId.expandGroup(0);
        //关闭数组某个数组，可以通过该属性来实现全部展开和只展开一个列表功能
        //expand_list_id.collapseGroup(0);
        expandListId.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {


            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                mGroupPosition = groupPosition;
                if (expandableListView.isGroupExpanded(groupPosition)) {
                    expandableListView.expandGroup(groupPosition);
                } else {
                    expandableListView.collapseGroup(groupPosition);
                }

                registerForContextMenu(expandableListView);
                return false;
            }
        });
        //子视图的点击事件
        expandListId.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {

                Intent intent = new Intent(requireActivity(), ZhuYuanActivity.class);
                intent.putExtra("name", childs.get(groupPosition).get(childPosition).getNickName());
                intent.putExtra("MoneyLimit", childs.get(groupPosition).get(childPosition).getMoneyLimit());
                intent.putExtra("HeadPhoto", childs.get(groupPosition).get(childPosition).getHeadPhoto());
                intent.putExtra("Finance", mRows.get(groupPosition).getGroupUserResp().getUserFinanceList().get(childPosition).getFinance());
                requireActivity().startActivity(intent);
                return true;
            }
        });
        //用于当组项折叠时的通知。
        expandListId.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

                ToastUtil.toastShortMessage("折叠了数据___" + groups.get(groupPosition));
            }
        });
        //
        //用于当组项折叠时的通知。
        expandListId.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                ToastUtil.toastShortMessage("展开了数据___" + groups.get(groupPosition));
            }
        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    //当ContextMenu被选中的时候处理具体的响应事件
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action1:
                mPresenter.getData(ADD_GROUP, mTestBean.getData().getRows().get(mGroupPosition).getId(), BaseApplication.mToken);
                return false;
            case R.id.action2:

                mPresenter.getData(LOGOUT_GROUP, mTestBean.getData().getRows().get(mGroupPosition).getId(), BaseApplication.mToken);
                return false;

            default:

        }
        return false;
    }


    @Override
    public void initData() {
        show();
        mPresenter.getData(QUERY_GROUP, page, 20);

    }

    @Override
    public HomeModle getModel() {
        return new HomeModle();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        hide();
        String str = (String) t[0];
        switch (whichApi) {
            default:
                break;
            case QUERY_GROUP:
                mTestBean = new Gson().fromJson(str, GroutBean.class);
                if (mTestBean != null && mTestBean.getCode() == 200) {
                    if (mTestBean.getData().getRows() != null) {
                        groups.clear();
                        childs.clear();
                        mRows = mTestBean.getData().getRows();
                        for (int i = 0; i < mTestBean.getData().getRows().size(); i++) {
                            groups.add(mTestBean.getData().getRows().get(i).getGroupName());
                            ArrayList<GroutBean.DataBean.RowsBean.GroupUserRespBean.UsersBean> objects = new ArrayList<>();
                            objects.addAll(mTestBean.getData().getRows().get(i).getGroupUserResp().getUsers());
                            childs.add(objects);

                        }
                        mAdapter.setlist(groups, childs);
                        mAdapter.notifyDataSetChanged();

                    }

                }

                break;

            case LOGOUT_GROUP:
            case ADD_GROUP:
                try {
                    JSONObject jsonObject = new JSONObject(str);


                    if (jsonObject.optInt("code") == 200) {

                        ToastUtil.toastShortMessage("操作成功");

                        initData();
                    } else {
                        ToastUtil.toastShortMessage(jsonObject.optString("desc"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;


        }
    }


}
