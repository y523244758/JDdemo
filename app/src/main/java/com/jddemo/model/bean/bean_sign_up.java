package com.jddemo.model.bean;

/**
 * Created by 胡靖宇 on 2017/11/2.
 */

public class bean_sign_up {


    /**
     * msg : 天呢！用户名或密码不能为空
     * code : 1
     * data : {}
     */

    private String msg;
    private String code;
    private String data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
