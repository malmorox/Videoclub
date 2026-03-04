package com.example.videoclub;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView titleView;
    private Movie currentMovie;

    public MovieViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.movieImage);
        titleView = itemView.findViewById(R.id.movieTitle);

        itemView.setOnClickListener(v -> {
            if (currentMovie != null) {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra("movie_id", currentMovie.getId());
                itemView.getContext().startActivity(intent);
            }
        });
    }

    public void bind(Movie movie) {
        currentMovie = movie;

        titleView.setText(movie.getTitle());

        Glide.with(itemView.getContext())
                .load(movie.getImageUrl())
                .into(imageView);
    }
}