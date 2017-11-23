package com.jddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.jddemo.Adapter.Select_adapter;
import com.jddemo.model.bean.bean_select;
import com.jddemo.presenter.ShowSwPresenterImpl;
import com.jddemo.view.ShowView_SeSw;

public class select_switchActivity extends AppCompatActivity implements ShowView_SeSw{
    String pscid;
    RecyclerView rcc;
    Select_adapter sd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_switch);

        Intent in=getIntent();
        pscid=in.getStringExtra("pscid");
        rcc= (RecyclerView) findViewById(R.id.rcss);
        ShowSwPresenterImpl spl=new ShowSwPresenterImpl(this);
        spl.relrevanceSeSw();
        //Toast.makeText(this,pscid,Toast.LENGTH_SHORT).show();
        rcc.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void ShowSeSwData(final bean_select select) {
        sd=new Select_adapter(this,select);
        rcc.setAdapter(sd);
        sd.setOnItemClickListener(new Select_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Toast.makeText(selectActivity.this,""+select.getData().get(position).getPid(),Toast.LENGTH_SHORT).show();
                Intent inte=new Intent(select_switchActivity.this,DetailActivity.class);
                inte.putExtra("pid",select.getData().get(position).getPid()+"");
                startActivity(inte);
            }
        });
    }

    @Override
    public String getPscid() {
        return pscid;
    }
}
