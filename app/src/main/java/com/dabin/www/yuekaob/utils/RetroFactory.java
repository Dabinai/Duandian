package com.dabin.www.yuekaob.utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dabin on 2017/11/23.
 */

public class RetroFactory {

    private RetroFactory() {
    }

    private static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .build();

    private static ApiService service = new Retrofit.Builder()
            .baseUrl(API.BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(client)
            .build()
            .create(ApiService.class);

    public static ApiService getInetce(){
        return service;
    }
}
