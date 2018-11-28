package com.example.mike.samplebooks.model.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomDatabase;

import com.example.mike.samplebooks.model.Book;

import java.util.List;

@Database( entities = Book.class, version = 1)
public abstract class LocalBookDB extends RoomDatabase {

    public abstract BookDAO bookDAO();

    @Dao
    public interface BookDAO{
        @Query("SELECT * FROM book")
        List<Book> getAll();

        @Insert
        void insertAll( Book... books );

        @Delete
        void delete(Book book);

        @Query("DELETE FROM book")
        void clearDB();
    }
}
