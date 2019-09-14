package com.example.booksgoodreads.Model.RoomPackage;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "book_table")
public class Book {


    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String author;

    private String genre;

    private String imageToUrl;

    private double rating;

    public Book(String title, String author, String genre, String imageToUrl, double rating) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.imageToUrl = imageToUrl;
        this.rating = rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getImageToUrl() {
        return imageToUrl;
    }

    public double getRating() {
        return rating;
    }

}
