package com.example.mike.samplebooks.ui.booklist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mike.samplebooks.R;
import com.example.mike.samplebooks.model.Book;

import org.w3c.dom.Text;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

    List<Book> books;

    public BookListAdapter(List<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int hasAuthor) {
        if ( hasAuthor==1 ){
            View v = LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.book_list_item_has_author, viewGroup, false );
            return new ViewHolderHasAuthor(v);
        }else{
            View v = LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.book_list_item_no_author, viewGroup, false );
            return new ViewHolderNoAuthor(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Book b = books.get(i);

        viewHolder.name.setText( b.getTitle() );

        Glide.with(viewHolder.image.getContext())
                .load(b.getImageURL())
                .into(viewHolder.image);

        if ( viewHolder instanceof ViewHolderHasAuthor ){
            ((ViewHolderHasAuthor)viewHolder).author.setText( b.getAuthor() );
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    @Override
    public int getItemViewType(int position) {
        if ( books.get(position).getAuthor() == null || books.get(position).getAuthor().equals("")){
            return 0;
        }else{
            return 1;
        }
    }

    public abstract class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class ViewHolderNoAuthor extends ViewHolder {

        public ViewHolderNoAuthor(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById( R.id.ivNoAuthorImage );
            name = itemView.findViewById( R.id.ivNoAuthorName );
        }
    }

    public class ViewHolderHasAuthor extends ViewHolder {

        public TextView author;

        public ViewHolderHasAuthor(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById( R.id.ivAuthorImage );
            name = itemView.findViewById( R.id.tvAuthorName );
            author = itemView.findViewById( R.id.tvAuthorAuthor );
        }
    }


}
