package com.chumu.jianzhimao.ui.mvp;

import com.example.common_base.base.ICommonModel;
import com.example.common_base.base.ICommonView;
import com.example.common_base.base.NetManager;

/**
 * JianZhiMao
 * <p>
 * Created by ChuMu on 2020/9/6
 * Copyright © 2020年 ChuMu. All rights reserved.
 * <p>
 * Describe:
 */

public class UserModle implements ICommonModel {

    NetManager mManager = NetManager.getNetManager();

    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        switch (whichApi) {
            default:
                break;
        }
    }
}