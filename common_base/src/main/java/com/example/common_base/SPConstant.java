package com.example.common_base;

import com.example.common_base.base.BaseApplication;

/**
 * ChuMuProjectFramework
 * <p>
 * Created by ChuMu on 2020/5/22
 * Copyright © 2020年 ChuMu. All rights reserved.
 * <p>
 * Describe: 存放sp的常量
 */

public class SPConstant {
    //是否第一次登录
    public static final String FIRST_IN = "first_in" ;
    public static String PORTRAIT_NAME = BaseApplication.getApplication().getPackageName();
   public static class Login {
        public static final String MOBILE = "mobile" ;// 手机号
        public static final String ID = "id" ;// 用户id
        public static final String NICKNAME = "nickName" ;// 昵称
        public static final String moneyLimit = "moneyLimit" ;// 每月额限
        public static final String TOKEN = "token" ;// token
        public static final String HEAD_PICTURE = "headPicture" ;// 头像
        public static final String SIGNATURE = "signature" ;// 个性签名
        public static final String V_CODE = "v_code" ;// 验证码

    }

}
