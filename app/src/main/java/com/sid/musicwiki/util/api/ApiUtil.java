package com.sid.musicwiki.util.api;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sid.musicwiki.util.AppConstants.BASE_URL;
import static com.sid.musicwiki.util.AppConstants.CUSTOM_CONNECT_TIMEOUT;
import static com.sid.musicwiki.util.AppConstants.CUSTOM_READ_TIMEOUT;

public class ApiUtil {

    private static OkHttpClient provideOkhttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .connectTimeout(CUSTOM_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(CUSTOM_READ_TIMEOUT, TimeUnit.SECONDS);
        return client.build();
    }

    public static Retrofit provideRetrofit() {

        OkHttpClient okHttpClient = provideOkhttpClient();

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


}
