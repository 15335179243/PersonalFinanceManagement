package com.chumu.jianzhimao.ui.mvp.bean;

import java.util.List;

/**
 * JianZhiMao
 * <p>
 * Created by ChuMu on 2020/9/24
 * Copyright © 2020年 ChuMu. All rights reserved.
 * <p>
 * Describe: 首页banner
 */

public class BeanBannerList {

    /**
     * code : 200
     * desc : 成功
     * requestTime : 1600960788682
     * traceId : ac110001160096078856210528602
     * data : [{"id":29,"link":"https://www.baidu.com","positionId":6,"url":"http://192.168.40.26:9000/bgimage/file1600847963881"},{"id":30,"link":"https://www.baidu.com","positionId":3,"url":"http://192.168.40.26:9000/bgimage/file1600848054207"},{"id":31,"link":"www.baidu.com","positionId":1,"url":"http://192.168.40.26:9000/bgimage/file1600848069865"}]
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
         * id : 29
         * link : https://www.baidu.com
         * positionId : 6
         * url : http://192.168.40.26:9000/bgimage/file1600847963881
         */

        private int id;
        private String link;
        private int positionId;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getPositionId() {
            return positionId;
        }

        public void setPositionId(int positionId) {
            this.positionId = positionId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
