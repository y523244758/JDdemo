package com.jddemo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jddemo.LoginActivity;
import com.jddemo.R;
import com.jddemo.Save_Data;
import com.jddemo.ShowAddressActivity;
import com.jddemo.sign_upActivity;

/**
 * Created by 胡靖宇 on 2017/11/1.
 */

public class My extends Fragment {

TextView my_signup,my_name;
    ImageView imglog;
    String uname;
    LinearLayout ll;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局
        View view=  View.inflate(getContext(),R.layout.myfragment,null);

        imglog=view.findViewById(R.id.my_login_img);
        my_signup=view.findViewById(R.id.my_signup);
        my_name=view.findViewById(R.id.my_name);
        ll=view.findViewById(R.id.myaddressll);

                 if(Save_Data.read.getString("usname","").isEmpty()){
                     my_name.setText("请登录");
                     my_signup.setVisibility(View.VISIBLE);
                 }  else {
                     my_name.setText(Save_Data.read.getString("usname",""));
                     my_signup.setVisibility(view.INVISIBLE);

                 }


        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),ShowAddressActivity.class));
            }
        });


        my_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),sign_upActivity.class));
            }
        });

        imglog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),LoginActivity.class));
            }
        });


        return view;
    }


}
