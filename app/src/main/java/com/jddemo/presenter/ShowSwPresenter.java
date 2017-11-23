package com.jddemo.presenter;

/**
 * Created by 胡靖宇 on 2017/11/1.
 */

public interface ShowSwPresenter {
    //三级列表
    void relevanceSw();

    void relevanceSw2();

    //注册
    void relevanceSign_up();

    //登陆
    void relevanceLogin();

    //搜索商品
    void relevanceSelect();

    //商品详情
    void relevanceDetail();

    //三级列表商品
    void relrevanceSeSw();
    //添加购物车
    void relrevanceAddShopp();
    //显示购物车
    void relrevanceShopp();
    //显示地址
    void relrevanceAddress();
}