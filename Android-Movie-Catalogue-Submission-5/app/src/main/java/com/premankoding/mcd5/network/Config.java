package com.premankoding.mcd5.network;

import com.premankoding.mcd5.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Config {
    private static Retrofit getRetrofit(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    public static  APIClient getApiClient(){
        return getRetrofit().create(APIClient.class);
    }
}
