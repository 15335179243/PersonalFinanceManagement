package com.example.common_base;

import android.accounts.Account;

/**
 * JianZhiMao
 * <p>
 * Created by ChuMu on 2020/9/19
 * Copyright © 2020年 ChuMu. All rights reserved.
 * <p>
 * Describe: 存放app常量数据
 */

public interface AppConfig {
    interface User {
//        type	是
//        行为类型 1：账号密码登录  2：验证码登录  3：注册

        int Account_Password_login = 1;
        int Verification_Code_login = 2;
        int register = 3;
    }

    interface DataTag {
        String PLACE = "place";
//1：复制  2：报名 3：注册 4：收藏 5：分享
//        在收藏和取消收藏时传入 1：收藏 0：取消收藏
        int copy = 1;
        int apply = 2;
        int register = 3;
        int favorite = 4;
        int share = 5;
        int inFavorite = 1;
        int unFavorite = 2;

    }

    interface WebView {
        int USER_AGREEMENT = 0;
        int PRIVACY_AGREEMENT = 1;

        int QQ = 1;
        int WEI_XIN = 2;
        int PHONE = 3;
    }
}
