package com.example.mike.samplebooks.ui.booklist;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.mike.samplebooks.ApplicationInstance;
import com.example.mike.samplebooks.R;
import com.example.mike.samplebooks.model.Book;
import com.example.mike.samplebooks.model.data.DataCallback;

import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity {

    public static final String TAG = "__TAG__";

    private RecyclerView rvBookList;
    private BookListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvBookList = findViewById( R.id.rvBookList );
        adapter = new BookListAdapter( new ArrayList<Book>());
        rvBookList.setAdapter(adapter);
        rvBookList.setLayoutManager(new LinearLayoutManager(this));

        BookListViewModel vm = ViewModelProviders.of(this).get(BookListViewModel.class);
        vm.getBooks(new DataCallback() {
            @Override
            public void onSuccess(List<Book> books) {
                Log.d(TAG, "onSuccess: books in activity="+books.size());
                adapter.books = books;
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String error) {
                Log.d(TAG, "onFailure: "+error);
            }
        });
    }


}
