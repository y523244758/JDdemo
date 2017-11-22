package com.rxdemo.Model;

import com.rxdemo.ApiService;
import com.rxdemo.RetrofitUtils;
import com.rxdemo.view.FinishListener;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 胡靖宇 on 2017/11/22.
 */

public class ShowModelSe implements ShowModel {

    @Override
    public void getData(final FinishListener finish) {
        ApiService service= RetrofitUtils.getInstance().getApiService("http://www.93.gov.cn/",ApiService.class);
        Observable<News> observable=service.getData("0","0");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<News>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(News news) {

                        finish.Success(news);
                    }
                });

    }
}
