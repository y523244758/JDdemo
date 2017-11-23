package com.jddemo.view;

import com.jddemo.model.bean.bean_sign_up;

/**
 * Created by 胡靖宇 on 2017/11/3.
 */

public interface ShowView_sign_Up {
    //注册
    void  Showsign_upData(bean_sign_up sign_up);
    String getphone();
    String getpwd();
}
