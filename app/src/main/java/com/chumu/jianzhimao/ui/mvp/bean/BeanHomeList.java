package com.chumu.jianzhimao.ui.mvp.bean;

import java.util.List;

/**
 * JianZhiMao
 * <p>
 * Created by ChuMu on 2020/9/24
 * Copyright © 2020年 ChuMu. All rights reserved.
 * <p>
 * Describe: 列表数据
 */

public class BeanHomeList {

    /**
     * code : 200
     * desc : 成功
     * requestTime : 1600960222181
     * traceId : ac110001160096022200010488602
     * data : {"total":6,"more":1,"rows":[{"id":6,"name":"软件运营","createDate":"2020-09-10","contact":"热门兼职","digest":"经济独立","labelList":["日结","月结","高薪"],"applyNum":0,"shareNum":0,"collectNum":0,"serviceName":"小小酥","picture":"http://192.168.40.26:9000/bgimage/file1600847963881","positionDetailsLink":"http:192.168.40.21:9093?positionId=6"},{"id":3,"name":"视频兼职","createDate":"2020-09-10","contact":"热门兼职","digest":"球鞋文化","labelList":["日结","月结","高薪"],"applyNum":0,"shareNum":0,"collectNum":0,"serviceName":"苏小小","picture":"http://192.168.40.26:9000/bgimage/file1600847963881","positionDetailsLink":"http:192.168.40.21:9093?positionId=3"},{"id":2,"name":"抖音兼职","createDate":"2020-09-10","contact":"热门兼职","digest":"快速经济独立","labelList":["日结","月结","高薪"],"applyNum":0,"shareNum":0,"collectNum":0,"serviceName":"苏小小","picture":"http://192.168.40.26:9000/bgimage/file1600847963881","positionDetailsLink":"http:192.168.40.21:9093?positionId=2"},{"id":5,"name":"软件开发","createDate":"2020-09-10","contact":"热门兼职","digest":"网红养成记","labelList":["日结","月结","高薪"],"applyNum":0,"shareNum":0,"collectNum":0,"serviceName":"小小酥","picture":"http://192.168.40.26:9000/bgimage/file1600847963881","positionDetailsLink":"http:192.168.40.21:9093?positionId=5"},{"id":4,"name":"软件设计","createDate":"2020-09-10","contact":"热门兼职","digest":"寻求志同道合的人","labelList":["日结","月结","高薪"],"applyNum":0,"shareNum":0,"collectNum":0,"serviceName":"小小酥","picture":"http://192.168.40.26:9000/bgimage/file1600847963881","positionDetailsLink":"http:192.168.40.21:9093?positionId=4"},{"id":1,"name":"广告兼职","createDate":"2020-09-10","contact":"热门兼职","digest":"看脸的时代","labelList":["日结","月结","高薪"],"applyNum":1,"shareNum":0,"collectNum":0,"serviceName":"苏小小","picture":"http://192.168.40.26:9000/bgimage/file1600847963881","positionDetailsLink":"http:192.168.40.21:9093?positionId=1"}]}
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
         * total : 6
         * more : 1
         * rows : [{"id":6,"name":"软件运营","createDate":"2020-09-10","contact":"热门兼职","digest":"经济独立","labelList":["日结","月结","高薪"],"applyNum":0,"shareNum":0,"collectNum":0,"serviceName":"小小酥","picture":"http://192.168.40.26:9000/bgimage/file1600847963881","positionDetailsLink":"http:192.168.40.21:9093?positionId=6"},{"id":3,"name":"视频兼职","createDate":"2020-09-10","contact":"热门兼职","digest":"球鞋文化","labelList":["日结","月结","高薪"],"applyNum":0,"shareNum":0,"collectNum":0,"serviceName":"苏小小","picture":"http://192.168.40.26:9000/bgimage/file1600847963881","positionDetailsLink":"http:192.168.40.21:9093?positionId=3"},{"id":2,"name":"抖音兼职","createDate":"2020-09-10","contact":"热门兼职","digest":"快速经济独立","labelList":["日结","月结","高薪"],"applyNum":0,"shareNum":0,"collectNum":0,"serviceName":"苏小小","picture":"http://192.168.40.26:9000/bgimage/file1600847963881","positionDetailsLink":"http:192.168.40.21:9093?positionId=2"},{"id":5,"name":"软件开发","createDate":"2020-09-10","contact":"热门兼职","digest":"网红养成记","labelList":["日结","月结","高薪"],"applyNum":0,"shareNum":0,"collectNum":0,"serviceName":"小小酥","picture":"http://192.168.40.26:9000/bgimage/file1600847963881","positionDetailsLink":"http:192.168.40.21:9093?positionId=5"},{"id":4,"name":"软件设计","createDate":"2020-09-10","contact":"热门兼职","digest":"寻求志同道合的人","labelList":["日结","月结","高薪"],"applyNum":0,"shareNum":0,"collectNum":0,"serviceName":"小小酥","picture":"http://192.168.40.26:9000/bgimage/file1600847963881","positionDetailsLink":"http:192.168.40.21:9093?positionId=4"},{"id":1,"name":"广告兼职","createDate":"2020-09-10","contact":"热门兼职","digest":"看脸的时代","labelList":["日结","月结","高薪"],"applyNum":1,"shareNum":0,"collectNum":0,"serviceName":"苏小小","picture":"http://192.168.40.26:9000/bgimage/file1600847963881","positionDetailsLink":"http:192.168.40.21:9093?positionId=1"}]
         */

