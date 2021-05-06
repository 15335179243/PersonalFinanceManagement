package com.chumu.jianzhimao.ui.mvp.bean;

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
     * requestTime : 1620202662705
     * data : {"id":6,"nickName":"我的快乐","headPhoto":"https://pic.liesio.com/2021/05/05/d5d00444f0b24.jpeg","moneyLimit":200,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwidXNlckluZm8iOnsibmlja05hbWUiOiLmiJHnmoTlv6vkuZAiLCJpZCI6Nn0sImV4cCI6MTYyMDI4OTA2MiwiaWF0IjoxNjIwMjAyNjYyfQ.qPMBxN2JXr-oMc_uYtwaUalBeePVdl8XXOiKd0U42Dk"}
     */

    private int code;
    private String desc;
    private long requestTime;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 6
         * nickName : 我的快乐
         * headPhoto : https://pic.liesio.com/2021/05/05/d5d00444f0b24.jpeg
         * moneyLimit : 200
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwidXNlckluZm8iOnsibmlja05hbWUiOiLmiJHnmoTlv6vkuZAiLCJpZCI6Nn0sImV4cCI6MTYyMDI4OTA2MiwiaWF0IjoxNjIwMjAyNjYyfQ.qPMBxN2JXr-oMc_uYtwaUalBeePVdl8XXOiKd0U42Dk
         */

        private int id;
        private String nickName;
        private String headPhoto;
        private int moneyLimit;
        private String token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getHeadPhoto() {
            return headPhoto;
        }

        public void setHeadPhoto(String headPhoto) {
            this.headPhoto = headPhoto;
        }

        public int getMoneyLimit() {
            return moneyLimit;
        }

        public void setMoneyLimit(int moneyLimit) {
            this.moneyLimit = moneyLimit;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
