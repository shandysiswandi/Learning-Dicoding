package com.premankoding.mcd3.service;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.premankoding.mcd3.model.MovieModelFrame;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static MovieRepository movieRepository;
    private APIClient apiClient;
    private MutableLiveData<MovieModelFrame> movieData;

    public static MovieRepository getInstance(){
        if (movieRepository == null){
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    private MovieRepository(){
        apiClient = Config.getApiClient();
    }

    public MutableLiveData<MovieModelFrame> getMovie(){
        movieData = new MutableLiveData<>();
        apiClient.getMovie().enqueue(new Callback<MovieModelFrame>() {
            @Override
            public void onResponse(@NonNull Call<MovieModelFrame> call, @NonNull Response<MovieModelFrame> response) {
                if(response.isSuccessful()){
                    movieData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieModelFrame> call, @NonNull Throwable t) {
                movieData.setValue(null);
            }
        });
        return movieData;
    }
}