package com.example.videoclub;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private List<Movie> movies = new ArrayList<>();

    public void setMovies(List<Movie> newMovies) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MoviesDiffCallback(movies, newMovies));

        movies = new ArrayList<>(newMovies);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    private static class MoviesDiffCallback extends DiffUtil.Callback {
        private final List<Movie> oldMoviesList;
        private final List<Movie> newMoviesList;

        MoviesDiffCallback(List<Movie> oldMoviesList, List<Movie> newMoviesList) {
            this.oldMoviesList = oldMoviesList;
            this.newMoviesList = newMoviesList;
        }

        @Override
        public int getOldListSize() {
            return oldMoviesList.size();
        }

        @Override
        public int getNewListSize() {
            return newMoviesList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            Log.d("MoviesAdapter", "[" + oldItemPosition + "," + newItemPosition + "]: "
                    + (oldMoviesList.get(oldItemPosition).getId() == newMoviesList.get(newItemPosition).getId()));
            return oldMoviesList.get(oldItemPosition).getId() == newMoviesList.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Movie oldItem = oldMoviesList.get(oldItemPosition);
            Movie newItem = newMoviesList.get(newItemPosition);
            return oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getDescription().equals(newItem.getDescription())
                    && oldItem.getYear() == newItem.getYear()
                    && oldItem.getImageUrl().equals(newItem.getImageUrl())
                    && oldItem.getGenre().equals(newItem.getGenre())
                    && Float.compare(oldItem.getStars(), newItem.getStars()) == 0;
        }
    }
}
