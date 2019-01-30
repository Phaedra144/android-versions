package com.dpdgroup.androidversions.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static String BASE_URL_BACKEND = "http://www.json-generator.com/api/json/get/claNBwNqOG/";

    private static Retrofit getConnection(String urlType) {
        return new Retrofit.Builder()
                .baseUrl(urlType)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
    }

    public static AndroidVersionsAPI getAndroidVersions() {
        return getConnection(BASE_URL_BACKEND).create(AndroidVersionsAPI.class);
    }


}
