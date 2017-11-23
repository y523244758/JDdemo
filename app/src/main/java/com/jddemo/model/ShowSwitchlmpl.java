package com.jddemo.model;

import com.jddemo.OnFinishListener.SwitchOnFinishListener;
import com.jddemo.Save_Data;
import com.jddemo.model.bean.bean_addshopp;
import com.jddemo.model.bean.bean_detail;
import com.jddemo.model.bean.bean_login;
import com.jddemo.model.bean.bean_select;
import com.jddemo.model.bean.bean_shopp;
import com.jddemo.model.bean.bean_showaddress;
import com.jddemo.model.bean.bean_sign_up;
import com.jddemo.model.bean.three_leve.bean_one;
import com.jddemo.model.bean.three_leve.bean_two;
import com.jddemo.presenter.ShowSwPresenterImpl;
import com.jddemo.utils.GsonObjectCallback;
import com.jddemo.utils.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 胡靖宇 on 2017/11/1.
 */

public class ShowSwitchlmpl implements ShowSwitchModel{

    Map<String,String> towmap=new HashMap<>();
    String idt;
    Map<String,String> regmap=new HashMap<>();
    Map<String,String> shoopmap=new HashMap<>();
    Map<String,String> detailmap=new HashMap<>();
    Map<String,String> swsemap=new HashMap<>();
    Map<String,String> shoppmap=new HashMap<>();

    String pidm;
    String phonem;
    String pwdm;
    String shoppname;
    String page=2+"";
    String ppid;
    String uidm,selleridm;
    String uids;
    String token;

    //一级列表
    @Override
    public void getSdata(final SwitchOnFinishListener sonfl) {
        OkHttp3Utils.doGet("https://www.zhaoapi.cn/product/getCatagory", new GsonObjectCallback<bean_one>() {


            @Override
            public void onUi(bean_one bean_one) {

                if(sonfl!=null){
                    sonfl.onSwith(bean_one);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }

    @Override
    public void getSdatatwo(final SwitchOnFinishListener showSwPresenter) {
        towmap.put("cid",idt);
        OkHttp3Utils.doPost("https://www.zhaoapi.cn/product/getProductCatagory", towmap,new GsonObjectCallback<bean_two>() {
            @Override
            public void onUi(bean_two two) {

                if(showSwPresenter!=null){
                    showSwPresenter.onSwith2(two);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }



    //一级向二级列表传值
    @Override
    public void getid(String id) {
        idt=id;

    }

    //注册
    @Override
    public void getDatasign_Up(final SwitchOnFinishListener sup) {
        regmap.put("mobile",phonem);
        regmap.put("password",pwdm);
        OkHttp3Utils.doPost("https://www.zhaoapi.cn/user/reg", regmap, new GsonObjectCallback<bean_sign_up>() {
            @Override
            public void onUi(bean_sign_up bean_sign_up) {
                if(sup!=null){
                    sup.onsign_Up(bean_sign_up);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }

    @Override
    public void getphone(String phone) {
        phonem=phone;
    }

    @Override
    public void getpwd(String pwd) {
        pwdm=pwd;
    }

    //登陆笔记本
    @Override
    public void getDataLogin(final SwitchOnFinishListener slog) {
        regmap.put("mobile",phonem);
        regmap.put("password",pwdm);
        OkHttp3Utils.doPost("https://www.zhaoapi.cn/user/login", regmap, new GsonObjectCallback<bean_login>() {
            @Override
            public void onUi(bean_login beanlog) {

                if(slog!=null){
                    Save_Data.editor.putString("usname",beanlog.getData().getUsername().toString()).commit();
                    Save_Data.editor.putString("uid",beanlog.getData().getUid()+"").commit();
                    Save_Data.editor.putString("token",beanlog.getData().getToken().toString()).commit();
                    Save_Data.editor.putBoolean("YN",true).commit();
                    slog.onLogin(beanlog);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }

    @Override
    public void getloginphone(String logphone) {
        phonem=logphone;
    }

    @Override
    public void getloginpwd(String logpwd) {
        pwdm=logpwd;
    }

    //搜索物品
    @Override
    public void getDataSelect(final SwitchOnFinishListener seleon) {
        shoopmap.put("keywords",shoppname);
        shoopmap.put("page",page);
        OkHttp3Utils.doPost("https://www.zhaoapi.cn/product/searchProducts", shoopmap, new GsonObjectCallback<bean_select>() {
            @Override
            public void onUi(bean_select bsele) {
                if(seleon!=null){
                    seleon.onSelect(bsele);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }

    @Override
    public void getShopp(String shopp) {
        shoppname=shopp;
    }

    //商品详情
    @Override
    public void getDataDetail(final SwitchOnFinishListener detailsw) {
        detailmap.put("pid",pidm);

        OkHttp3Utils.doPost("https://www.zhaoapi.cn/product/getProductDetail",detailmap, new GsonObjectCallback<bean_detail>() {
            @Override
            public void onUi(bean_detail bdt) {
                if(detailsw!=null){
                    detailsw.onSelectDetail(bdt);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }

    @Override
    public void getDetailpid(String detail) {
        pidm=detail;
    }

    //三级列表商品
    @Override
    public void getDataSeSw(final SwitchOnFinishListener seleon) {
        swsemap.put("pscid",ppid);
        OkHttp3Utils.doPost("https://www.zhaoapi.cn/product/getProducts", swsemap, new GsonObjectCallback<bean_select>() {
            @Override
            public void onUi(bean_select bsele) {
                if(seleon!=null){
                    seleon.onSeSw(bsele);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }

    @Override
    public void getPscid(String pscid) {
        ppid=pscid;
    }

    //添加购物车
    @Override
    public void getDataAddShopp(final SwitchOnFinishListener swAddshopp) {
        detailmap.put("uid",uidm);
        detailmap.put("pid",pidm);
        detailmap.put("token",selleridm);
        OkHttp3Utils.doPost("https://www.zhaoapi.cn/product/addCart",detailmap, new GsonObjectCallback<bean_addshopp>() {
            @Override
            public void onUi(bean_addshopp badd) {
                if(swAddshopp!=null){
                    swAddshopp.onAddShopp(badd);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }

    @Override
    public void getuid(String uid) {
        uidm=uid;
    }

    @Override
    public void getpid(String pid) {
        pidm=pid;
    }

    @Override
    public void getsellerid(String sellerid) {
        selleridm=sellerid;
    }

    //显示购物车
    @Override
    public void getDataShopp(final SwitchOnFinishListener swShopp) {
        token= Save_Data.read.getString("token","");
        shoppmap.put("uid",uids);
        shoopmap.put("token",token);
        OkHttp3Utils.doPost("https://www.zhaoapi.cn/product/getCarts",shoppmap, new GsonObjectCallback<bean_shopp>() {
            @Override
            public void onUi(bean_shopp bshopp) {
                if(swShopp!=null){
                    swShopp.onShopp(bshopp);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }

    @Override
    public void getuids(String uid) {
        uids=uid;
    }

    @Override
    public void getDataAddress(final SwitchOnFinishListener swAddress) {
        token= Save_Data.read.getString("token","");
        uids= Save_Data.read.getString("uid","");
        shoppmap.put("uid",uids);
        shoopmap.put("token",token);
        OkHttp3Utils.doPost("https://www.zhaoapi.cn/user/getAddrs",shoppmap, new GsonObjectCallback<bean_showaddress>() {
            @Override
            public void onUi(bean_showaddress bsa) {
                if(swAddress!=null){
                    swAddress.onAddress(bsa);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }


}
