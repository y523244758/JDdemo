package com.jddemo.presenter;

import com.jddemo.DetailActivity;
import com.jddemo.Fragment.Shopp;
import com.jddemo.LoginActivity;
import com.jddemo.OnFinishListener.SwitchOnFinishListener;
import com.jddemo.ShowAddressActivity;
import com.jddemo.model.ShowSwitchModel;
import com.jddemo.model.ShowSwitchlmpl;
import com.jddemo.model.bean.bean_addshopp;
import com.jddemo.model.bean.bean_detail;
import com.jddemo.model.bean.bean_login;
import com.jddemo.model.bean.bean_select;
import com.jddemo.model.bean.bean_shopp;
import com.jddemo.model.bean.bean_showaddress;
import com.jddemo.model.bean.bean_sign_up;
import com.jddemo.model.bean.three_leve.bean_one;
import com.jddemo.model.bean.three_leve.bean_two;
import com.jddemo.selectActivity;
import com.jddemo.select_switchActivity;
import com.jddemo.sign_upActivity;
import com.jddemo.view.ShowViewDetail;
import com.jddemo.view.ShowView_SeSw;
import com.jddemo.view.ShowView_ShowAddress;
import com.jddemo.view.ShowView_Sw;
import com.jddemo.view.ShowView_login;
import com.jddemo.view.ShowView_select;
import com.jddemo.view.ShowView_shopp;
import com.jddemo.view.ShowView_sign_Up;

/**
 * Created by 胡靖宇 on 2017/11/1.
 */

public class ShowSwPresenterImpl implements ShowSwPresenter,SwitchOnFinishListener{
    ShowView_Sw sv;
    ShowView_sign_Up view_sign_up;
    ShowSwitchModel smd;
    ShowView_login loginview;
    ShowView_select selectview;
    ShowViewDetail svDetail;
    ShowView_SeSw sesw;
    ShowView_shopp shoppview;
    ShowView_ShowAddress ssaddress;

    public ShowSwPresenterImpl(ShowView_Sw sv) {

        this.sv=sv;
        smd=new ShowSwitchlmpl();
    }

    public ShowSwPresenterImpl(sign_upActivity view_sign_up) {
        this.view_sign_up=view_sign_up;
        smd=new ShowSwitchlmpl();
    }

    public ShowSwPresenterImpl(ShowView_login loginview) {
        this.loginview=loginview;
        smd=new ShowSwitchlmpl();

    }

    public ShowSwPresenterImpl(ShowView_select selectview) {
        this.selectview=selectview;
        smd=new ShowSwitchlmpl();
    }

    public ShowSwPresenterImpl(ShowViewDetail svDetail) {
        this.svDetail=svDetail;
        smd=new ShowSwitchlmpl();
    }

    public ShowSwPresenterImpl(ShowView_SeSw sesw) {
        this.sesw=sesw;
        smd=new ShowSwitchlmpl();
    }

    public ShowSwPresenterImpl(ShowView_shopp shoppview) {
        this.shoppview=shoppview;
        smd=new ShowSwitchlmpl();
    }

    public ShowSwPresenterImpl(ShowView_ShowAddress ssaddress) {
       this.ssaddress=ssaddress;
        smd=new ShowSwitchlmpl();
    }

    //三级列表
    // p and m
    @Override
    public void relevanceSw() {
        smd.getSdata(this);
    }




    public void relevanceSw2() {
        smd.getid(sv.getid());
        smd.getSdatatwo(this);

    }


    //p and v
    @Override
    public void onSwith(bean_one bon) {
        sv.ShowSwData(bon);
    }

    @Override
    public void onSwith2(bean_two two) {
        sv.ShowSwData2(two);

    }
    //注册
    @Override
    public void onsign_Up(bean_sign_up sup) {
        view_sign_up.Showsign_upData(sup);
    }



    //注册
    @Override
    public void relevanceSign_up() {
        smd.getphone(view_sign_up.getphone());
        smd.getpwd(view_sign_up.getpwd());
        smd.getDatasign_Up(this);

    }

    //登陆
    @Override
    public void onLogin(bean_login beanLogin) {
        loginview.ShowloginData(beanLogin);

    }



    //登陆
    @Override
    public void relevanceLogin() {
        smd.getphone(loginview.getphone());
        smd.getpwd(loginview.getpwd());
        smd.getDataLogin(this);
    }

    //搜索商品
    @Override
    public void relevanceSelect() {
        smd.getShopp(selectview.getshoppname());
        smd.getDataSelect(this);

    }



    @Override
    public void onSelect(bean_select bselect) {
        selectview.ShowSelectData(bselect);
    }

    //商品详情
    @Override
    public void relevanceDetail() {
        smd.getDetailpid(svDetail.getPid());
        smd.getDataDetail(this);

    }



    @Override
    public void onSelectDetail(bean_detail bean_detail) {
        svDetail.ShowDetailData(bean_detail);
    }
//三级列表商品

    @Override
    public void relrevanceSeSw() {
        smd.getPscid(sesw.getPscid());
        smd.getDataSeSw(this);
    }



    @Override
    public void onSeSw(bean_select bsswse) {
        sesw.ShowSeSwData(bsswse);
    }

    //添加购物车
    @Override
    public void relrevanceAddShopp() {
        smd.getuid(svDetail.getuid());
        smd.getpid(svDetail.getpidt());
        smd.getsellerid(svDetail.getsellerid());
        smd.getDataAddShopp(this);
    }



    @Override
    public void onAddShopp(bean_addshopp beanAddshopp) {
        svDetail.ShowAddShoppData(beanAddshopp);
    }


    @Override
    public void relrevanceShopp() {
        smd.getuids(shoppview.getuid());
        smd.getDataShopp(this);
    }

    @Override
    public void onShopp(bean_shopp beanShopping) {
        shoppview.ShowShoppData(beanShopping);
    }

    //显示地址
    @Override
    public void relrevanceAddress() {
        smd.getDataAddress(this);
    }



    @Override
    public void onAddress(bean_showaddress beanaddress) {
        ssaddress.ShowAddressData(beanaddress);
    }
}
