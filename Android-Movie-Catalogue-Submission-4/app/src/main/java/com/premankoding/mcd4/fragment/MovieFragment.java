package com.premankoding.mcd4.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.premankoding.mcd4.R;
import com.premankoding.mcd4.adapter.MovieAdapter;
import com.premankoding.mcd4.model.MovieModelFrame;
import com.premankoding.mcd4.model.MovieModelResult;
import com.premankoding.mcd4.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class MovieFragment extends Fragment {

    private ArrayList<MovieModelResult> movieResults = new ArrayList<>();
    private MovieAdapter movieAdapter;
    private ProgressBar loading;

    public MovieFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        RecyclerView rvMovie = view.findViewById(R.id.rvMovie);

        loading = view.findViewById(R.id.loading);
        loading.setVisibility(View.VISIBLE);

        if (movieAdapter == null) {
            movieAdapter = new MovieAdapter(getActivity(), movieResults);
            rvMovie.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            rvMovie.setAdapter(movieAdapter);
        } else {
            movieAdapter.notifyDataSetChanged();
        }

        MovieViewModel movieViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MovieViewModel.class);
        movieViewModel.setMovieResult();
        movieViewModel.getMovieResult().observe(getActivity(), new Observer<MovieModelFrame>() {
            @Override
            public void onChanged(MovieModelFrame movieModelFrame) {
                movieResults.addAll(movieModelFrame.getResults());
                movieAdapter.notifyDataSetChanged();
                loading.setVisibility(View.GONE);
            }
        });

        return view;
    }

}
