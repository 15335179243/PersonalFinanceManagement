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

public class GroutBean {


    /**
     * code : 200
     * desc : 成功
     * requestTime : 1620213579000
     * data : {"total":4,"more":0,"rows":[{"id":1,"groupName":"张三的个人群","groupStatus":1,"created":"2021-05-03T23:40:29.000+0000","groupMax":50,"groupUserResp":{"users":[{"id":1,"nickName":"admin","moneyLimit":5008}],"userFinanceList":[{"userId":1,"userNickName":"admin","finance":2000}]}},{"id":2,"groupName":"我的世界","groupStatus":1,"created":"2021-05-05T07:35:21.000+0000","groupMax":100,"groupUserResp":{"users":[{"id":6,"nickName":"我的快乐","headPhoto":"https://pic.liesio.com/2021/05/05/d5d00444f0b24.jpeg","moneyLimit":200}],"userFinanceList":[{"userId":6,"userNickName":"我的快乐","finance":6310}]}},{"id":3,"groupName":"我的世界","groupStatus":1,"created":"2021-05-05T07:36:35.000+0000","groupMax":100,"groupUserResp":{"users":[{"id":6,"nickName":"我的快乐","headPhoto":"https://pic.liesio.com/2021/05/05/d5d00444f0b24.jpeg","moneyLimit":200}],"userFinanceList":[{"userId":6,"userNickName":"我的快乐","finance":6310}]}},{"id":4,"groupName":"我的世界","groupStatus":1,"created":"2021-05-05T07:41:32.000+0000","groupMax":100,"groupUserResp":{"users":[{"id":6,"nickName":"我的快乐","headPhoto":"https://pic.liesio.com/2021/05/05/d5d00444f0b24.jpeg","moneyLimit":200}],"userFinanceList":[{"userId":6,"userNickName":"我的快乐","finance":6310}]}}]}
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
         * total : 4
         * more : 0
         * rows : [{"id":1,"groupName":"张三的个人群","groupStatus":1,"created":"2021-05-03T23:40:29.000+0000","groupMax":50,"groupUserResp":{"users":[{"id":1,"nickName":"admin","moneyLimit":5008}],"userFinanceList":[{"userId":1,"userNickName":"admin","finance":2000}]}},{"id":2,"groupName":"我的世界","groupStatus":1,"created":"2021-05-05T07:35:21.000+0000","groupMax":100,"groupUserResp":{"users":[{"id":6,"nickName":"我的快乐","headPhoto":"https://pic.liesio.com/2021/05/05/d5d00444f0b24.jpeg","moneyLimit":200}],"userFinanceList":[{"userId":6,"userNickName":"我的快乐","finance":6310}]}},{"id":3,"groupName":"我的世界","groupStatus":1,"created":"2021-05-05T07:36:35.000+0000","groupMax":100,"groupUserResp":{"users":[{"id":6,"nickName":"我的快乐","headPhoto":"https://pic.liesio.com/2021/05/05/d5d00444f0b24.jpeg","moneyLimit":200}],"userFinanceList":[{"userId":6,"userNickName":"我的快乐","finance":6310}]}},{"id":4,"groupName":"我的世界","groupStatus":1,"created":"2021-05-05T07:41:32.000+0000","groupMax":100,"groupUserResp":{"users":[{"id":6,"nickName":"我的快乐","headPhoto":"https://pic.liesio.com/2021/05/05/d5d00444f0b24.jpeg","moneyLimit":200}],"userFinanceList":[{"userId":6,"userNickName":"我的快乐","finance":6310}]}}]
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
             * id : 1
             * groupName : 张三的个人群
             * groupStatus : 1
             * created : 2021-05-03T23:40:29.000+0000
             * groupMax : 50
             * groupUserResp : {"users":[{"id":1,"nickName":"admin","moneyLimit":5008}],"userFinanceList":[{"userId":1,"userNickName":"admin","finance":2000}]}
             */

            private int id;
            private String groupName;
            private int groupStatus;
            private String created;
            private int groupMax;
            private GroupUserRespBean groupUserResp;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public int getGroupStatus() {
                return groupStatus;
            }

            public void setGroupStatus(int groupStatus) {
                this.groupStatus = groupStatus;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public int getGroupMax() {
                return groupMax;
            }

            public void setGroupMax(int groupMax) {
                this.groupMax = groupMax;
            }

            public GroupUserRespBean getGroupUserResp() {
                return groupUserResp;
            }

            public void setGroupUserResp(GroupUserRespBean groupUserResp) {
                this.groupUserResp = groupUserResp;
            }

            public static class GroupUserRespBean {
                private List<UsersBean> users;
                private List<UserFinanceListBean> userFinanceList;

                public List<UsersBean> getUsers() {
                    return users;
                }

                public void setUsers(List<UsersBean> users) {
                    this.users = users;
                }

                public List<UserFinanceListBean> getUserFinanceList() {
                    return userFinanceList;
                }

                public void setUserFinanceList(List<UserFinanceListBean> userFinanceList) {
                    this.userFinanceList = userFinanceList;
                }

                public static class UsersBean {
                    /**
                     * id : 1
                     * nickName : admin
                     * moneyLimit : 5008
                     */

                    private int id;
                    private String nickName;
                    private Double moneyLimit;
                    private String headPhoto;

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

                    public String getNickName() {
                        return nickName;
                    }

                    public void setNickName(String nickName) {
                        this.nickName = nickName;
                    }

                    public Double getMoneyLimit() {
                        return moneyLimit;
                    }

                    public void setMoneyLimit(Double moneyLimit) {
                        this.moneyLimit = moneyLimit;
                    }
                }

                public static class UserFinanceListBean {
                    /**
                     * userId : 1
                     * userNickName : admin
                     * finance : 2000.0
                     */

                    private int userId;
                    private String userNickName;
                    private double finance;

                    public int getUserId() {
                        return userId;
                    }

                    public void setUserId(int userId) {
                        this.userId = userId;
                    }

                    public String getUserNickName() {
                        return userNickName;
                    }

                    public void setUserNickName(String userNickName) {
                        this.userNickName = userNickName;
                    }

                    public double getFinance() {
                        return finance;
                    }

                    public void setFinance(double finance) {
                        this.finance = finance;
                    }
                }
            }
        }
    }
}
