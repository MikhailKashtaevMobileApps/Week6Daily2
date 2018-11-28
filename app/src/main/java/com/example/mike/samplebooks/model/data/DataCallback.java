package com.example.mike.samplebooks.model.data;

import com.example.mike.samplebooks.model.Book;

import java.util.List;

public interface DataCallback {

    void onSuccess(List<Book> books);
    void onFailure(String error);

}
