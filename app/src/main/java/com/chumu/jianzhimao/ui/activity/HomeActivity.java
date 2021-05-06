package com.chumu.jianzhimao.ui.activity;


import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;


import com.chumu.dt.v24.magicbox.ChuMuFramgentManager;
import com.chumu.jianzhimao.R;

import com.chumu.jianzhimao.ui.activity.eys.TestFragment;
import com.chumu.jianzhimao.ui.fragment.HomeFragment;
import com.chumu.jianzhimao.ui.fragment.MyFragment;
import com.chumu.jianzhimao.ui.fragment.QunZhuFragment;
import com.chumu.jianzhimao.ui.fragment.RenMengFragment;
import com.chumu.jianzhimao.ui.zhihu.ZhuhuFragment;
import com.example.common_base.base.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.xm.asus.umenglibr.DynamicPermissions;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {
    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;
    @BindView(R.id.container)
    FrameLayout mContainer;
    private MaterialSearchView mSearchView;
    private MenuItem mSearchMenuItem;
    private MenuItem mAction_2;
    private MenuItem mAction_3;
    private MenuItem mAction_4;

    @Override
    protected int onCreateContentView() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        getTitleView().mBackBtn.setVisibility(View.GONE);

        ChuMuFramgentManager.addFragment(getSupportFragmentManager(), HomeFragment.class, R.id.container);
        getTitleView().setTitle("首页");
        getTitleView().setBackgroundColor(getResources().getColor(R.color.app_theme_color));
        mNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    default:
                        break;
                    case R.id.navigation_home:
                        mAction_2.setVisible(false);
                        mAction_3.setVisible(true);
                        mAction_4.setVisible(false);
                        ChuMuFramgentManager.addFragment(getSupportFragmentManager(), HomeFragment.class, R.id.container);
                        getTitleView().setTitle("账单");
                        break;
                    case R.id.navigation_dashboard:
                        mAction_2.setVisible(false);
                        mAction_3.setVisible(false);
                        mAction_4.setVisible(true);
                        ChuMuFramgentManager.addFragment(getSupportFragmentManager(), TestFragment.class, R.id.container);
                        getTitleView().setTitle("统计");
                        break;
                    case R.id.navigation_notifications:
                        mAction_2.setVisible(true);
                        mAction_3.setVisible(false);
                        mAction_4.setVisible(false);
                        ChuMuFramgentManager.addFragment(getSupportFragmentManager(), QunZhuFragment.class, R.id.container);
                        getTitleView().setTitle("群组");
                        break;
                    case R.id.navigation_zixun:
                        mAction_2.setVisible(false);
                        mAction_3.setVisible(false);
                        mAction_4.setVisible(false);
                        ChuMuFramgentManager.addFragment(getSupportFragmentManager(), ZhuhuFragment.class, R.id.container);
                        getTitleView().setTitle("资讯");
                        break;
                    case R.id.navigation_my:
                        mAction_2.setVisible(false);
                        mAction_3.setVisible(false);
                        mAction_4.setVisible(false);
                        ChuMuFramgentManager.addFragment(getSupportFragmentManager(), MyFragment.class, R.id.container);
                        getTitleView().setTitle("我的");
                        break;
                }

                return true;
            }
        });
    }

    @Override
    public void initData() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_openmenu, menu);
//        MenuItem item = menu.findItem(R.id.search);
        mAction_2 = menu.findItem(R.id.action_2);
        mAction_3 = menu.findItem(R.id.action_3);
        mAction_4 = menu.findItem(R.id.action_4);
        mAction_2.setVisible(false);
        mAction_3.setVisible(true);
        mAction_4.setVisible(false);
//        item.setVisible(false);
//        mSearchView.setMenuItem(item);
//        mSearchMenuItem = item;

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, SetAllActivity.class);
        switch (item.getItemId()) {
            case R.id.action_2:
                intent.putExtra("title", "创建群组");
                intent.putExtra("type", 1);

                break;
            case R.id.action_3:
                intent.putExtra("title", "记一笔");
                intent.putExtra("type", 2);
                break;
            case R.id.action_4:
                intent.putExtra("title", "设置阈值");
                intent.putExtra("type", 0);

                break;
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }


    @OnClick({R.id.navigation})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.navigation:
                break;
        }
    }


}
