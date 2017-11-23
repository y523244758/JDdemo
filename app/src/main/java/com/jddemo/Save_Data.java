package com.jddemo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 胡靖宇 on 2017/11/3.
 */

public class Save_Data {
    public static SharedPreferences read;
    public static SharedPreferences.Editor editor;

    public  static  String save_name;
    public  static  String save_uid;
    public  static  boolean save_YN;
    public  static  String save_token;




    public  static void saveData(Context context){
        read = context.getSharedPreferences("user",context.MODE_PRIVATE);
        editor= read.edit();
        save_YN=read.getBoolean("YN",false);
        save_name= read.getString("usname","");
        save_uid= read.getString("uid","");
        save_token=read.getString("token","");
        editor.commit();
    }
}
