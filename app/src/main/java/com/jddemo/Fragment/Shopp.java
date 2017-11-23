package com.jddemo.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jddemo.LoginActivity;
import com.jddemo.R;
import com.jddemo.Save_Data;
import com.jddemo.model.bean.bean_shopp;
import com.jddemo.model.bean.bean_shopp_dele;
import com.jddemo.presenter.ShowSwPresenterImpl;
import com.jddemo.utils.GsonObjectCallback;
import com.jddemo.utils.OkHttp3Utils;
import com.jddemo.view.ShowView_shopp;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 胡靖宇 on 2017/11/1.
 */

public class Shopp extends Fragment implements ShowView_shopp{
    ExpandableListView expandableListView;
    CheckBox tv_shopcart_addselect;
    ExpandableAdapter exd;
    String uid;
    List<bean_shopp.DataBean> shopplist;
    TextView goodssize,tv_shopcart_totalprice,tv_shopcart_totalnum,tv_shopcart_submit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局
        View view=  View.inflate(getContext(),R.layout.shoppfragment,null);
        //总价
        tv_shopcart_totalprice=view.findViewById(R.id.tv_shopcart_totalprice);
        //总商品量
        tv_shopcart_totalnum=view.findViewById(R.id.tv_shopcart_totalnum);
        expandableListView=view.findViewById(R.id.els);

