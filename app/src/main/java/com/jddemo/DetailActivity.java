package com.jddemo;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.jddemo.model.bean.bean_addshopp;
import com.jddemo.model.bean.bean_detail;
import com.jddemo.presenter.ShowSwPresenterImpl;
import com.jddemo.view.ShowViewDetail;
import com.jddemo.view.ShowView_addShopp;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements ShowViewDetail{
    //笔记本
    String pid,uid,sellerid;
    TextView goodsdetail_title,goodsdetail_subhead,goodsdetail_price,goodsdetail_salenum,goodsdetail_store_name, price, count1,pop_subhead;
    ImageView img;
    bean_detail.DataBean list;
    Button goodsdetail_joinshopping,goodsdetai_shopping;
    private PopupWindow pop;
    ShowSwPresenterImpl spl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        goodsdetail_title= (TextView) findViewById(R.id.goodsdetail_title);
        goodsdetail_subhead= (TextView) findViewById(R.id.goodsdetail_subhead);
        goodsdetail_price= (TextView) findViewById(R.id.goodsdetail_price);
        goodsdetail_salenum= (TextView) findViewById(R.id.goodsdetail_salenum);
        goodsdetail_store_name= (TextView) findViewById(R.id.goodsdetail_store_name);
        goodsdetail_joinshopping= (Button) findViewById(R.id.goodsdetail_joinshopping);
        goodsdetai_shopping= (Button) findViewById(R.id.goodsdetai_shopping);

        img= (ImageView) findViewById(R.id.goodsdetail_img);

        Intent in=getIntent();
        pid= in.getStringExtra("pid");
        uid= Save_Data.read.getString("uid","");
        sellerid=Save_Data.read.getString("token","");

        goodsdetail_joinshopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow();
            }
        });

        goodsdetai_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imgi=list.getImages().toString();
                String [] tempi = null;
                tempi = imgi.split("\\|");
                Intent inte=new Intent(DetailActivity.this,OrdersActivity.class);
                inte.putExtra("price",list.getPrice()+"");
                inte.putExtra("title",list.getTitle());
                inte.putExtra("img",tempi[0]);
                startActivity(inte);
            }
        });

        spl=new ShowSwPresenterImpl(this);

        spl.relevanceDetail();
    }

    @Override
    public void ShowDetailData(bean_detail beanDetail) {
       list= beanDetail.getData();

        goodsdetail_title.setText(beanDetail.getData().getTitle().toString());
        goodsdetail_subhead.setText(beanDetail.getData().getSubhead().toString());
        goodsdetail_price.setText("￥"+beanDetail.getData().getPrice()+"");
        goodsdetail_salenum.setText("销量"+beanDetail.getData().getSalenum()+"");
        goodsdetail_store_name.setText(beanDetail.getSeller().getName().toString());

        String imgy=beanDetail.getData().getImages().toString();
        String [] tempde = null;
        tempde = imgy.split("\\|");
        Picasso.with(this).load(tempde[0]).placeholder(R.mipmap.ic_launcher).into(img);


    }

    @Override
    public String getPid() {
        return pid;
    }


    //popwindow
    private void showPopupWindow (){


        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.goods_popwin, null);
        pop = new PopupWindow(contentView,
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        pop.setContentView(contentView);
        pop.setBackgroundDrawable(new ColorDrawable());
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        //设置各个控件的点击响应

        price = contentView.findViewById(R.id.popprice);
        count1 = contentView.findViewById(R.id.popcount);
        pop_subhead=contentView.findViewById(R.id.pop_subhead);

        price.setText("￥" + list.getPrice());
        count1.setText("销量："+list.getSalenum()+"件");
        pop_subhead.setText(list.getSubhead().toString());

        ImageView img = contentView.findViewById(R.id.imageView);
        ImageView imgpop = contentView.findViewById(R.id.popimg);
        Button join=contentView.findViewById(R.id.popjoinshooping);

        Picasso.with(imgpop.getContext()).load(list.getImages()).into(imgpop);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop.dismiss();
            }
        });

        //提交添加购物车
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spl.relrevanceAddShopp();

            }
        });

        //显示PopupWindow
        View rootview = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        pop.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

    }

    @Override
    public void ShowAddShoppData(bean_addshopp addshopp) {

      Toast.makeText(DetailActivity.this,addshopp.getMsg().toString(),Toast.LENGTH_SHORT).show();
        if(addshopp.getCode().equals("0")){
            showNormalDialog();
        }else {
            Toast.makeText(DetailActivity.this,"添加失败请重试",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public String getuid() {
        return uid;
    }

    @Override
    public String getpidt() {
        return pid;
    }

    @Override
    public String getsellerid() {
        return sellerid;
    }

    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(DetailActivity.this);
        normalDialog.setIcon(R.mipmap.ic_launcher_round);
        normalDialog.setTitle("已经加入购物车！");
        normalDialog.setMessage("加入的商品："+list.getTitle().toString());
        normalDialog.setPositiveButton("继续看看",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        dialog.dismiss();
                    }
                });
        normalDialog.setNegativeButton("去购物车",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        Intent show=new Intent(DetailActivity.this,MainActivity.class);

                        show.putExtra("id",2);
                        startActivity(show);
                        finish();

                    }
                });
        // 显示
        normalDialog.show();
    }

}
