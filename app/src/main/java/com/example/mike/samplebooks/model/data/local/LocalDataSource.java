package com.example.mike.samplebooks.model.data.local;

import android.arch.persistence.room.Room;

import com.example.mike.samplebooks.ApplicationInstance;
import com.example.mike.samplebooks.model.Book;
import com.example.mike.samplebooks.model.data.DataCallback;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class LocalDataSource {

    private LocalBookDB getDB(){
        return Room.databaseBuilder(ApplicationInstance.getInstance(),
                LocalBookDB.class, "book.db").build();
    }

    public void getBooks(final DataCallback callback){

        Single.fromCallable(new Callable<List<Book>>() {
                    @Override
                    public List<Book> call() {
                        return getDB().bookDAO().getAll();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Book>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Book> books) {
                        callback.onSuccess(books);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e.getMessage());
                    }
                });
    }

    public void insertAll(final Book... books ){
        performAction(insertAllAction(books));
    }

    public void delete(Book book){
        performAction(deleteAction(book));
    }

    public void deleteAll(){
        performAction(deleteAllAction());
    }

    private void performAction(Action action){
        Completable.fromAction(action)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

    }

    public void replaceAll(final Book... books){
        Completable.fromAction(deleteAllAction())
                .andThen(Completable.fromAction(insertAllAction(books)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private Action deleteAction(final Book book){
        return new Action() {
            @Override
            public void run() throws Exception {
                getDB().bookDAO().delete(book);
            }
        };
    }

    private Action insertAllAction(final Book... books){
        return new Action() {
            @Override
            public void run() throws Exception {
                getDB().bookDAO().insertAll(books);
            }
        };
    }

    private Action deleteAllAction(){
        return new Action() {
            @Override
            public void run() throws Exception {
                getDB().bookDAO().clearDB();
            }
        };
    }



}
