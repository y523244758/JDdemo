package com.jddemo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jddemo.Adapter.Sw_adapter;
import com.jddemo.Adapter.Sw_adapter2;
import com.jddemo.R;
import com.jddemo.model.bean.three_leve.bean_one;
import com.jddemo.model.bean.three_leve.bean_two;
import com.jddemo.presenter.ShowSwPresenterImpl;
import com.jddemo.view.ShowView_Sw;

import java.util.List;

/**
 * Created by 胡靖宇 on 2017/11/1.
 */

public class Switch extends Fragment implements ShowView_Sw{
    ShowSwPresenterImpl sp;
    RecyclerView rc1,rc2;
    Sw_adapter sd;
    Sw_adapter2 sd2;
    int id=1;

    List<bean_one.DataBean> blist;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局
        View view=  View.inflate(getContext(),R.layout.switchfragment,null);
        rc1=view.findViewById(R.id.rcsw_1);
        rc2=view.findViewById(R.id.rcsw_2);

        rc1.setLayoutManager(new LinearLayoutManager(getContext()));
        rc2.setLayoutManager(new LinearLayoutManager(getContext()));

        sp= new ShowSwPresenterImpl(this);
        sp.relevanceSw();

        sp.relevanceSw2();
        return view;
    }

    @Override
    public void ShowSwData(final bean_one bon) {
       blist= bon.getData();
        sd=new Sw_adapter(getContext(),blist);
        rc1.setAdapter(sd);


        sd.setOnItemClickListener(new Sw_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                id=   blist.get(position).getCid();
               // Toast.makeText(getActivity(),id+""+position,Toast.LENGTH_SHORT).show();

                sp.relevanceSw2();
            }
        });

    }

    @Override
    public void ShowSwData2(final bean_two two) {
       List<bean_two.DataBean> twolist= two.getData();
      sd2=new  Sw_adapter2(getContext(),twolist);
        rc2.setAdapter(sd2);

    }

    @Override
    public String getid() {
        return id+"";
    }
}
