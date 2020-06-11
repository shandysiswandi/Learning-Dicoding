package com.premankoding.mcd5.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.premankoding.mcd5.R;
import com.premankoding.mcd5.adapter.TvAdapter;
import com.premankoding.mcd5.model.TvModelFrame;
import com.premankoding.mcd5.model.TvModelResult;
import com.premankoding.mcd5.viewmodel.TvViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class TvFragment extends Fragment {

    private ArrayList<TvModelResult> tvResults = new ArrayList<>();
    private TvAdapter tvAdapter;

    public TvFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv, container, false);

        RecyclerView rvTv = view.findViewById(R.id.rvTv);

        if (tvAdapter == null) {
            tvAdapter = new TvAdapter(getActivity(), tvResults);
            rvTv.setLayoutManager(new GridLayoutManager(getActivity(),2));
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
            }
        });

        return view;
    }

}
