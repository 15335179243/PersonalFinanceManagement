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
     * requestTime : 1620216906688
     * data : {"total":5,"more":0,"rows":[{"id":5,"userId":1,"desc":"收入","created":"2021-05-03T23:21:11.000+0000","createdTime":"2021-05-04","status":2,"money":2050},{"id":3,"userId":1,"desc":"支出","created":"2021-05-03T23:19:55.000+0000","createdTime":"2021-05-04","status":1,"money":50},{"id":1,"userId":1,"desc":"收入","created":"2021-05-03T23:18:34.000+0000","createdTime":"2021-05-04","status":2,"money":100},{"id":2,"userId":1,"desc":"收入","created":"2021-05-02T23:18:41.000+0000","createdTime":"2021-05-03","status":1,"money":50},{"id":4,"userId":1,"desc":"这是收入","created":"2021-05-01T23:20:49.000+0000","createdTime":"2021-05-02","status":1,"money":50}]}
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
         * total : 5
         * more : 0
         * rows : [{"id":5,"userId":1,"desc":"收入","created":"2021-05-03T23:21:11.000+0000","createdTime":"2021-05-04","status":2,"money":2050},{"id":3,"userId":1,"desc":"支出","created":"2021-05-03T23:19:55.000+0000","createdTime":"2021-05-04","status":1,"money":50},{"id":1,"userId":1,"desc":"收入","created":"2021-05-03T23:18:34.000+0000","createdTime":"2021-05-04","status":2,"money":100},{"id":2,"userId":1,"desc":"收入","created":"2021-05-02T23:18:41.000+0000","createdTime":"2021-05-03","status":1,"money":50},{"id":4,"userId":1,"desc":"这是收入","created":"2021-05-01T23:20:49.000+0000","createdTime":"2021-05-02","status":1,"money":50}]
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
             * id : 5
             * userId : 1
             * desc : 收入
             * created : 2021-05-03T23:21:11.000+0000
             * createdTime : 2021-05-04
             * status : 2
             * money : 2050
             */

            private int id;
            private int userId;
            private String desc;
            private String created;
            private String createdTime;
            private int status;
            private int money;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public String getCreatedTime() {
                return createdTime;
            }

            public void setCreatedTime(String createdTime) {
                this.createdTime = createdTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
                this.money = money;
            }
        }
    }
}
