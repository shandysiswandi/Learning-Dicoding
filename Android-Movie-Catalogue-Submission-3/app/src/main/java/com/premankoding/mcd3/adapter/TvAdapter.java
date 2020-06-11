package com.premankoding.mcd3.adapter;

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
import com.premankoding.mcd3.DetailTvActivity;
import com.premankoding.mcd3.R;
import com.premankoding.mcd3.model.TvModelResult;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<TvModelResult> tvResults;

    public TvAdapter(Context context, ArrayList<TvModelResult> tvResults) {
        this.context = context;
        this.tvResults = tvResults;
    }

    @NonNull
    @Override
    public TvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvAdapter.MyViewHolder holder, final int position) {

        holder.title.setText(tvResults.get(position).getName());
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + tvResults.get(position).getPosterPath())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.poster);

        holder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailTvActivity.class);
                intent.putParcelableArrayListExtra("tvResults", tvResults);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvResults.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView title;

        private MyViewHolder(View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);
            title = itemView.findViewById(R.id.title);
        }
    }
}
