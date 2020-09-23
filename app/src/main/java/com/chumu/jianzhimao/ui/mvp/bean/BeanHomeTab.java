package com.chumu.jianzhimao.ui.mvp.bean;

import java.util.List;

/**
 * JianZhiMao
 * <p>
 * Created by ChuMu on 2020/9/23
 * Copyright © 2020年 ChuMu. All rights reserved.
 * <p>
 * Describe: 首页tab
 */

public class BeanHomeTab {

    /**
     * code : 200
     * desc : 成功
     * requestTime : 1600531589296
     * traceId : c0a88901160053158905210094656
     * data : [{"indexType":1,"indexName":"为你推荐"},{"indexType":2,"indexName":"高薪急聘"},{"indexType":3,"indexName":"极速上岗"},{"indexType":4,"indexName":"推荐详情"}]
     */

    private int code;
    private String desc;
    private long requestTime;
    private String traceId;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * indexType : 1
         * indexName : 为你推荐
         */

        private int indexType;
        private String indexName;

        public int getIndexType() {
            return indexType;
        }

        public void setIndexType(int indexType) {
            this.indexType = indexType;
        }

        public String getIndexName() {
            return indexName;
        }

        public void setIndexName(String indexName) {
            this.indexName = indexName;
        }
    }
}
