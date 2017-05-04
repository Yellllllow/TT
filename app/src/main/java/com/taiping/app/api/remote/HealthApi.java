package com.taiping.app.api.remote;

import com.taiping.app.model.HealthInfo;
import com.taiping.app.model.HealthResult;
import com.taiping.app.model.response.wx.WXItem;
import com.taiping.app.model.response.wx.WXResult;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;


public interface HealthApi {

    @GET("wxnew")
   Observable<WXResult<List<WXItem>>> getWXHot(@Query("key") String key, @Query("num") int num, @Query("page") int page);


    @GET("info/list")
    Observable<HealthResult> getHealthList(@Query("page") int page);


}

