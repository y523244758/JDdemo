package com.jddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jddemo.model.bean.bean_add_address;
import com.jddemo.utils.GsonObjectCallback;
import com.jddemo.utils.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class AddressActivity extends AppCompatActivity {

    Map<String,String> addmap=new HashMap<>();
    EditText add_address_name,add_address_phonenum,add_address_address;
    Button add_address_bt;
    String name,phone,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        add_address_name= (EditText) findViewById(R.id.add_address_name);
        add_address_phonenum= (EditText) findViewById(R.id.add_address_phonenum);
        add_address_address= (EditText) findViewById(R.id.add_address_address);
        add_address_bt= (Button) findViewById(R.id.add_address_bt);
        add_address_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name= add_address_name.getText().toString();
                phone=  add_address_phonenum.getText().toString();
                address=  add_address_address.getText().toString();
                okhttp();
            }
        });


    }

    private void okhttp() {
        addmap.put("uid",Save_Data.read.getString("uid",""));
        addmap.put("addr",address);
        addmap.put("mobile",phone);
        addmap.put("name",name);
        addmap.put("token",Save_Data.read.getString("token",""));
        OkHttp3Utils.doPost("https://www.zhaoapi.cn/user/addAddr",addmap,new GsonObjectCallback<bean_add_address>() {
            @Override
            public void onUi(bean_add_address bean_add_address) {

                if(bean_add_address.getCode().equals("0")){
                    Toast.makeText(AddressActivity.this,bean_add_address.getMsg().toString(),Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddressActivity.this,ShowAddressActivity.class));
                    finish();
                }else {
                    Toast.makeText(AddressActivity.this,bean_add_address.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
