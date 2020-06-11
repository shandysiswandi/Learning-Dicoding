package com.premankoding.mcd3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.premankoding.mcd3.model.MovieModelResult;
import com.premankoding.mcd3.model.TvModelResult;

import java.util.ArrayList;
import java.util.Objects;

public class DetailTvActivity extends AppCompatActivity {
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatail_tv);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(R.string.msg_loading);
        progressDialog.setMessage(Objects.requireNonNull(this).getResources().getString(R.string.msg_message));
        progressDialog.show();

        TextView release, rating, overview, oriLang;

        ImageView poster = findViewById(R.id.poster);
        release = findViewById(R.id.release);
        rating = findViewById(R.id.rating);
        oriLang = findViewById(R.id.ori_lang);
        overview = findViewById(R.id.overview);

        ArrayList<TvModelResult> movieResults;
        movieResults = getIntent().getParcelableArrayListExtra("tvResults");
        int pos = getIntent().getIntExtra("position", 0);

        if(movieResults != null){
            progressDialog.dismiss();
            Objects.requireNonNull(getSupportActionBar()).setTitle(movieResults.get(pos).getName());
            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500" + movieResults.get(pos).getPosterPath())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(poster);
            rating.setText(Objects.requireNonNull(this).getResources().getString(R.string.rating)+movieResults.get(pos).getVoteAverage());

            release.setText(Objects.requireNonNull(this).getResources().getString(R.string.release)+movieResults.get(pos).getFirstAirDate());

            oriLang.setText(Objects.requireNonNull(this).getResources().getString(R.string.ori_lang)+movieResults.get(pos).getOriginalLanguage());

            overview.setText(Objects.requireNonNull(this).getResources().getString(R.string.overview)+movieResults.get(pos).getOverview());
        }
    }
}