        private int total;
        private int more;
        private List<RowsBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getMore() {
            return more;
        }

        public void setMore(int more) {
            this.more = more;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * id : 6
             * name : 软件运营
             * createDate : 2020-09-10
             * contact : 热门兼职
             * digest : 经济独立
             * labelList : ["日结","月结","高薪"]
             * applyNum : 0
             * shareNum : 0
             * collectNum : 0
             * serviceName : 小小酥
             * picture : http://192.168.40.26:9000/bgimage/file1600847963881
             * positionDetailsLink : http:192.168.40.21:9093?positionId=6
             */

            private int id;
            private String name;
            private String createDate;
            private String contact;
            private String digest;
            private int applyNum;
            private int shareNum;
            private int collectNum;
            private String serviceName;
            private String picture;
            private String positionDetailsLink;
            private String headPhoto;
            private List<String> labelList;
            private boolean haveCollect=false;

            public boolean isHaveCollect() {
                return haveCollect;
            }

            public void setHaveCollect(boolean haveCollect) {
                this.haveCollect = haveCollect;
            }

            public String getHeadPhoto() {
                return headPhoto;
            }

            public void setHeadPhoto(String headPhoto) {
                this.headPhoto = headPhoto;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
            }

            public String getDigest() {
                return digest;
            }

            public void setDigest(String digest) {
                this.digest = digest;
            }

            public int getApplyNum() {
                return applyNum;
            }

            public void setApplyNum(int applyNum) {
                this.applyNum = applyNum;
            }

            public int getShareNum() {
                return shareNum;
            }

            public void setShareNum(int shareNum) {
                this.shareNum = shareNum;
            }

            public int getCollectNum() {
                return collectNum;
            }

            public void setCollectNum(int collectNum) {
                this.collectNum = collectNum;
            }

            public String getServiceName() {
                return serviceName;
            }

            public void setServiceName(String serviceName) {
                this.serviceName = serviceName;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getPositionDetailsLink() {
                return positionDetailsLink;
            }

            public void setPositionDetailsLink(String positionDetailsLink) {
                this.positionDetailsLink = positionDetailsLink;
            }

            public List<String> getLabelList() {
                return labelList;
            }

            public void setLabelList(List<String> labelList) {
                this.labelList = labelList;
            }

            @Override
            public String toString() {
                return "RowsBean{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", createDate='" + createDate + '\'' +
                        ", contact='" + contact + '\'' +
                        ", digest='" + digest + '\'' +
                        ", applyNum=" + applyNum +
                        ", shareNum=" + shareNum +
                        ", collectNum=" + collectNum +
                        ", serviceName='" + serviceName + '\'' +
                        ", picture='" + picture + '\'' +
                        ", positionDetailsLink='" + positionDetailsLink + '\'' +
                        ", headPhoto='" + headPhoto + '\'' +
                        ", labelList=" + labelList +
                        ", haveCollect=" + haveCollect +
                        '}';
            }
        }
    }
}
