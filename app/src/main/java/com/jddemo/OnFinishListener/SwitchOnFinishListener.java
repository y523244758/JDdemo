package com.jddemo.OnFinishListener;

import com.jddemo.model.bean.bean_addshopp;
import com.jddemo.model.bean.bean_detail;
import com.jddemo.model.bean.bean_login;
import com.jddemo.model.bean.bean_select;
import com.jddemo.model.bean.bean_shopp;
import com.jddemo.model.bean.bean_showaddress;
import com.jddemo.model.bean.bean_sign_up;
import com.jddemo.model.bean.three_leve.bean_one;
import com.jddemo.model.bean.three_leve.bean_two;

/**
 * Created by 胡靖宇 on 2017/11/1.
 */

public interface SwitchOnFinishListener {
    //三级列表
    void onSwith(bean_one bon);
    void onSwith2(bean_two two);
    //注册
    void onsign_Up(bean_sign_up sup);
    //登陆
    void onLogin(bean_login beanLogin);
    //搜索商品
    void onSelect(bean_select bselect);
    //商品详情
    void onSelectDetail(bean_detail bean_detail);
    //三级列表
    void onSeSw(bean_select bsswse);
    //添加购物车
    void onAddShopp(bean_addshopp beanAddshopp);
    //显示购物车
    void onShopp(bean_shopp beanShopping);
    //显示地址
    void onAddress(bean_showaddress beanaddress);
}
