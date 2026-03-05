package com.example.videoclub;

import android.content.Intent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.videoclub.databinding.ItemMovieBinding;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    private ItemMovieBinding binding;
    private Movie currentMovie;

    public MovieViewHolder(View itemView) {
        super(itemView);
        binding = ItemMovieBinding.bind(itemView);

        binding.getRoot().setOnClickListener(v -> {
            if (currentMovie != null) {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra("movie", currentMovie);
                itemView.getContext().startActivity(intent);
            }
        });
    }

    public void bind(Movie movie) {
        currentMovie = movie;

        binding.movieTitle.setText(movie.getTitle());

        Glide.with(itemView.getContext())
                .load(movie.getImageUrl())
                .into(binding.movieImage);
    }
}