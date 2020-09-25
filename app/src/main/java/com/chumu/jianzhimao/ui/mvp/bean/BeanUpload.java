package com.chumu.jianzhimao.ui.mvp.bean;

/**
 * JianZhiMao
 * <p>
 * Created by ChuMu on 2020/9/25
 * Copyright © 2020年 ChuMu. All rights reserved.
 * <p>
 * Describe:上传头像
 */

public class BeanUpload {

    /**
     * code : 200
     * desc : 成功
     * requestTime : 1600999529575
     * traceId : ac110001160099952911512318602
     * data : {"url":"http://192.168.40.26:9000/appimage/file1600999529187","name":"file1600999529187"}
     */

    private int code;
    private String desc;
    private long requestTime;
    private String traceId;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * url : http://192.168.40.26:9000/appimage/file1600999529187
         * name : file1600999529187
         */

        private String url;
        private String name;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
