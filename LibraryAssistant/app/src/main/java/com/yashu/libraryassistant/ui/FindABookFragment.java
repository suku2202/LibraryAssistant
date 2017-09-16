package com.yashu.libraryassistant.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.yashu.libraryassistant.LibraryAssistantApp;
import com.yashu.libraryassistant.database.LibraryDbHelper;
import com.yashu.libraryassistant.model.Book;
import com.yashu.libraryassistant.common.BooksListAdapter;
import com.yashu.libraryassistant.R;
import com.yashu.libraryassistant.common.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FindABookFragment extends Fragment {


    private RecyclerView recyclerView;
    private EditText bookInfo;
    private BooksListAdapter booksListAdapter;
    private LibraryDbHelper dbHelper;
    private ArrayList<Book> books;

    public FindABookFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = LibraryAssistantApp.getAppInstance().getLibraryDbHelper();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View findABookView = inflater.inflate(R.layout.fragment_find_abook, container, false);

        bookInfo = (EditText) findABookView.findViewById(R.id.book_details);
        bookInfo.addTextChangedListener(textWatcher);
        books =  dbHelper.getBooksList();
        booksListAdapter = new BooksListAdapter(books);
        recyclerView = (RecyclerView) findABookView.findViewById(R.id.book_rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(booksListAdapter);
        return findABookView;
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s != null && s.length() > 0){
                books = dbHelper.getBooksList(s.toString());
                booksListAdapter.setBooks(books);
            }

        }
    };

}
