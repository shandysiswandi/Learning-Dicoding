package com.premankoding.mcd4.fragment;


import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.premankoding.mcd4.R;
import com.premankoding.mcd4.adapter.MovieAdapter;
import com.premankoding.mcd4.database.MovieContract;
import com.premankoding.mcd4.model.MovieModelResult;

import java.util.ArrayList;
import java.util.Objects;

public class FavoriteMovieFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private RecyclerView mRecyclerView;
    private ArrayList<MovieModelResult> movieResult;

    public FavoriteMovieFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_movie, container, false);
        movieResult = new ArrayList<>();
        mRecyclerView = view.findViewById(R.id.rvFavoriteMovie);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        getLoaderManager().initLoader(100, null, this);
        return view;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        if (id == 100) {
            return new CursorLoader(Objects.requireNonNull(getActivity()), MovieContract.MovieEntry.CONTENT_URI, null, null, null, null);
        }
        throw new RuntimeException("Loader Not Working");
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        movieResult.clear();
        if(data.getCount() > 0) {
            if (data.moveToFirst()) {
                do {
                    MovieModelResult movieModel = new MovieModelResult();
                    movieModel.setId(data.getInt(data.getColumnIndex(MovieContract.MovieEntry.Column_Id)));
                    movieModel.setTitle(data.getString(data.getColumnIndex(MovieContract.MovieEntry.Column_Title)));
                    movieModel.setPosterPath(data.getString(data.getColumnIndex(MovieContract.MovieEntry.Column_Poster)));
                    movieModel.setOverview(data.getString(data.getColumnIndex(MovieContract.MovieEntry.Column_Overview)));
                    movieModel.setReleaseDate(data.getString(data.getColumnIndex(MovieContract.MovieEntry.Column_Release)));
                    movieModel.setVoteAverage(data.getDouble(data.getColumnIndex(MovieContract.MovieEntry.Column_Rating)));

                    movieResult.add(movieModel);
                    MovieAdapter movieAdapter = new MovieAdapter(getActivity(), movieResult);
                    mRecyclerView.setAdapter(movieAdapter);
                } while (data.moveToNext());
            }
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
