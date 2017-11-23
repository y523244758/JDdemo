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
import com.jddemo.model.bean.three_leve.bean_two;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 胡靖宇 on 2017/11/2.
 */

public class Sw_adapter3 extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    Context context;
    List<bean_two.DataBean.ListBean> threelist;
    private OnItemClickListener mOnItemClickListener = null;
    public Sw_adapter3(Context context, List<bean_two.DataBean.ListBean> threelist) {
        this.context=context;
        this.threelist=threelist;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.switch_item_three,parent,false);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return new Mythree(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Mythree mt=new Mythree(holder.itemView);
        mt.itemView.setTag(position);
        mt.tvthree.setText(threelist.get(position).getName().toString());

        Picasso.with(context).load(threelist.get(position).getIcon()).placeholder(R.mipmap.ic_launcher).into(mt.img);

    }

    @Override
    public int getItemCount() {
        return threelist.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(view,(int)view.getTag());
        }
    }

    //点击事件
    public static interface OnItemClickListener {
        void onItemClick(View view , int positiont);

    }
    //最后暴露给外面的调用者，定义一个设置Listener的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public class Mythree extends RecyclerView.ViewHolder{

        TextView tvthree;
        ImageView img;
        public Mythree(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.sw3_item_img);
            tvthree=itemView.findViewById(R.id.sw3_item_tv);


        }
    }
}
