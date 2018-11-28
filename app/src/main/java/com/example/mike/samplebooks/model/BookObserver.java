package com.example.mike.samplebooks.model;

import com.example.mike.samplebooks.model.data.DataCallback;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BookObserver implements Observer<List<Book>>,DataCallback {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(List<Book>books){
        onSuccess(books);
    }

    @Override
    public void onError(Throwable e){
        onFailure(e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    @Override
    public abstract void onSuccess(List<Book> books);

    @Override
    public abstract void onFailure(String error);
}
