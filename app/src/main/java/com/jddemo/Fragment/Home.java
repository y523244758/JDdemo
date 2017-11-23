package com.jddemo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jddemo.R;
import com.jddemo.selectActivity;

/**
 * Created by 胡靖宇 on 2017/11/1.
 */

public class Home extends Fragment {


    LinearLayout ll;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局
        View view=  View.inflate(getContext(),R.layout.homefragment,null);

        ll=view.findViewById(R.id.ss);

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),selectActivity.class));
            }
        });

        return view;
    }
}
