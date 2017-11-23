package com.jddemo.view;

import com.jddemo.model.bean.bean_addshopp;
import com.jddemo.model.bean.bean_detail;

/**
 * Created by 胡靖宇 on 2017/11/7.
 */

public interface ShowViewDetail {
    void ShowDetailData(bean_detail beanDetail);
    String getPid();

    void ShowAddShoppData(bean_addshopp addshopp);
    String getuid();
    String getpidt();
    String getsellerid();

}
