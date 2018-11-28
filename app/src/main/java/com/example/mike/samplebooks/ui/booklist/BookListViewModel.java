package com.example.mike.samplebooks.ui.booklist;

import android.arch.lifecycle.ViewModel;

import com.example.mike.samplebooks.model.data.BookRepository;
import com.example.mike.samplebooks.model.data.DataCallback;
import com.example.mike.samplebooks.model.data.local.LocalDataSource;
import com.example.mike.samplebooks.model.data.remote.RemoteDataSource;

public class BookListViewModel extends ViewModel {

    public void getBooks(DataCallback dataCallback){
        new BookRepository(new RemoteDataSource(), new LocalDataSource()).getBooks(dataCallback);
    }

}
