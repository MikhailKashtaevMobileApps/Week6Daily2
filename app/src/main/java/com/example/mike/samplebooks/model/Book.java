package com.example.mike.samplebooks.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Book {

    @PrimaryKey
    public int id;

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("imageURL")
    @Expose
    public String imageURL;
    @SerializedName("author")
    @Expose
    public String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}