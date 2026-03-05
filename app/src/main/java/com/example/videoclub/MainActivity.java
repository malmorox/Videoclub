package com.example.videoclub;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.videoclub.databinding.ActivityMainBinding;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().setTitle(R.string.movies);

        binding.moviesList.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MoviesAdapter();
        binding.moviesList.setAdapter(adapter);

        loadMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.moviesList.setVisibility(View.GONE);
            loadMovies();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void loadMovies() {
        new Thread(() -> {
            try {
                List<Movie> movies = ApiClient.getMovies();

                runOnUiThread(() -> {
                    if (movies != null && !movies.isEmpty()) {
                        adapter.setMovies(movies);

                        binding.progressBar.setVisibility(View.GONE);
                        binding.moviesList.setVisibility(View.VISIBLE);
                    } else {
                        Log.e("MainActivity", "No se pudieron cargar las peliculas");
                    }
                });
            } catch (Exception e) {
                runOnUiThread(() ->
                        Log.e("MainActivity", "Error: " + e)
                );
            }
        }).start();
    }
}