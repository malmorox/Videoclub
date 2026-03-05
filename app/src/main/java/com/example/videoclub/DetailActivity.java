package com.example.videoclub;

import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.videoclub.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Movie movie = getIntent().getParcelableExtra("movie");

        if (movie != null) {
            getSupportActionBar().setTitle(movie.getTitle());

            Glide.with(this)
                    .load(movie.getImageUrl())
                    .centerCrop()
                    .into(binding.detailImage);
            binding.detailTitle.setText(movie.getTitle());
            binding.detailDescription.setText(movie.getDescription());
            binding.detailYearAndGenre.setText(movie.getYear() + " - " + movie.getGenre());
            binding.detailStars.setRating(movie.getStars());
        }

        /*int movieId = getIntent().getIntExtra("movie_id", -1);

        new Thread(() -> {
            Movie movie = ApiClient.getMovieById(movieId);
            runOnUiThread(() -> {
                if (movie != null) {
                    getSupportActionBar().setTitle(movie.getTitle());

                    Glide.with(this)
                            .load(movie.getImageUrl())
                            .centerCrop()
                            .into(binding.detailImage);
                    binding.detailTitle.setText(movie.getTitle());
                    binding.detailDescription.setText(movie.getDescription());
                    binding.detailYearAndGenre.setText(movie.getYear() + " - " + movie.getGenre());
                    binding.detailStars.setRating(movie.getStars());

                    binding.progressBar.setVisibility(View.GONE);
                    binding.detailImage.setVisibility(View.VISIBLE);
                    binding.detailTitle.setVisibility(View.VISIBLE);
                    binding.detailDescription.setVisibility(View.VISIBLE);
                    binding.detailYearAndGenre.setVisibility(View.VISIBLE);
                    binding.detailStars.setVisibility(View.VISIBLE);
                }
            });
        }).start();*/
    }
}