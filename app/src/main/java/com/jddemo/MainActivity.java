package com.jddemo;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.jddemo.Fragment.Home;
import com.jddemo.Fragment.My;
import com.jddemo.Fragment.Shopp;
import com.jddemo.Fragment.Switch;

public class MainActivity extends FragmentActivity {

    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp= (ViewPager) findViewById(R.id.vp);
        Save_Data.saveData(getApplicationContext());
        Intent inte=getIntent();
        int i=inte.getIntExtra("id",0);

        if(i==1){
         vp.setCurrentItem(3);
        }else if(i==2){
            vp.setCurrentItem(2);
        }



        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;


                switch (position) {
                    case 0:
                        fragment = new Home();
                        break;
                    case 1:
                        fragment = new Switch();
                        break;
                    case 2:
                        fragment = new Shopp();
                        break;
                    case 3:
                        fragment = new My();
                        break;


                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });


    }

    public void bt(View view){
        switch (view.getId()){
            case R.id.jd_home:
                vp.setCurrentItem(0);
                break;
            case R.id.jd_switch:
                vp.setCurrentItem(1);
                break;
            case R.id.jd_shopp:
                vp.setCurrentItem(2);
                break;
            case R.id.jd_my:
                vp.setCurrentItem(3);
                break;

        }
    }

}
