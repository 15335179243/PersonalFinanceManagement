package com.chumu.jianzhimao.ui.fragment;


import androidx.fragment.app.Fragment;

import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.mvp.HomeModle;
import com.example.common_base.base.BaseMvpFragment;

import static com.example.common_base.ApiConfig.GET_HOME_TAB;


/**
 * A simple {@link Fragment} 裙主.
 */
public class EmailFragment extends BaseMvpFragment<HomeModle> {



    @Override
    public int getLayoutId() {
        return R.layout.fragment_qunzhu;
    }

    @Override
    public void initView() {



    }

    private void initTab() {
    }


    @Override
    public void initData() {
        show();
        mPresenter.getData(GET_HOME_TAB);

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
            case GET_HOME_TAB:


                break;
        }
    }


}
