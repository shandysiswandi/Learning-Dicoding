package com.premankoding.mcd5.network;

import com.premankoding.mcd5.BuildConfig;
import com.premankoding.mcd5.model.MovieModelFrame;
import com.premankoding.mcd5.model.TvModelFrame;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIClient {

    @GET("movie?api_key="+BuildConfig.API_KEY+"&language=en-US")
    Call<MovieModelFrame> getMovie();

    @GET("tv?api_key="+ BuildConfig.API_KEY+"&language=en-US")
    Call<TvModelFrame> getTv();

    @GET("search/movie?api_key="+ BuildConfig.API_KEY+"&language=en-US")
    Call<MovieModelFrame> getSearchMovie(@Query("query") String param);

    @GET("search/tv?api_key="+ BuildConfig.API_KEY+"&language=en-US")
    Call<TvModelFrame> getSearchTv(@Query("query") String param);
}
