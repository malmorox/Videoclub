package com.example.videoclub;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.net.HttpURLConnection;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ApiClient {
    private static final String URL = "https://devsapihub.com/api-movies";

    private static HttpURLConnection getConnection(){
        return getConnection(null);
    }
    private static HttpURLConnection getConnection(String endpoint) {
        HttpURLConnection connection = null;

        String finalUrl = URL;

        if (endpoint != null && !endpoint.isEmpty()) {
            finalUrl += endpoint;
        }
        try {
            URL url = new URL(finalUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
        } catch (Exception e) {
            Log.e("ApiClient", "Error en la conexion con la API:", e);
        }
        return connection;
    }
    public static List<Movie> getMovies() {
        HttpURLConnection connection = getConnection();
        List<Movie> movies = new ArrayList<>();

        if (connection == null) return movies;

        try {
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String json = readResponse(connection.getInputStream());

                Movie[] moviesArray = new Gson().fromJson(json, Movie[].class);
                movies = new ArrayList<>(Arrays.asList(moviesArray));
                Log.e("ApiClient", movies.toString());
            } else {
                Log.e("ApiClient", "Error en codigo de respuesta: " + responseCode);
            }
        } catch (Exception e) {
            Log.e("ApiClient", "Error" + e);
        } finally {
            connection.disconnect();
        }

        return movies;
    }

    public static Movie getMovieById(int id) {
        HttpURLConnection connection = getConnection("/" + id);

        Movie movie = null;

        if (connection == null) return movie;

        try {
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String json = readResponse(connection.getInputStream());

                movie = new Gson().fromJson(json, Movie.class);
            } else {
                Log.e("ApiClient", "Error en codigo de respuesta: " + responseCode);
            }
        } catch (Exception e) {
            Log.e("ApiClient", "Error" + e);
        } finally {
            connection.disconnect();
        }

        return movie;
    }

    private static String readResponse(InputStream responseInputStream) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(responseInputStream));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();
        return result.toString();
    }
}
