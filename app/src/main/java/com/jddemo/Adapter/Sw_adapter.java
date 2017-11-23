package com.jddemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jddemo.R;
import com.jddemo.model.bean.three_leve.bean_one;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 胡靖宇 on 2017/11/1.
 */

public class Sw_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>implements View.OnClickListener{

    private OnItemClickListener mOnItemClickListener = null;

    Context context;
    List<bean_one.DataBean> blist;
    public Sw_adapter(Context context, List<bean_one.DataBean> blist) {
        this.context=context;
        this.blist=blist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.switch_item_one,parent,false);

        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return new  My_sw_ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        My_sw_ViewHolder my=new My_sw_ViewHolder(holder.itemView);
        my.itemView.setTag(position);
        my.tv_title.setText(blist.get(position).getName().toString());
        Picasso.with(context).load(blist.get(position).getIcon()).placeholder(R.mipmap.ic_launcher).into(my.img);
    }

    @Override
    public int getItemCount() {
        return blist.size();
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
        void onItemClick(View view , int position);

    }
    //最后暴露给外面的调用者，定义一个设置Listener的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public class My_sw_ViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv_title;
        ImageView img;
        public My_sw_ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.sw_item_tv);
            img=itemView.findViewById(R.id.sw_item_img);

        }
    }
}
