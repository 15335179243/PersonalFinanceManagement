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

public interface  AppConfig {
    interface User{
//        type	是
//        行为类型 1：账号密码登录  2：验证码登录  3：注册

      int Account_Password_login=1;
      int Verification_Code_login=2;
      int register=3;
    }
}
