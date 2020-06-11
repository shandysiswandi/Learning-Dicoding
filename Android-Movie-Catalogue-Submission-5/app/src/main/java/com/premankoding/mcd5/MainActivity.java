package com.premankoding.mcd5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.premankoding.mcd5.fragment.FavoriteMovieFragment;
import com.premankoding.mcd5.fragment.FavoriteTvFragment;
import com.premankoding.mcd5.fragment.MovieFragment;
import com.premankoding.mcd5.fragment.SettingFragment;
import com.premankoding.mcd5.fragment.TvFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.title_movie);
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.content, new MovieFragment())
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_movie:
                Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.title_movie);
                getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.content, new MovieFragment())
                        .commit();
                return true;
            case R.id.nav_tv:
                Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.title_tv);
                getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.content, new TvFragment())
                        .commit();
                return true;
            case R.id.nav_fav_movie:
                Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.title_favorite_movie);
                getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.content, new FavoriteMovieFragment())
                        .commit();
                return true;
            case R.id.nav_fav_tv:
                Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.title_favorite_tv);
                getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.content, new FavoriteTvFragment())
                        .commit();
                return true;
            case R.id.nav_setting:
                Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.title_settings);
                getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.content, new SettingFragment())
                        .commit();
                return true;
        }
        return false;
    }
}
