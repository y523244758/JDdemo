package com.jddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jddemo.model.bean.bean_sign_up;
import com.jddemo.presenter.ShowSwPresenterImpl;
import com.jddemo.utils.GsonObjectCallback;
import com.jddemo.utils.OkHttp3Utils;
import com.jddemo.view.ShowView_sign_Up;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class sign_upActivity extends AppCompatActivity implements ShowView_sign_Up{

    EditText phone,pws;
    Button btgo;
    String aname;
    String apwd;
    ShowSwPresenterImpl swPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        phone= (EditText) findViewById(R.id.signup_phone);
        pws= (EditText) findViewById(R.id.signup_password);
        btgo= (Button) findViewById(R.id.signup_go);



        swPresenter=new ShowSwPresenterImpl(this);

        btgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aname=phone.getText().toString();
                apwd=pws.getText().toString();

                swPresenter.relevanceSign_up();
            }
        });



    }




    @Override
    public void Showsign_upData(bean_sign_up sign_up) {
        if(sign_up.getCode().toString().equals(0)){
            Toast.makeText(sign_upActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(sign_upActivity.this,sign_up.getMsg().toString(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public String getphone() {
        return aname;
    }

    @Override
    public String getpwd() {
        return apwd;
    }
}
