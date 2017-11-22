package com.rxdemo.Presenter;

import com.rxdemo.MainActivity;
import com.rxdemo.Model.News;
import com.rxdemo.Model.ShowModel;
import com.rxdemo.Model.ShowModelSe;
import com.rxdemo.view.FinishListener;
import com.rxdemo.view.ShowView;

/**
 * Created by 胡靖宇 on 2017/11/22.
 */

public class ShowPresenterSe implements ShowPresenter,FinishListener{
    ShowView sV;
    ShowModel sm;
    public ShowPresenterSe(ShowView sV) {
        this.sV=sV;
        sm=new ShowModelSe();
    }

    @Override
    public void Success(News news) {
        sV.ShowData(news);
    }

    @Override
    public void PresenterData() {
        sm.getData(this);
    }
}
