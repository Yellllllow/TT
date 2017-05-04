package com.taiping.app.api.remote;

import com.taiping.app.model.response.wx.WXItem;
import com.taiping.app.model.response.wx.WXResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface WXApi {

    @GET("wxnew")
    Observable<WXResult<List<WXItem>>> getWXHot(@Query("key") String key, @Query("num") int num, @Query("page") int page);

    @GET("wxnew")
    Observable<WXResult<List<WXItem>>> getWXHotSearch(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);
}

