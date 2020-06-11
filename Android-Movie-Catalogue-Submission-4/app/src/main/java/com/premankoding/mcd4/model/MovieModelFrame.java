package com.premankoding.mcd4.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieModelFrame {

    @SerializedName("results")
    @Expose
    private ArrayList<MovieModelResult> results = null;

    public ArrayList<MovieModelResult> getResults() {
        return results;
    }


}
