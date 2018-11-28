package com.example.mike.samplebooks.model.data.remote;

import com.example.mike.samplebooks.model.Book;
import com.example.mike.samplebooks.utils.NetworkAPI;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RemoteService {

    @GET(NetworkAPI.PATH)
    Observable<List<Book>> getBooks();

}
