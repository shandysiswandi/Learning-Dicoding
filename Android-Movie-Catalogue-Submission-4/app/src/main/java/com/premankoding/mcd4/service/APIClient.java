package com.premankoding.mcd4.service;

import com.premankoding.mcd4.BuildConfig;
import com.premankoding.mcd4.model.MovieModelFrame;
import com.premankoding.mcd4.model.TvModelFrame;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIClient {
    @GET("movie?api_key="+BuildConfig.API_KEY+"&language=en-US")
    Call<MovieModelFrame> getMovie();

    @GET("tv?api_key="+ BuildConfig.API_KEY+"&language=en-US")
    Call<TvModelFrame> getTv();

}
