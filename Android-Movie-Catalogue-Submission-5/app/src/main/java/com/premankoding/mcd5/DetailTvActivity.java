package com.premankoding.mcd5;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.premankoding.mcd5.database.TvContract;
import com.premankoding.mcd5.model.TvModelResult;

import java.util.ArrayList;
import java.util.Objects;

public class DetailTvActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    boolean isFav = false;
    private ArrayList<TvModelResult> tvResults;
    private int pos;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);

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

        tvResults = getIntent().getParcelableArrayListExtra("tvResults");
        pos = getIntent().getIntExtra("position", 0);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFav){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("favTv" +tvResults.get(pos).getName(),false);
                    editor.apply();
                    isFav = false;
                    delFav();

                    Snackbar.make(view, "Not Favorite", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("favTv" +tvResults.get(pos).getName(),true);
                    editor.apply();
                    isFav = true;
                    addFav();

                    Snackbar.make(view, "Favorite", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                update();
            }
        });

        isFav =  preferences.getBoolean("favTv" +tvResults.get(pos).getName(), false);
        update();

        if(tvResults != null){
            loading.setVisibility(View.GONE);
            Objects.requireNonNull(getSupportActionBar()).setTitle(tvResults.get(pos).getName());
            Glide.with(this)
                    .load(BuildConfig.POSTER_URL+ tvResults.get(pos).getPosterPath())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(poster);
            rating.setText(Objects.requireNonNull(this).getResources().getString(R.string.rating)+tvResults.get(pos).getVoteAverage());

            release.setText(Objects.requireNonNull(this).getResources().getString(R.string.release)+tvResults.get(pos).getFirstAirDate());

            oriLang.setText(Objects.requireNonNull(this).getResources().getString(R.string.ori_lang)+tvResults.get(pos).getOriginalLanguage());

            overview.setText(Objects.requireNonNull(this).getResources().getString(R.string.overview)+tvResults.get(pos).getOverview());
        }
    }

    private void delFav() {
        getContentResolver()
                .delete(TvContract.TvEntry.CONTENT_URI.buildUpon()
                .appendPath(String.valueOf(tvResults.get(pos).getId())).build(),null,null);
    }

    private void addFav() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TvContract.TvEntry.Column_Id, tvResults.get(pos).getId());
        contentValues.put(TvContract.TvEntry.Column_Title, tvResults.get(pos).getName());
        contentValues.put(TvContract.TvEntry.Column_Poster, tvResults.get(pos).getPosterPath());
        contentValues.put(TvContract.TvEntry.Column_Overview, tvResults.get(pos).getOverview());
        contentValues.put(TvContract.TvEntry.Column_Release, tvResults.get(pos).getFirstAirDate());
        contentValues.put(TvContract.TvEntry.Column_Rating, tvResults.get(pos).getVoteAverage());

        getContentResolver().insert(TvContract.TvEntry.CONTENT_URI, contentValues);
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
