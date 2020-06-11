package com.premankoding.mcd5.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.premankoding.mcd5.model.TvModelFrame;
import com.premankoding.mcd5.network.APIClient;
import com.premankoding.mcd5.network.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvViewModel  extends ViewModel {
    private MutableLiveData<TvModelFrame> tvResult;

    public void setTvResult(){
        if (tvResult != null){
            return;
        }

        tvResult = new MutableLiveData<>();
        APIClient apiClient = Config.getApiClient();
        apiClient.getTv().enqueue(new Callback<TvModelFrame>() {
            @Override
            public void onResponse(@NonNull Call<TvModelFrame> call, @NonNull Response<TvModelFrame> response) {
                if(response.isSuccessful()){
                    tvResult.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvModelFrame> call, @NonNull Throwable t) {
                tvResult.setValue(null);
            }
        });

    }

    public LiveData<TvModelFrame> getTvResult() {
        return tvResult;
    }
}
