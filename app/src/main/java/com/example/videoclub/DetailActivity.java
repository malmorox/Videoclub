package com.example.videoclub;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView titleView;

    private TextView descriptionView;
    private TextView yearAndGenreView;
    private MaterialRatingBar ratingBar;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        //TODO viewbinding
        //progressBar = findViewById(R.id.circularProgressDetail);
        imageView = findViewById(R.id.detailImage);
        titleView = findViewById(R.id.detailTitle);
        descriptionView = findViewById(R.id.detailDescription);
        yearAndGenreView = findViewById(R.id.detailYearAndGenre);
        ratingBar = findViewById(R.id.detailStars);

        Movie movie = getIntent().getParcelableExtra("movie");

        if (movie != null) {
            getSupportActionBar().setTitle(movie.getTitle());

            Glide.with(this)
                    .load(movie.getImageUrl())
                    .centerCrop()
                    .into(imageView);
            titleView.setText(movie.getTitle());
            descriptionView.setText(movie.getDescription());
            yearAndGenreView.setText(movie.getYear() + " - " + movie.getGenre());
            ratingBar.setRating(movie.getStars());
        }

        /*int movieId = getIntent().getIntExtra("movie_id", -1);

        new Thread(() -> {
            Movie movie = ApiClient.getMovieById(movieId);
            runOnUiThread(() -> {
                if (movie != null) {
                    getSupportActionBar().setTitle(movie.getTitle());

                    imageView = findViewById(R.id.detailImage);
                    Glide.with(this)
                            .load(movie.getImageUrl())
                            .centerCrop()
                            .into(imageView);

                    titleView.setText(movie.getTitle());
                    descriptionView.setText(movie.getDescription());
                    yearAndGenreView.setText(movie.getYear() + " - " + movie.getGenre());
                    ratingBar.setRating(movie.getStars());

                    progressBar.setVisibility(View.GONE);
                    imageView.setVisibility(View.VISIBLE);
                    titleView.setVisibility(View.VISIBLE);
                    descriptionView.setVisibility(View.VISIBLE);
                    yearAndGenreView.setVisibility(View.VISIBLE);
                    ratingBar.setVisibility(View.VISIBLE);
                }
            });
        }).start();*/
    }
}