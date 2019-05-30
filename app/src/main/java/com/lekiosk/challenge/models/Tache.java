package com.lekiosk.challenge.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Badr Elattaoui
 * on 30/05/2019.
 */

public class Tache {

    @SerializedName("id")
    private int mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("completed")
    private boolean mCompleted;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public boolean ismCompleted() {
        return mCompleted;
    }

    public void setmCompleted(boolean mCompleted) {
        this.mCompleted = mCompleted;
    }
}
