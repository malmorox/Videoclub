package com.example.videoclub;

public class Movie {
    // se llaman las variables igual que las claves del json para que Gson pueda mapearlas bien
    private int id;
    private String title;
    private String description;
    private int year;
    private String image_url;
    private String genre;
    private float stars;

    public Movie(int id, String title, String description, int year, String image_url, String genre, float stars) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.year = year;
        this.image_url = image_url;
        this.genre = genre;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getYear() {
        return year;
    }

    public String getImageUrl() {
        return image_url;
    }

    public String getGenre() {
        return genre;
    }

    public float getStars() {
        return stars;
    }
}
