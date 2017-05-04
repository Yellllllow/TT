package com.taiping.app.api.remote;


import com.taiping.app.AppConstant;
import com.taiping.app.api.ClientFactory;
import com.taiping.app.api.convert.Health.HealthGsonConverterFactory;
import com.taiping.app.api.convert.gan.GanGsonConverterFactory;
import com.taiping.app.api.convert.wx.WXGsonConverterFactory;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public enum ApiFactory {
    INSTANCE;

    private static WXApi sWXApi;
    private static GanApi sGanApi;
    private static FirApi sFirApi;

    private static HealthApi sHealthApo;

    ApiFactory() {
    }

    public static FirApi getFirApi() {
        if (sFirApi == null) {
            ApiFactory.sFirApi = createApi(AppConstant.API_FIR_URL, FirApi.class, ScalarsConverterFactory.create());
        }
        return sFirApi;
    }

    public static WXApi getWXApi() {
        if (sWXApi == null) {
            ApiFactory.sWXApi = createApi(AppConstant.API_WX_URL, WXApi.class, WXGsonConverterFactory.create());

        }
        return sWXApi;
    }

    public static GanApi getGanApi() {
        if (sGanApi == null) {
            ApiFactory.sGanApi = createApi(AppConstant.API_GAN_URL, GanApi.class, GanGsonConverterFactory.create());
        }
        return sGanApi;
    }


    public static HealthApi getsHealthApi() {
        if (sHealthApo == null) {
            ApiFactory.sHealthApo = createApi(AppConstant.API_HEALTH_URL, HealthApi.class, HealthGsonConverterFactory.create());

        }
        return sHealthApo;
    }

    private static <T> T createApi(String baseUrl, Class<T> t, Converter.Factory factory) {
        Retrofit.Builder mBuilder = new Retrofit.Builder()
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl);


        return mBuilder.client(ClientFactory.INSTANCE.getHttpClient()).build().create(t);
    }


}
