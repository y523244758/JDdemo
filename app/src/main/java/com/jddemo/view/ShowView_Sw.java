package com.jddemo.view;

import com.jddemo.model.bean.three_leve.bean_one;
import com.jddemo.model.bean.three_leve.bean_two;

/**
 * Created by 胡靖宇 on 2017/11/1.
 */

public interface ShowView_Sw {
    //列表
    void ShowSwData(bean_one bon);
    void ShowSwData2(bean_two two);
    String getid();

}