        //全选
        tv_shopcart_addselect=view.findViewById(R.id.tv_shopcart_addselect);
        //判断登陆
        if(Save_Data.read.getString("uid","").toString().equals("")){
            Toast.makeText(getActivity(),"请登陆",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
            uid= Save_Data.read.getString("uid","");
        //连接p层
        ShowSwPresenterImpl shp=new ShowSwPresenterImpl(this);
        shp.relrevanceShopp();


        tv_shopcart_addselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()) {

                    List<bean_shopp.DataBean> data = shopplist;

                    for (int i = 0; i < data.size(); i++) {
                        Log.i("xxx", data.get(i).isAllCheck() + "");
                        bean_shopp.DataBean groupDatas = shopplist.get(i);
                        groupDatas.setAllCheck(true);
                        List<bean_shopp.DataBean.ListBean> datas = shopplist.get(i).getList();
                        for (int j = 0; j < datas.size(); j++) {
                            Log.i("xxx", datas.get(j).isItemCheck() + "");
                            List<bean_shopp.DataBean.ListBean> childDatas = shopplist.get(i).getList();
                            for (bean_shopp.DataBean.ListBean childData : childDatas) {
                                childData.setItemCheck(true);
                            }
                        }
                    }
                    //刷新界面
                    notifyCheckAdapter();
                } else {

                    List<bean_shopp.DataBean> data = shopplist;
                    for (int i = 0; i < data.size(); i++) {
                        Log.i("xxx", data.get(i).isAllCheck() + "");
                        bean_shopp.DataBean groupDatas = shopplist.get(i);
                        groupDatas.setAllCheck(false);
                        List<bean_shopp.DataBean.ListBean> datas = shopplist.get(i).getList();
                        for (int j = 0; j < datas.size(); j++) {
                            Log.i("xxx", datas.get(j).isItemCheck() + "");
                            List<bean_shopp.DataBean.ListBean> childDatas = shopplist.get(i).getList();
                            for (bean_shopp.DataBean.ListBean childData : childDatas) {
                                childData.setItemCheck(false);
                            }
                        }
                    }
                    //刷新界面
                    notifyCheckAdapter();
                }
            }
        });


        return view;
    }

    @Override
    public void ShowShoppData(bean_shopp beanShopp) {

      shopplist=  beanShopp.getData();
        exd=new ExpandableAdapter(getContext(),shopplist);
        expandableListView.setAdapter(exd);
        for (int i=0;i<shopplist.size();i++){
            expandableListView.expandGroup(i);
        }


    }

    @Override
    public String getuid() {
        return uid;
    }

    public class ExpandableAdapter extends BaseExpandableListAdapter{
        Context context;
        List<bean_shopp.DataBean> shopplist;
        public ExpandableAdapter(Context context, List<bean_shopp.DataBean> shopplist) {
            this.context=context;
            this.shopplist=shopplist;
        }
        //删除条目的方法
        public void removeChildItem(int groupPosition,int childPosition){
            this.shopplist.get(groupPosition).getList().remove(childPosition);
            notifyDataSetChanged();
        }
        //删除条目的方法
        public void removeGroupItem(int groupPosition){
            this.shopplist.remove(groupPosition);
            notifyDataSetChanged();
        }
        @Override
        public int getGroupCount() {
            return shopplist.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return shopplist.get(i).getList().size();
        }

        @Override
        public Object getGroup(int i) {
            return shopplist.get(i);
        }

        @Override
        public Object getChild(int i, int i1) {
            return shopplist.get(i).getList().get(i1);
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

            view=View.inflate(context, R.layout.item_shopping, null);
            CheckBox iv_item_shopcart_shopselect=view.findViewById(R.id.iv_item_shopcart_shopselect);
            TextView tv_item_shopcart_shopname=(TextView) view.findViewById(R.id.tv_item_shopcart_shopname);

            tv_item_shopcart_shopname.setText(shopplist.get(i).getSellerName());

            if (shopplist.get(i).isAllCheck()) {
                iv_item_shopcart_shopselect.setChecked(true);
            } else {
                iv_item_shopcart_shopselect.setChecked(false);
            }
            //一级监听
            iv_item_shopcart_shopselect.setOnClickListener(new OnGroupClickListener(i, iv_item_shopcart_shopselect));


            return view;
        }

        @Override
        public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
            view=View.inflate(context, R.layout.item_shoppinggoods, null);

            TextView tv_item_shopcart_clothname=(TextView) view.findViewById(R.id.tv_item_shopcart_clothname);
            TextView tv_item_shopcart_cloth_price=view.findViewById(R.id.tv_item_shopcart_cloth_price);
            TextView tv_item_shopcart_cloth_num=view.findViewById(R.id.tv_item_shopcart_cloth_num);
            ImageView iv_item_shopcart_cloth_pic=(ImageView) view.findViewById(R.id.iv_item_shopcart_cloth_pic);
            ImageView iv_item_shopcart_cloth_delete=view.findViewById(R.id.iv_item_shopcart_cloth_delete);

            CheckBox tv_item_shopcart_clothselect=view.findViewById(R.id.tv_item_shopcart_clothselect);



            tv_item_shopcart_clothname.setText(shopplist.get(i).getList().get(i1).getTitle());
            tv_item_shopcart_cloth_price.setText("¥"+shopplist.get(i).getList().get(i1).getPrice()+"");
            tv_item_shopcart_cloth_num.setText("数量："+shopplist.get(i).getList().get(i1).getNum()+"");

            String img=shopplist.get(i).getList().get(i1).getImages().toString();
            String [] temp = null;
            temp = img.split("\\|");

            Picasso.with(iv_item_shopcart_cloth_pic.getContext()).load(temp[0]).into(iv_item_shopcart_cloth_pic);

            if (shopplist.get(i).getList().get(i1).isItemCheck()) {
                tv_item_shopcart_clothselect.setChecked(true);
            } else {
                tv_item_shopcart_clothselect.setChecked(false);
            }

            final Map<String,String> mapde=new HashMap<>();
           String tok= Save_Data.read.getString("token","");
            mapde.put("uid",uid);
            mapde.put("pid",shopplist.get(i).getList().get(i1).getPid()+"");
            mapde.put("token",tok);
            iv_item_shopcart_cloth_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    OkHttp3Utils.doPost("https://www.zhaoapi.cn/product/deleteCart", mapde, new GsonObjectCallback<bean_shopp_dele>() {
                        @Override
                        public void onUi(bean_shopp_dele bean_shopp_dele) {

                            if(bean_shopp_dele.getCode().equals("0")){
                                Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
                                removeChildItem(i,i1);

                                if(shopplist.get(i).getList().size()==0){
                                    removeGroupItem(i);

                                }
                            }
                        }

                        @Override
                        public void onFailed(Call call, IOException e) {

                        }
                    });
                }
            });


            tv_item_shopcart_clothselect.setOnClickListener(new OnChildCheckListener(i, i1, tv_item_shopcart_clothselect));
            return view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }


        //一级监听
        private class OnGroupClickListener implements View.OnClickListener {
            int groupPosition;
            CheckBox iv_item_shopcart_shopselect;

            public OnGroupClickListener(int groupPosition, CheckBox iv_item_shopcart_shopselect) {
                this.iv_item_shopcart_shopselect = iv_item_shopcart_shopselect;
                this.groupPosition = groupPosition;
            }


            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    //一级全选
                    setCheck(true);

                } else {
                    //取消全选
                    setCheck(false);
                    iv_item_shopcart_shopselect.setChecked(false);
                }
                notifyCheckAdapter();
            }

            public void setCheck(boolean checkFlag) {

                bean_shopp.DataBean groupDatas = shopplist.get(groupPosition);
                List<bean_shopp.DataBean> data= shopplist;

                //一级状态
                groupDatas.setAllCheck(checkFlag);

                //全选状态判断
                int num = 0;
                for (int i = 0; i < data.size(); i++) {
                    boolean allCheck = data.get(i).isAllCheck();
                    if (!allCheck) {
                        num++;
                    }
                }
                if (num == 0) {
                    iv_item_shopcart_shopselect.setChecked(true);
                } else {
                    iv_item_shopcart_shopselect.setChecked(false);
                }

                //二级状态
                List<bean_shopp.DataBean.ListBean> childDatas = groupDatas.getList();
                for (bean_shopp.DataBean.ListBean childData : childDatas) {
                    //二级状态
                    childData.setItemCheck(checkFlag);

                }

            }
        }


        //二级监听
        private class OnChildCheckListener implements View.OnClickListener {
            int groupPosition;
            int childPosition;
            CheckBox tv_item_shopcart_clothselect;

            public OnChildCheckListener(int groupPosition, int childPosition, CheckBox tv_item_shopcart_clothselect) {
                this.tv_item_shopcart_clothselect = tv_item_shopcart_clothselect;
                this.groupPosition = groupPosition;
                this.childPosition = childPosition;
            }

            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    //子选中
                    shopplist.get(groupPosition).getList().get(childPosition).setItemCheck(true);





                } else {
                    //子未选中
                    shopplist.get(groupPosition).getList().get(childPosition).setItemCheck(false);

                }
                //二级联动一级状态
                setParentCheckFlag();

                //检测状态 二级联动全选
                int num = 0;
                for (int i = 0; i < shopplist.size(); i++) {
                    boolean allCheck = shopplist.get(i).isAllCheck();
                    if (!allCheck) {
                        num++;
                    }

                }
                if (num == 0) {
                    tv_item_shopcart_clothselect.setChecked(true);
                } else {
                    tv_item_shopcart_clothselect.setChecked(false);
                }
            }

            //二级联动一级状态
            private void setParentCheckFlag() {
                bean_shopp.DataBean dataInfo = shopplist.get(groupPosition);
                List<bean_shopp.DataBean.ListBean> datasInfos= shopplist.get(groupPosition).getList();

                for (int i = 0; i < datasInfos.size(); i++) {
                    if (!datasInfos.get(i).isItemCheck()) {
                        //子未选中 父取消选中
                        dataInfo.setAllCheck(false);
                        notifyCheckAdapter();
                        return;
                    }
                    if (i == datasInfos.size() - 1) {
                        //子选中 父选中
                        dataInfo.setAllCheck(true);
                        notifyCheckAdapter();

                        return;
                    }


                }
//            没出现全选或者取消全选的时候执行的
                sum(datasInfos);
            }

        }



    }

    //统计数量和价格
    private void sum(List<bean_shopp.DataBean.ListBean> datasInfos) {
        int num = 0;
        int price = 0;

        List<bean_shopp.DataBean> data = shopplist;
        for (bean_shopp.DataBean parentData : data) {
            for (bean_shopp.DataBean.ListBean child :parentData.getList()) {
                if (child.isItemCheck()) {
                    num++;
                    price +=child.getPrice();
                }
            }
        }
        tv_shopcart_totalnum.setText("共"+num+"件商品");
        tv_shopcart_totalprice.setText("总价:"+price);
    }

    //刷新适配器界面
    private void notifyCheckAdapter() {
        sum(shopplist.get(0).getList());
        expandableListView.setAdapter(exd);
        int count = expandableListView.getCount();
        for (int i = 0; i < count; i++) {
            expandableListView.expandGroup(i);
        }
    }
}
