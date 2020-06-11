package com.premankoding.mcd3.service;

import com.premankoding.mcd3.model.MovieModelFrame;
import com.premankoding.mcd3.model.TvModelFrame;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIClient {
    @GET("movie?api_key=02efc4081250e3e7ad27fff31625ab28&language=en-US")
    Call<MovieModelFrame> getMovie();

    @GET("tv?api_key=02efc4081250e3e7ad27fff31625ab28&language=en-US")
    Call<TvModelFrame> getTv();

}
