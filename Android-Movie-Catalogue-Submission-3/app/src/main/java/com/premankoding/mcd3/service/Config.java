package com.premankoding.mcd3.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Config {
    private static Retrofit getRetrofit(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/discover/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    public static  APIClient getApiClient(){
        return getRetrofit().create(APIClient.class);
    }
}
