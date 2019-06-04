package com.lekiosk.challenge.db;

import com.lekiosk.challenge.App;

/**
 * Created by Badr Elattaoui
 * on 04/06/2019.
 */

public class DbClient {

    private static DBHelper mDbHelper;

    public static DBHelper getmDbHelper() {

        if(mDbHelper == null){
            mDbHelper = new DBHelper(App.getmAppContext());
        }
        return mDbHelper;
    }
}
