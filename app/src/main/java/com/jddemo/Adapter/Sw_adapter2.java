package com.jddemo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jddemo.R;
import com.jddemo.model.bean.three_leve.bean_two;
import com.jddemo.select_switchActivity;

import java.util.List;

/**
 * Created by 胡靖宇 on 2017/11/1.
 */

public class Sw_adapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<bean_two.DataBean> twolist;
    Sw_adapter3 sw3;
    List<bean_two.DataBean.ListBean> threelist;

    public Sw_adapter2(Context context, List<bean_two.DataBean> twolist) {
        this.context=context;
        this.twolist=twolist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.switch_item_two,parent,false);
        return new  Mytwo(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Mytwo mt=new Mytwo(holder.itemView);
        threelist = twolist.get(position).getList();
        mt.tvtwo.setText(twolist.get(position).getName().toString());
        sw3=new Sw_adapter3(context,threelist);
        mt.rc3.setAdapter(sw3);
        sw3.setOnItemClickListener(new Sw_adapter3.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int positiont) {

                Intent inte3=new Intent(context,select_switchActivity.class);
                inte3.putExtra("pscid",twolist.get(position).getList().get(positiont).getPscid()+"");
                context.startActivity(inte3);
            }
        });

    }

    @Override
    public int getItemCount() {
        return twolist.size();
    }


    public class Mytwo extends RecyclerView.ViewHolder{

        TextView tvtwo;
        RecyclerView rc3;

        public Mytwo(View itemView) {
            super(itemView);

            tvtwo=itemView.findViewById(R.id.sw2_item_tv);

            rc3=itemView.findViewById(R.id.sw2_rc);
            rc3.setLayoutManager(new GridLayoutManager(context,3));

        }
    }
}
