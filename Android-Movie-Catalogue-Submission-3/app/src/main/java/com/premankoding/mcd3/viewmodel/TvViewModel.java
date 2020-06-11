package com.premankoding.mcd3.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.premankoding.mcd3.model.TvModelFrame;
import com.premankoding.mcd3.service.TvRepository;

public class TvViewModel extends ViewModel {

    private MutableLiveData<TvModelFrame> tvResult;

    public void setTvResult(){
        if (tvResult != null){
            return;
        }

        TvRepository tvRepository = TvRepository.getInstance();
        tvResult = tvRepository.getTv();

    }

    public LiveData<TvModelFrame> getTvResult() {
        return tvResult;
    }
}


