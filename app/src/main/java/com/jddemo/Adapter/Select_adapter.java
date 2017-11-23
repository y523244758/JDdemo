package com.jddemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jddemo.R;
import com.jddemo.model.bean.bean_select;
import com.jddemo.selectActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by 胡靖宇 on 2017/11/4.
 */

public class Select_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>implements View.OnClickListener{
    Context context;
    bean_select select;

    private OnItemClickListener mOnItemClickListener = null;

    public Select_adapter(Context context, bean_select select) {
        this.context=context;
        this.select=select;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_select,parent,false);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return new My_select_ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        My_select_ViewHolder ms=new My_select_ViewHolder(holder.itemView);
        ms.itemView.setTag(position);
        ms.title.setText(select.getData().get(position).getTitle().toString());
        ms.goods_subhead.setText(select.getData().get(position).getSubhead().toString());
        ms.goods_price.setText("￥"+select.getData().get(position).getPrice());
        ms.goods_salenum.setText("销量："+select.getData().get(position).getSalenum());

        String imgt=select.getData().get(position).getImages().toString();
        String [] tempse = null;
        tempse = imgt.split("\\|");
        Picasso.with(context).load(tempse[0]).placeholder(R.mipmap.ic_launcher).into(ms.img);
    }

    @Override
    public int getItemCount() {
        return select.getData().size();
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

    public class My_select_ViewHolder extends RecyclerView.ViewHolder{

        TextView title,goods_subhead,goods_price,goods_bargainPrice,goods_salenum;
        ImageView img;
        public My_select_ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.goods_title);
            goods_subhead = itemView.findViewById(R.id.goods_subhead);
            goods_price = itemView.findViewById(R.id.goods_price);
            goods_bargainPrice = itemView.findViewById(R.id.goods_bargainPrice);
            goods_salenum = itemView.findViewById(R.id.goods_salenum);

            img=itemView.findViewById(R.id.goods_img);

        }
    }
}
