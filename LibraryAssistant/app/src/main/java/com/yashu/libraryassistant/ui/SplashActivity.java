package com.yashu.libraryassistant.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.yashu.libraryassistant.LibraryAssistantApp;
import com.yashu.libraryassistant.R;
import com.yashu.libraryassistant.common.Utils;
import com.yashu.libraryassistant.database.LibraryDbHelper;
import com.yashu.libraryassistant.model.Book;

import java.util.ArrayList;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new InitializeBooksAsync().execute();
    }

    private class InitializeBooksAsync extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            LibraryDbHelper dbHelper = LibraryAssistantApp.getAppInstance().getLibraryDbHelper();
            ArrayList<Book> books = Utils.loadBooksInfoFromAsset(SplashActivity.this);
            for (Book book: books) {
                dbHelper.addBookDetails(book);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent homeActivity = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(homeActivity);
            // close this activity
            finish();

        }
    }
}
