package com.jddemo.view;

import com.jddemo.model.bean.bean_login;
import com.jddemo.model.bean.bean_sign_up;

/**
 * Created by 胡靖宇 on 2017/11/3.
 */

public interface ShowView_login {
    //注册
    void  ShowloginData(bean_login login);
    String getphone();
    String getpwd();
}
