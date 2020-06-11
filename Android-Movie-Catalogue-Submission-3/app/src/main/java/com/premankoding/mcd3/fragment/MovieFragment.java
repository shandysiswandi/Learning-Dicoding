package com.premankoding.mcd3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.premankoding.mcd3.R;
import com.premankoding.mcd3.adapter.MovieAdapter;
import com.premankoding.mcd3.model.MovieModelFrame;
import com.premankoding.mcd3.model.MovieModelResult;
import com.premankoding.mcd3.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class MovieFragment extends Fragment {

    private ArrayList<MovieModelResult> movieResults = new ArrayList<>();
    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;
    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        RecyclerView rvMovie = view.findViewById(R.id.rvMovie);

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

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
                progressBar.setVisibility(View.GONE);
            }
        });

        return view;
    }
}
