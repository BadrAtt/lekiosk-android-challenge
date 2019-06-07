package com.lekiosk.challenge;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Badr Elattaoui
 * on 04/06/2019.
 */

//the Application class to maintain common objects shared in all the app
public class App extends Application {

    private static Context mAppContext;
    public static boolean mIsConnected = false;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
        mIsConnected = isConnected(getApplicationContext());
    }

    public static Context getmAppContext() {
        return mAppContext;
    }

    public static boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = null;
        if (cm != null) {
            netinfo = cm.getActiveNetworkInfo();
        }

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            return (mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting());
        } else
        return false;
    }
}
