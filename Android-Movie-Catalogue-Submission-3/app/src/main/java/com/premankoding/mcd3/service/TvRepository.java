package com.premankoding.mcd3.service;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.premankoding.mcd3.model.TvModelFrame;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvRepository {
    private static TvRepository tvRepository;
    private APIClient apiClient;
    private MutableLiveData<TvModelFrame> tvData;

    public static TvRepository getInstance(){
        if (tvRepository == null){
            tvRepository = new TvRepository();
        }
        return tvRepository;
    }

    private TvRepository(){
        apiClient = Config.getApiClient();
    }

    public MutableLiveData<TvModelFrame> getTv(){
        tvData = new MutableLiveData<>();
        apiClient.getTv().enqueue(new Callback<TvModelFrame>() {
            @Override
            public void onResponse(@NonNull Call<TvModelFrame> call, @NonNull Response<TvModelFrame> response) {
                if(response.isSuccessful()){
                    tvData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvModelFrame> call, @NonNull Throwable t) {
                tvData.setValue(null);
            }
        });
        return tvData;
    }

}
