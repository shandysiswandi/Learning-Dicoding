package com.premankoding.mcd4.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.premankoding.mcd4.R;
import com.premankoding.mcd4.adapter.TvAdapter;
import com.premankoding.mcd4.model.TvModelFrame;
import com.premankoding.mcd4.model.TvModelResult;
import com.premankoding.mcd4.viewmodel.TvViewModel;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment {

    private ArrayList<TvModelResult> tvResults = new ArrayList<>();
    private TvAdapter tvAdapter;
    private ProgressBar loading;

    public TvFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv, container, false);

        RecyclerView rvTv = view.findViewById(R.id.rvTv);

        loading = view.findViewById(R.id.loading);
        loading.setVisibility(View.VISIBLE);

        if (tvAdapter == null) {
            tvAdapter = new TvAdapter(getActivity(), tvResults);
            rvTv.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvTv.setAdapter(tvAdapter);
        } else {
            tvAdapter.notifyDataSetChanged();
        }

        TvViewModel tvViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(TvViewModel.class);
        tvViewModel.setTvResult();
        tvViewModel.getTvResult().observe(getActivity(), new Observer<TvModelFrame>() {
            @Override
            public void onChanged(TvModelFrame tvModelFrame) {
                tvResults.addAll(tvModelFrame.getResults());
                tvAdapter.notifyDataSetChanged();
                loading.setVisibility(View.GONE);
            }
        });



        return view;
    }

}
