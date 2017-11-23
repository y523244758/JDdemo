package com.jddemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jddemo.R;
import com.jddemo.Save_Data;
import com.jddemo.ShowAddressActivity;
import com.jddemo.model.bean.bean_add_address;
import com.jddemo.model.bean.bean_showaddress;
import com.jddemo.utils.GsonObjectCallback;
import com.jddemo.utils.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 胡靖宇 on 2017/11/15.
 */

public class Address_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Map<String,String> map=new HashMap<>();
    Context context;
    bean_showaddress beanShowaddress;
    public Address_adapter(Context context, bean_showaddress beanShowaddress) {
        this.context=context;
        this.beanShowaddress=beanShowaddress;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_address,parent,false);

        return new Myaddress(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final Myaddress md=new Myaddress(holder.itemView);
        md.addresstv.setText("地址:"+beanShowaddress.getData().get(position).getAddr().toString());
        md.name.setText("姓名："+beanShowaddress.getData().get(position).getName().toString());
        md.phone.setText("电话:"+beanShowaddress.getData().get(position).getMobile()+"");
        if(beanShowaddress.getData().get(position).getStatus()==1){
            md.addmr.setText("默认地址");
        }else {
            md.addmr.setText("设为默认地址");
        }

        md.addmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.put("uid", Save_Data.read.getString("uid",""));
                map.put("token",Save_Data.read.getString("token",""));
                map.put("addrid",beanShowaddress.getData().get(position).getAddrid()+"");
                map.put("status",1+"");
                OkHttp3Utils.doPost("https://www.zhaoapi.cn/user/setAddr", map, new GsonObjectCallback<bean_add_address>() {
                    @Override
                    public void onUi(bean_add_address bean_add_address) {
                       if(bean_add_address.getCode().equals("0")){
                           Toast.makeText(context,"设为默认地址",Toast.LENGTH_SHORT).show();
                           md.addmr.setText("默认地址");
                       }
                    }

                    @Override
                    public void onFailed(Call call, IOException e) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanShowaddress.getData().size();
    }

    public class Myaddress extends RecyclerView.ViewHolder{

        TextView name,phone,addresstv,addmr;

        public Myaddress(View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            phone=itemView.findViewById(R.id.phone);
            addresstv=itemView.findViewById(R.id.addresstv);
            addmr=itemView.findViewById(R.id.mraddress);
        }
    }
}
