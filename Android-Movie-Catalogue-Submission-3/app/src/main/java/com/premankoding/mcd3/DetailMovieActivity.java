package com.premankoding.mcd3;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.premankoding.mcd3.model.MovieModelResult;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class DetailMovieActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.progressBar);
        showLoading(true);

        TextView release, rating, overview, oriLang;

        ImageView poster = findViewById(R.id.poster);
        release = findViewById(R.id.release);
        rating = findViewById(R.id.rating);
        oriLang = findViewById(R.id.ori_lang);
        overview = findViewById(R.id.overview);

        ArrayList<MovieModelResult> movieResults;
        movieResults = getIntent().getParcelableArrayListExtra("movieResults");
        int pos = getIntent().getIntExtra("position", 0);

        if(movieResults != null){
            showLoading(false);
            Objects.requireNonNull(getSupportActionBar()).setTitle(movieResults.get(pos).getTitle());
            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500" + movieResults.get(pos).getPosterPath())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(poster);
            rating.setText(Objects.requireNonNull(this).getResources().getString(R.string.rating)+movieResults.get(pos).getVoteAverage());

            release.setText(Objects.requireNonNull(this).getResources().getString(R.string.release)+movieResults.get(pos).getReleaseDate());

            oriLang.setText(Objects.requireNonNull(this).getResources().getString(R.string.ori_lang)+movieResults.get(pos).getOriginalLanguage());

            overview.setText(Objects.requireNonNull(this).getResources().getString(R.string.overview)+movieResults.get(pos).getOverview());
        }

    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
