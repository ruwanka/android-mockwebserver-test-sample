package com.example.ruwa.mockservertest.service;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    public static String API_BASE_URL = "http://localhost/8080/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if(retrofit == null) {
            throw new IllegalStateException("service should be created first");
        }
        return retrofit;
    }

    public static <S> S create(Class<S> sc) {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(getLoggingInterceptor(HttpLoggingInterceptor.Level.BODY, "MOCK"))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(sc);
    }

    public static HttpLoggingInterceptor getLoggingInterceptor(HttpLoggingInterceptor.Level level,
                                                               final String tag) {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(tag, message);
            }
        }).setLevel(level);
    }

}
