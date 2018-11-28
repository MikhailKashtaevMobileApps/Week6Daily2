package com.example.mike.samplebooks.model.data;

import android.util.Log;

import com.example.mike.samplebooks.ApplicationInstance;
import com.example.mike.samplebooks.model.Book;
import com.example.mike.samplebooks.model.BookObserver;
import com.example.mike.samplebooks.model.data.local.LocalDataSource;
import com.example.mike.samplebooks.model.data.remote.RemoteDataSource;

import java.util.Date;
import java.util.List;

public class BookRepository {

    public static final String TAG = "__TAG__";
    RemoteDataSource remoteDataSource;
    LocalDataSource localDataSource;

    public BookRepository(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    public void getBooks(final DataCallback dataCallback){
        if (ApplicationInstance.getInstance().lastUpdated == null || new Date().getTime() - ApplicationInstance.getInstance().lastUpdated > 300000){
            // Fetch new data and update db
            remoteDataSource.getBooks(new DataCallback() {
                @Override
                public void onSuccess(List<Book> books) {
                    Log.d(TAG, "onSuccess: Repository success="+books.size());
                    //localDataSource.replaceAll((Book[])books.toArray());
                    //ApplicationInstance.getInstance().setLastUpdated( new Date().getTime() );
                    dataCallback.onSuccess(books);
                }

                @Override
                public void onFailure(String error) {
                    dataCallback.onFailure(error);
                }
            });
        }else{
            // Just get local shit
            localDataSource.getBooks(dataCallback);
        }
    }

    private void getBooksRemote(DataCallback dataCallback){
        remoteDataSource.getBooks(dataCallback);
    }

    private void getBooksLocal(DataCallback dataCallback){
        localDataSource.getBooks(dataCallback);
    }

}
