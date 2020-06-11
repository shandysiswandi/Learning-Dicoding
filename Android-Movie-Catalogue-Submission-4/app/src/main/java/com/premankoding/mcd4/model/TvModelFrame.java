package com.premankoding.mcd4.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TvModelFrame {
    @SerializedName("results")
    @Expose
    private ArrayList<TvModelResult> results = null;

    public ArrayList<TvModelResult> getResults() {
        return results;
    }

}
