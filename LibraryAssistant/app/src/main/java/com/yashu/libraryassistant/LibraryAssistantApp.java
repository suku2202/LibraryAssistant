package com.yashu.libraryassistant;

import android.app.Application;

import com.yashu.libraryassistant.database.LibraryDbHelper;

/**
 * Created by Yashashwini on 09/09/17.
 */

public class LibraryAssistantApp extends Application{

    private static LibraryAssistantApp appInstance;

    LibraryDbHelper libraryDbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        libraryDbHelper = getLibraryDbHelper();
    }

    public static LibraryAssistantApp getAppInstance() {
        return appInstance;
    }

    public LibraryDbHelper getLibraryDbHelper() {
        if(libraryDbHelper == null){
            libraryDbHelper = new LibraryDbHelper(this);
        }
        return libraryDbHelper;
    }
}
