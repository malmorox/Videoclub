package com.example.videoclub;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

class MovieViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView titleView;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.movieImage);
        titleView = itemView.findViewById(R.id.movieTitle);

        itemView.setOnClickListener(v -> {
            Movie movie = (Movie) itemView.getTag();

            if (movie != null) {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra("movie_id", movie.getId());
                itemView.getContext().startActivity(intent);
            }
        });
    }

    public void bind(Movie movie) {
        // el tag es un espacio de almacenamiento en cualquier view donde se puede guardar referencias
        itemView.setTag(movie);

        titleView.setText(movie.getTitle());

        Glide.with(itemView.getContext())
                .load(movie.getImageUrl())
                .into(imageView);
    }
}