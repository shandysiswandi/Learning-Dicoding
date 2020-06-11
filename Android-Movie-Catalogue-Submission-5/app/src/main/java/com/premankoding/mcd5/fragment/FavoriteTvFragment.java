package com.premankoding.mcd5.fragment;


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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.premankoding.mcd5.R;
import com.premankoding.mcd5.adapter.TvAdapter;
import com.premankoding.mcd5.database.TvContract;
import com.premankoding.mcd5.model.TvModelResult;

import java.util.ArrayList;
import java.util.Objects;

public class FavoriteTvFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private RecyclerView mRecyclerView;
    private ArrayList<TvModelResult> tvResult;

    public FavoriteTvFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_tv, container, false);
        tvResult = new ArrayList<>();
        mRecyclerView = view.findViewById(R.id.rvFavoriteTv);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        getLoaderManager().initLoader(200, null, this);
        return view;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        if (id == 200) {
            return new CursorLoader(Objects.requireNonNull(getActivity()), TvContract.TvEntry.CONTENT_URI, null, null, null, null);
        }
        throw new RuntimeException("Loader Not Working");
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        tvResult.clear();
        if(data.getCount() > 0) {
            if (data.moveToFirst()) {
                do {
                    TvModelResult tvModel = new TvModelResult();
                    tvModel.setId(data.getInt(data.getColumnIndex(TvContract.TvEntry.Column_Id)));
                    tvModel.setName(data.getString(data.getColumnIndex(TvContract.TvEntry.Column_Title)));
                    tvModel.setPosterPath(data.getString(data.getColumnIndex(TvContract.TvEntry.Column_Poster)));
                    tvModel.setOverview(data.getString(data.getColumnIndex(TvContract.TvEntry.Column_Overview)));
                    tvModel.setFirstAirDate(data.getString(data.getColumnIndex(TvContract.TvEntry.Column_Release)));
                    tvModel.setVoteAverage(data.getDouble(data.getColumnIndex(TvContract.TvEntry.Column_Rating)));

                    tvResult.add(tvModel);
                    TvAdapter tvAdapter = new TvAdapter(getActivity(), tvResult);
                    mRecyclerView.setAdapter(tvAdapter);
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
