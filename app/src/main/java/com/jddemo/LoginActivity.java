package com.jddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jddemo.model.bean.bean_login;
import com.jddemo.presenter.ShowSwPresenterImpl;
import com.jddemo.view.ShowView_login;

public class LoginActivity extends AppCompatActivity implements ShowView_login{

    EditText pname,ppwd;
    Button login;
    ShowSwPresenterImpl swPresenter;
    String phone,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login= (Button) findViewById(R.id.login_go);

        pname= (EditText) findViewById(R.id.login_phone);
        ppwd= (EditText) findViewById(R.id.login_password);

        swPresenter=new ShowSwPresenterImpl(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone=pname.getText().toString();
                pwd=ppwd.getText().toString();

                swPresenter.relevanceLogin();
            }
        });

    }

    @Override
    public void ShowloginData(bean_login login) {

        if(login.getCode().equals("0")){
            Toast.makeText(LoginActivity.this,login.getData().getUsername()+"登陆成功！",Toast.LENGTH_SHORT).show();
            Intent inte=new Intent(LoginActivity.this,MainActivity.class);
            inte.putExtra("id",1);
            startActivity(inte);

        }else {
            Toast.makeText(LoginActivity.this,login.getMsg().toString(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public String getphone() {
        return phone;
    }

    @Override
    public String getpwd() {
        return pwd;
    }
}
