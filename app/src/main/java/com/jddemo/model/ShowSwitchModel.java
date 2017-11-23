package com.jddemo.model;

import com.jddemo.OnFinishListener.SwitchOnFinishListener;
import com.jddemo.presenter.ShowSwPresenterImpl;

/**
 * Created by 胡靖宇 on 2017/11/1.
 */

public interface ShowSwitchModel {
    //三级列表
    void getSdata(SwitchOnFinishListener sonfl);
    void getSdatatwo(SwitchOnFinishListener showSwPresenter);
    //获取二级列表id
    void  getid(String id);
    //注册
    void getDatasign_Up(SwitchOnFinishListener sup);
    void  getphone(String phone);
    void  getpwd(String pwd);
    //登陆
    void  getDataLogin(SwitchOnFinishListener slog);
    void getloginphone(String logphone);
    void getloginpwd(String logpwd);
    //搜索商品
    void getDataSelect(SwitchOnFinishListener seleon);
    void getShopp(String shopp);
    //商品详情
    void  getDataDetail(SwitchOnFinishListener detailsw);
    void  getDetailpid(String detail);
    //三级列表商品
    void getDataSeSw(SwitchOnFinishListener seleon);
    void getPscid(String pscid);
    //添加购物车
    void getDataAddShopp(SwitchOnFinishListener swAddshopp);
    void getuid(String uid);
    void getpid(String pid);
    void getsellerid(String sellerid);
    //显示购物车
    void getDataShopp(SwitchOnFinishListener swShopp);
    void getuids(String uid);
    //显示地址
    void getDataAddress(SwitchOnFinishListener swAddress);
}
