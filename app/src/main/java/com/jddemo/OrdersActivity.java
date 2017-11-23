package com.jddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jddemo.model.bean.bean_add_address;
import com.jddemo.pay.PayDemoActivity;
import com.jddemo.utils.GsonObjectCallback;
import com.jddemo.utils.OkHttp3Utils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class OrdersActivity extends AppCompatActivity {

    Map<String,String> map=new HashMap<>();
    ImageView img;
    TextView title,price;
    Button go,qx;
    Intent data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        img= (ImageView) findViewById(R.id.orders_img);
        title= (TextView) findViewById(R.id.order_title);
        price= (TextView) findViewById(R.id.order_price);
        go= (Button) findViewById(R.id.order_ok);
        qx= (Button) findViewById(R.id.order_qx);

        data=getIntent();

        title.setText(data.getStringExtra("title"));
        price.setText("￥"+data.getStringExtra("price"));
        Picasso.with(this).load(data.getStringExtra("img")).placeholder(R.mipmap.ic_launcher).into(img);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                okhttp();
            }
        });
    }

    private void okhttp() {

        map.put("uid",Save_Data.read.getString("uid",""));
        map.put("token",Save_Data.read.getString("token",""));
        map.put("price",data.getStringExtra("price"));
        OkHttp3Utils.doPost("https://www.zhaoapi.cn/product/createOrder", map, new GsonObjectCallback<bean_add_address>() {
            @Override
            public void onUi(bean_add_address bean_add_address) {
                if(bean_add_address.getCode().equals("0")){

                    Toast.makeText(OrdersActivity.this,"成功准备支付",Toast.LENGTH_SHORT).show();
                    Intent inte =new Intent(OrdersActivity.this,PayDemoActivity.class);
                    inte.putExtra("price",data.getStringExtra("price"));
                    inte.putExtra("title",data.getStringExtra("title"));
                    startActivity(inte);
                }else {
                    Toast.makeText(OrdersActivity.this,"失败",Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }

}
