package com.taiping.app.api;


import com.taiping.app.AppConstant;
import com.taiping.app.AppContext;
import com.taiping.app.BuildConfig;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public enum ClientFactory {
    INSTANCE;

    private volatile OkHttpClient okHttpClient;

    private static final int TIMEOUT_READ = 15;
    private static final int TIMEOUT_CONNECTION = 15;
    private final OkHttpClient.Builder mBuilder;

    ClientFactory() {
        mBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            mBuilder.addInterceptor(ClientHelper.getHttpLoggingInterceptor());
        }
        Cache cache = new Cache(new File(AppConstant.NET_DATA_PATH), 10 * 1024 * 1024);
        mBuilder.addNetworkInterceptor(ClientHelper.getAutoCacheInterceptor());
//        mBuilder.addInterceptor(ClientHelper.getAutoCacheInterceptor());
        mBuilder.cache(cache);
        mBuilder.retryOnConnectionFailure(true)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .build();
//        File file = new File("/sdcard/Pictures/myPicture/index.jpg");
//        File file1 = new File("/sdcard/Picuures/myPicture/me.txt");
//        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
//        Map<String, RequestBody> params = new HashMap<>();
//
//        params.put("file; filename="+ file.getName(), requestBody);
//        params.put("file\"; filename=\""+ file1.getName(), requestBody1);
    }


    private void onHttpsNoCertficates() {
        try {
            mBuilder.sslSocketFactory(ClientHelper.getSSLSocketFactory()).hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void onHttpCertficates(int[] certficates, String[] hosts) {
        mBuilder.socketFactory(ClientHelper.getSSLSocketFactory(AppContext.context(), certficates));
        mBuilder.hostnameVerifier(ClientHelper.getHostnameVerifier(hosts));
    }


    public OkHttpClient getHttpClient() {
        okHttpClient = mBuilder.build();
        return okHttpClient;
    }
}