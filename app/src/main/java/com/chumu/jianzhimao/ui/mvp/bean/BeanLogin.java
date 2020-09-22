package com.chumu.jianzhimao.ui.mvp.bean;

import com.google.gson.annotations.SerializedName;

/**
 * JianZhiMao
 * <p>
 * Created by ChuMu on 2020/9/20
 * Copyright © 2020年 ChuMu. All rights reserved.
 * <p>
 * Describe:
 */

public class BeanLogin {


    /**
     * code : 200
     * desc : 成功
     * requestTime : 1600791809018
     * traceId : ac110001160079180900310135555
     * data : {"whetherRegister":false}
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
         * id : 5
         * mobile : 15819495896
         * signature : 暂无个性签名,点击进行设置
         * nickName : 普通会员
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwidXNlckluZm8iOnsidXNlcklkIjo1fSwiZXhwIjoxNjAwNDgwNzMyLCJpYXQiOjE2MDAzOTQzMzJ9.bkvweH4NzTbEEDMt_ZbJUHOBnbwB2aG1qn8U0X3i9zc
         * headPicture : http://192.168.40.26:9000/appimage/file1600393530461
         * ”whetherRegister“ : true
         */

        private int id;
        private String mobile;
        private String signature;
        private String nickName;
        private String token;
        private String headPicture;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getHeadPicture() {
            return headPicture;
        }

        public void setHeadPicture(String headPicture) {
            this.headPicture = headPicture;
        }

    }
}
