package com.example.mike.samplebooks.model.data.remote;

import android.util.Log;

import com.example.mike.samplebooks.model.Book;
import com.example.mike.samplebooks.model.BookObserver;
import com.example.mike.samplebooks.model.data.DataCallback;
import com.example.mike.samplebooks.utils.NetworkAPI;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {

    public static final String TAG = "__TAG__";

    public Retrofit init(){
        return new Retrofit.Builder().baseUrl(NetworkAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Observable<List<Book>> getBooksObs(){
        return this.init().create(RemoteService.class).getBooks();
    }

    public void getBooks(final DataCallback callback){

        getBooksObs().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BookObserver() {
                    @Override
                    public void onSuccess(List<Book> books) {
                        Log.d(TAG, "onSuccess: "+books.size()+books.get(0).toString());
                        callback.onSuccess(books);
                    }

                    @Override
                    public void onFailure(String error) {
                        callback.onFailure(error);
                    }
                });

    }

}
