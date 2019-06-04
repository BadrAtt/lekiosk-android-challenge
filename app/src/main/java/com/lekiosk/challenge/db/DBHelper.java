package com.lekiosk.challenge.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lekiosk.challenge.models.Tache;
import com.lekiosk.challenge.models.Utilisateur;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Badr Elattaoui
 * on 04/06/2019.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "lekiosk_challenge";

    //USERS TABLE
    private static final String USERS_TABLE_NAME = "users";
    private static final String USER_COLUMN_USER_ID = "id";
    private static final String USER_COLUMN_USER_NAME = "name";
    private static final String USER_COLUMN_USER_USERNAME = "user_name";
    private static final String USER_COLUMN_USER_EMAIL = "email";


    //TASKS Table

    private static final String TASKS_TABLE_NAME = "tasks";
    private static final String TASK_CULUMN_TASK_ID = "id";
    private static final String TASK_CULUMN_TASK_TITLE = "title";
    private static final String TASK_CULUMN_TASK_STATE = "state";
    public static final String TASK_CULUMN_TASK_USER_ID = "user_id";


    DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
        public void onCreate(SQLiteDatabase db) {
        createUserTable(db);
        createUserTasksTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
        onCreate(db);
    }

    private void createUserTable(SQLiteDatabase db) {
        try {
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + USERS_TABLE_NAME + "("
                            + USER_COLUMN_USER_ID + " INTEGER, "
                            + USER_COLUMN_USER_NAME + " VARCHAR(20), "
                            + USER_COLUMN_USER_USERNAME + " VARCHAR(50), "
                            + USER_COLUMN_USER_EMAIL + " INTEGER DEFAULT 0) "
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createUserTasksTable(SQLiteDatabase db) {
        try {
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + TASKS_TABLE_NAME + "("
                            + USER_COLUMN_USER_ID + " INTEGER, "
                            + TASK_CULUMN_TASK_TITLE + " VARCHAR(20), "
                            + TASK_CULUMN_TASK_STATE+ " BOOLEAN(50))"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Utilisateur getUser(Long userId) throws Resources.NotFoundException, NullPointerException {
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(
                    "SELECT * FROM "
                            + USERS_TABLE_NAME
                            + " WHERE "
                            + USER_COLUMN_USER_ID
                            + " = ? ",
                    new String[]{userId + ""});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setmId(cursor.getInt(cursor.getColumnIndex(USER_COLUMN_USER_ID)));
                utilisateur.setmName(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_NAME)));
                utilisateur.setmUserName(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_USERNAME)));
                utilisateur.setmEmail(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_EMAIL)));

                return utilisateur;
            } else {
                throw new Resources.NotFoundException("User with id " + userId + " does not exists");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }

    public void insertUser(Utilisateur utilisateur) throws Exception {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(USER_COLUMN_USER_NAME, utilisateur.getmName());
            contentValues.put(USER_COLUMN_USER_ID, utilisateur.getmId());
            contentValues.put(USER_COLUMN_USER_USERNAME, utilisateur.getmUserName());
            contentValues.put(USER_COLUMN_USER_EMAIL, utilisateur.getmEmail());
            db.insert(USERS_TABLE_NAME, null, contentValues);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Tache getUserTask(int userId) throws Resources.NotFoundException, NullPointerException {
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(
                    "SELECT * FROM "
                            + TASKS_TABLE_NAME
                            + " WHERE "
                            + USER_COLUMN_USER_ID
                            + " = ? ",
                    new String[]{userId + ""});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                Tache tache = new Tache();
                tache.setmId(cursor.getInt(cursor.getColumnIndex(TASK_CULUMN_TASK_ID)));
                tache.setmTitle(cursor.getString(cursor.getColumnIndex(TASK_CULUMN_TASK_TITLE)));
                tache.setmCompleted(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(TASK_CULUMN_TASK_STATE))));

                return tache;
            } else {
                throw new Resources.NotFoundException("User with id " + userId + " does not exists");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }

    public void insertUserTask(Tache tache, int userId) throws Exception {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(TASK_CULUMN_TASK_ID, tache.getmId());
            contentValues.put(TASK_CULUMN_TASK_TITLE, tache.getmTitle());
            contentValues.put(TASK_CULUMN_TASK_STATE, tache.ismCompleted());
            contentValues.put(TASK_CULUMN_TASK_USER_ID, userId);
            db.insert(TASKS_TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Utilisateur> getAllUsers(){

        List<Utilisateur> allUsers = new ArrayList<>();
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(
                    "SELECT * FROM "
                            + USERS_TABLE_NAME,
                    null);

            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                do{
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setmId(cursor.getInt(cursor.getColumnIndex(USER_COLUMN_USER_ID)));
                    utilisateur.setmName(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_NAME)));
                    utilisateur.setmUserName(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_USERNAME)));
                    utilisateur.setmEmail(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_EMAIL)));

                    if(!allUsers.contains(utilisateur)){
                        allUsers.add(utilisateur);
                    }

                }while (cursor.moveToNext());
                db.close();
                return allUsers;

            } else {
                throw new Resources.NotFoundException("List of users is empty");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }
}
