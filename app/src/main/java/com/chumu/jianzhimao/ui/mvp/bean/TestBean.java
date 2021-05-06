package com.chumu.jianzhimao.ui.mvp.bean;

import java.util.List;

/**
 * JianZhiMao
 * <p>
 * Created by ChuMu on 2021/5/5
 * Copyright © 2021年 ChuMu. All rights reserved.
 * <p>
 * Describe:
 */

public class TestBean {


    /**
     * code : 200
     * desc : 成功
     * requestTime : 1620222771550
     * data : {"statisticsTotal":8209,"pay":150,"get":8359,"weekList":[{"payTotal":0,"getTotal":0,"date":"2021-04-29","dateTime":1619625600000},{"payTotal":0,"getTotal":0,"date":"2021-04-30","dateTime":1619712000000},{"payTotal":0,"getTotal":0,"date":"2021-05-01","dateTime":1619798400000},{"payTotal":0,"getTotal":0,"date":"2021-05-02","dateTime":1619884800000},{"payTotal":50,"getTotal":0,"date":"2021-05-03","dateTime":1619971200000},{"payTotal":50,"getTotal":2150,"date":"2021-05-04","dateTime":1620057600000},{"payTotal":0,"getTotal":6209,"date":"2021-05-05","dateTime":1620144000000}]}
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
         * statisticsTotal : 8209.0
         * pay : 150.0
         * get : 8359.0
         * weekList : [{"payTotal":0,"getTotal":0,"date":"2021-04-29","dateTime":1619625600000},{"payTotal":0,"getTotal":0,"date":"2021-04-30","dateTime":1619712000000},{"payTotal":0,"getTotal":0,"date":"2021-05-01","dateTime":1619798400000},{"payTotal":0,"getTotal":0,"date":"2021-05-02","dateTime":1619884800000},{"payTotal":50,"getTotal":0,"date":"2021-05-03","dateTime":1619971200000},{"payTotal":50,"getTotal":2150,"date":"2021-05-04","dateTime":1620057600000},{"payTotal":0,"getTotal":6209,"date":"2021-05-05","dateTime":1620144000000}]
         */

        private double statisticsTotal;
        private double pay;
        private double get;
        private List<WeekListBean> weekList;

        public double getStatisticsTotal() {
            return statisticsTotal;
        }

        public void setStatisticsTotal(double statisticsTotal) {
            this.statisticsTotal = statisticsTotal;
        }

        public double getPay() {
            return pay;
        }

        public void setPay(double pay) {
            this.pay = pay;
        }

        public double getGet() {
            return get;
        }

        public void setGet(double get) {
            this.get = get;
        }

        public List<WeekListBean> getWeekList() {
            return weekList;
        }

        public void setWeekList(List<WeekListBean> weekList) {
            this.weekList = weekList;
        }

        public static class WeekListBean {
            /**
             * payTotal : 0.0
             * getTotal : 0.0
             * date : 2021-04-29
             * dateTime : 1619625600000
             */

            private double payTotal;
            private double getTotal;
            private String date;
            private long dateTime;

            public double getPayTotal() {
                return payTotal;
            }

            public void setPayTotal(double payTotal) {
                this.payTotal = payTotal;
            }

            public double getGetTotal() {
                return getTotal;
            }

            public void setGetTotal(double getTotal) {
                this.getTotal = getTotal;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public long getDateTime() {
                return dateTime;
            }

            public void setDateTime(long dateTime) {
                this.dateTime = dateTime;
            }
        }
    }
}
