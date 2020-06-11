package com.premankoding.mcd5.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.premankoding.mcd5.model.MovieModelFrame;
import com.premankoding.mcd5.network.APIClient;
import com.premankoding.mcd5.network.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<MovieModelFrame> movieResult;

    public void setMovieResult(){
        if (movieResult != null){
            return;
        }

        movieResult = new MutableLiveData<>();
        APIClient apiClient = Config.getApiClient();
        apiClient.getMovie().enqueue(new Callback<MovieModelFrame>() {
            @Override
            public void onResponse(@NonNull Call<MovieModelFrame> call, @NonNull Response<MovieModelFrame> response) {
                if(response.isSuccessful()){
                    movieResult.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieModelFrame> call, @NonNull Throwable t) {
                movieResult.setValue(null);
            }
        });
    }

    public LiveData<MovieModelFrame> getMovieResult() {
        return movieResult;
    }
}
