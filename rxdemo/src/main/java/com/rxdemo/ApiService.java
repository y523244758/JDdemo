package com.rxdemo;

import com.rxdemo.Model.News;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 胡靖宇 on 2017/11/22.
 */

public interface ApiService {
    @GET("93app/data.do")
    Observable<News> getData(@Query("channelId") String id,@Query("startNum") String num);

}
