package com.premankoding.mcd4.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.premankoding.mcd4.BuildConfig;
import com.premankoding.mcd4.DetailMovieActivity;
import com.premankoding.mcd4.R;
import com.premankoding.mcd4.model.MovieModelResult;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<MovieModelResult> movieResults;

    public MovieAdapter(Context context, ArrayList<MovieModelResult> movieResults) {
        this.context = context;
        this.movieResults = movieResults;
    }

    @NonNull
    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyViewHolder holder, final int position) {

        holder.title.setText(movieResults.get(position).getTitle());
        Glide.with(context)
                .load(BuildConfig.POSTER_URL + movieResults.get(position).getPosterPath())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.poster);

        holder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailMovieActivity.class);
                intent.putParcelableArrayListExtra("movieResults", movieResults);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieResults.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView title;

        private MyViewHolder(View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);
            title = itemView.findViewById(R.id.title);
        }
    }
}