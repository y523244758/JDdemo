package com.jddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.jddemo.Adapter.Address_adapter;
import com.jddemo.model.bean.bean_showaddress;
import com.jddemo.presenter.ShowSwPresenterImpl;
import com.jddemo.view.ShowView_ShowAddress;

public class ShowAddressActivity extends AppCompatActivity implements ShowView_ShowAddress{

    RecyclerView rc;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_address);

        rc= (RecyclerView) findViewById(R.id.rcaddress);
        bt= (Button) findViewById(R.id.bt_add_address);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShowAddressActivity.this,AddressActivity.class));
            }
        });

        ShowSwPresenterImpl ssp=new ShowSwPresenterImpl(this);
        ssp.relrevanceAddress();
        rc.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void ShowAddressData(bean_showaddress beanShowaddress) {

        Address_adapter aad=new Address_adapter(this,beanShowaddress);
        rc.setAdapter(aad);

    }
}
