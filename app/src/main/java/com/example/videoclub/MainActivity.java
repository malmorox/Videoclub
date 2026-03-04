package com.example.videoclub;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerMovies;
    private MoviesAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.movies);

        progressBar = findViewById(R.id.circularProgressMain);

        recyclerMovies = findViewById(R.id.moviesList);
        recyclerMovies.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MoviesAdapter(new ArrayList<>());
        recyclerMovies.setAdapter(adapter);

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
            progressBar.setVisibility(View.VISIBLE);
            recyclerMovies.setVisibility(View.GONE);
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

                        progressBar.setVisibility(View.GONE);
                        recyclerMovies.setVisibility(View.VISIBLE);
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