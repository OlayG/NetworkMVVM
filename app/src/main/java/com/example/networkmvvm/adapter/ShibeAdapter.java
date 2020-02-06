package com.example.networkmvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.networkmvvm.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShibeAdapter extends RecyclerView.Adapter<ShibeAdapter.ShibeViewHolder> {

    private List<String> urls;

    public ShibeAdapter() {
        urls = new ArrayList<>();
    }

    @NonNull
    @Override
    public ShibeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shibe_item, parent, false);
        return new ShibeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShibeViewHolder holder, int position) {
        String url = urls.get(position);
        // Use Image Loading Library
        /*Picasso.get()
                .load(url)
                .into(holder.ivShibe);*/

        Glide.with(holder.ivShibe.getContext())
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.ivShibe);
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
        notifyDataSetChanged();
    }

    class ShibeViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivShibe;

        ShibeViewHolder(@NonNull View itemView) {
            super(itemView);
            ivShibe = itemView.findViewById(R.id.ivShibe);
        }
    }
}
