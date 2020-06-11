package com.premankoding.mcd5;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.premankoding.mcd5.database.MovieContract;
import com.premankoding.mcd5.model.MovieModelResult;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class DetailMovieActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    boolean isFav = false;
    private ArrayList<MovieModelResult> movieResults;
    private int pos;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        ProgressBar loading = findViewById(R.id.loading);
        loading.setVisibility(View.VISIBLE);

        TextView release, rating, overview, oriLang;
        fab = findViewById(R.id.fab);

        ImageView poster = findViewById(R.id.poster);
        release = findViewById(R.id.release);
        rating = findViewById(R.id.rating);
        oriLang = findViewById(R.id.ori_lang);
        overview = findViewById(R.id.overview);

        movieResults = getIntent().getParcelableArrayListExtra("movieResults");
        pos = getIntent().getIntExtra("position", 0);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFav){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("Fav" +movieResults.get(pos).getTitle(),false);
                    editor.apply();
                    isFav = false;
                    delFav();

                    Snackbar.make(view, "Not Favorite", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("Fav" +movieResults.get(pos).getTitle(),true);
                    editor.apply();
                    isFav = true;
                    addFav();

                    Snackbar.make(view, "Favorite", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                update();
            }
        });

        isFav =  preferences.getBoolean("Fav" +movieResults.get(pos).getTitle(), false);
        update();

        if(movieResults != null){
            loading.setVisibility(View.GONE);
            Objects.requireNonNull(getSupportActionBar()).setTitle(movieResults.get(pos).getTitle());
            Glide.with(this)
                    .load(BuildConfig.POSTER_URL+ movieResults.get(pos).getPosterPath())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(poster);
            rating.setText(Objects.requireNonNull(this).getResources().getString(R.string.rating)+movieResults.get(pos).getVoteAverage());

            release.setText(Objects.requireNonNull(this).getResources().getString(R.string.release)+movieResults.get(pos).getReleaseDate());

            oriLang.setText(Objects.requireNonNull(this).getResources().getString(R.string.ori_lang)+movieResults.get(pos).getOriginalLanguage());

            overview.setText(Objects.requireNonNull(this).getResources().getString(R.string.overview)+movieResults.get(pos).getOverview());
        }

    }
    private void delFav() {
        getContentResolver()
                .delete(MovieContract.MovieEntry.CONTENT_URI.buildUpon()
                .appendPath(String.valueOf(movieResults.get(pos).getId())).build(),null,null);
    }

    private void addFav() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieContract.MovieEntry.Column_Id, movieResults.get(pos).getId());
        contentValues.put(MovieContract.MovieEntry.Column_Title, movieResults.get(pos).getTitle());
        contentValues.put(MovieContract.MovieEntry.Column_Poster, movieResults.get(pos).getPosterPath());
        contentValues.put(MovieContract.MovieEntry.Column_Overview, movieResults.get(pos).getOverview());
        contentValues.put(MovieContract.MovieEntry.Column_Release, movieResults.get(pos).getReleaseDate());
        contentValues.put(MovieContract.MovieEntry.Column_Rating, movieResults.get(pos).getVoteAverage());

        getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, contentValues);
    }

    private void update() {
        if(isFav){
            fab.setImageResource(R.drawable.ic_fav_ok);
        }else{
            fab.setImageResource(R.drawable.ic_fav_no);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
