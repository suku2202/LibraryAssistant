package com.yashu.libraryassistant.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yashu.libraryassistant.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yashashwini on 09/09/17.
 */

public class LibraryDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LibraryAssistant.db";


    private static final String TABLE_BOOK = "book";
    private static final String COLUMN_BOOK_ISBN = "book_id";
    private static final String COLUMN_BOOK_TITLE = "title";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_EDITION = "edition";

    private static final String CREATE_BOOK_TABLE = "CREATE TABLE " + TABLE_BOOK + "("
            + COLUMN_BOOK_ISBN + " TEXT PRIMARY KEY," + COLUMN_BOOK_TITLE + " TEXT,"
            + COLUMN_AUTHOR + " TEXT," + COLUMN_EDITION + " TEXT" + ")";

    private static final String DELETE_BOOK_TABLE = "DROP TABLE IF EXISTS " + TABLE_BOOK;



    public LibraryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_BOOK_TABLE);
        onCreate(db);
    }

    public void addBookDetails(Book book){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_BOOK_ISBN, book.getId());
        values.put(COLUMN_BOOK_TITLE, book.getTitle());
        values.put(COLUMN_AUTHOR, book.getAuthor());
        values.put(COLUMN_EDITION, book.getEdition());

        // Inserting Row
        db.insert(TABLE_BOOK, null, values);
        db.close(); //
    }

    public ArrayList<Book> getBooksList() {
        ArrayList<Book> books = new ArrayList<Book>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BOOK;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setId(cursor.getString(0));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setEdition(cursor.getString(3));
                books.add(book);
            } while (cursor.moveToNext());
        }
        return books;
    }

    public ArrayList<Book> getBooksList(String searchData) {
        ArrayList<Book> books = new ArrayList<Book>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_BOOK, new String[]{COLUMN_BOOK_ISBN, COLUMN_BOOK_TITLE, COLUMN_AUTHOR, COLUMN_EDITION},
                COLUMN_BOOK_ISBN + " LIKE ? OR " + COLUMN_BOOK_TITLE + " LIKE ?",
                new String[]{"%" + String.valueOf(searchData) + "%", "%" + String.valueOf(searchData) + "%"}, null, null, null, null);
        if (cursor != null) {
            if(cursor.moveToFirst()){
                do {
                    Book book = new Book();
                    book.setId(cursor.getString(0));
                    book.setTitle(cursor.getString(1));
                    book.setAuthor(cursor.getString(2));
                    book.setEdition(cursor.getString(3));
                    books.add(book);
                } while (cursor.moveToNext());
            }
        }
        return books;
    }


}
