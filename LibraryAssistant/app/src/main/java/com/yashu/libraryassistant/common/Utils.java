package com.yashu.libraryassistant.common;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yashu.libraryassistant.model.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yashashwini on 03/09/17.
 */

public class Utils {


    public static ArrayList<Book> loadBooksInfoFromAsset(Context context) {
        JSONObject booksResponse = null;
        ArrayList<Book> books = new ArrayList<Book>();
        Gson gson = new Gson();
        try {
            booksResponse = new JSONObject(Utils.loadJsonFromAsset(context, "books"));
            JSONArray booksObj = booksResponse.getJSONArray("books");
            Type type = new TypeToken<List<Book>>() {
            }.getType();
            books = gson.fromJson(booksObj.toString(), type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static String loadJsonFromAsset(Context context, String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
