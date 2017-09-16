package com.yashu.libraryassistant.common;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yashu.libraryassistant.R;
import com.yashu.libraryassistant.model.Book;

import java.util.ArrayList;

/**
 * Created by Yashashwini on 03/09/17.
 */

public class BooksListAdapter extends RecyclerView.Adapter<BooksListAdapter.ViewHolder> {

    ArrayList<Book> books;

    public BooksListAdapter (ArrayList<Book> books){
        this.books = books;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_row, parent, false);
        return new ViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.book_id.setText(book.getId());
        holder.book_name.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.edition.setText(book.getEdition());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView book_id, book_name, author, edition;
        public ViewHolder(View itemView) {
            super(itemView);
            book_id = (TextView) itemView.findViewById(R.id.book_id);
            book_name = (TextView) itemView.findViewById(R.id.book_name);
            author = (TextView) itemView.findViewById(R.id.author);
            edition = (TextView) itemView.findViewById(R.id.edition);

        }
    }

    public void setBooks(ArrayList<Book> books){
        this.books = books;
        notifyDataSetChanged();
    }


}
