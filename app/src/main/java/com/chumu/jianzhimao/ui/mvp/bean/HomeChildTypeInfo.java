package com.chumu.jianzhimao.ui.mvp.bean;

import com.example.common_base.ChuMuData;

/**
 * JianZhiMao
 * <p>
 * Created by ChuMu on 2020/9/25
 * Copyright © 2020年 ChuMu. All rights reserved.
 * <p>
 * Describe:
 */

public class HomeChildTypeInfo  extends ChuMuData {
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public HomeChildTypeInfo(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "HomeChildTypeInfo{" +
                "label='" + label + '\'' +
                '}';
    }
}
