package com.example.videoclub;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
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

    public Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        year = in.readInt();
        image_url = in.readString();
        genre = in.readString();
        stars = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(year);
        dest.writeString(image_url);
        dest.writeString(genre);
        dest.writeFloat(stars);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

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
