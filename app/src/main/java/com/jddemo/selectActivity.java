package com.jddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jddemo.Adapter.Select_adapter;
import com.jddemo.model.bean.bean_select;
import com.jddemo.presenter.ShowSwPresenterImpl;
import com.jddemo.view.ShowView_select;

public class selectActivity extends AppCompatActivity implements ShowView_select{
        //笔记本

    EditText search_bar;
    RecyclerView rc;
    Button search_go;
    String shoopname;
    ShowSwPresenterImpl spl;
    Select_adapter sd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        search_bar= (EditText) findViewById(R.id.search_text);
        rc= (RecyclerView) findViewById(R.id.select_goods);
        search_go= (Button) findViewById(R.id.search_go);

         spl=new ShowSwPresenterImpl(this);

        search_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoopname=search_bar.getText().toString();

                spl.relevanceSelect();
            }
        });

        rc.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void ShowSelectData(final bean_select select) {

        sd=new Select_adapter(this,select);
        rc.setAdapter(sd);
        sd.setOnItemClickListener(new Select_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
               // Toast.makeText(selectActivity.this,""+select.getData().get(position).getPid(),Toast.LENGTH_SHORT).show();
                Intent inte=new Intent(selectActivity.this,DetailActivity.class);
                inte.putExtra("pid",select.getData().get(position).getPid()+"");
                startActivity(inte);
            }
        });
    }

    @Override
    public String getshoppname() {
        return shoopname;
    }
}
