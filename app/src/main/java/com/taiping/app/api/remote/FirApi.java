package com.taiping.app.api.remote;

import com.taiping.app.model.response.fir.Version;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;


public interface FirApi {

    @GET("/apps/latest/{id}")
    Observable<Version> getVersion(@Path("id") String id, @Query("api_token") String token);

    @GET
    Observable<String> getList(@Url String url,   @Query("channel") String changel);

    @FormUrlEncoded
    @POST
    Observable<String> getLifeInsurance(@Url String url, @Field("certiCode") String certiCode);


    @FormUrlEncoded
    @POST
    Observable<String> getCarInsurance(@Url String url, @Field("identifyNumber") String identifyNumber,@Field("pageIndex") String pageIndex,@Field("pageSize") String pageSize);


    @FormUrlEncoded
    @POST
    Observable<String> getFeedback(@Url String url, @Field("page") int page,@Field("size") int size);

    @FormUrlEncoded
    @POST
    Observable<String> commitFeedback(@Url String url, @FieldMap Map<String, String> map) ;

}
