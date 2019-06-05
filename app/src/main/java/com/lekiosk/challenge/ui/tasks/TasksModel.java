package com.lekiosk.challenge.ui.tasks;

import android.support.annotation.NonNull;

import com.lekiosk.challenge.db.DbClient;
import com.lekiosk.challenge.models.Tache;
import com.lekiosk.challenge.services.ApiClient;
import com.lekiosk.challenge.services.RestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Badr Elattaoui
 * on 03/06/2019.
 */

public class TasksModel implements TasksContract.TasksModel {

    @Override
    public void getUserTasks(int id, final OnFinishListener listener) {
        final int finalUserId = id;
        ApiClient.getRetrofitClient().create(RestApi.class).getTodos(id).enqueue(new Callback<List<Tache>>() {
            @Override
            public void onResponse(@NonNull Call<List<Tache>> call, @NonNull Response<List<Tache>> response) {
                listener.onSuccessResponse(response);

                if(response.body() !=null){
                    for (Tache tache : response.body()){
                        try {
                            DbClient.getmDbHelper().insertUserTask(tache, finalUserId);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Tache>> call, @NonNull Throwable t) {
                listener.onFailureResponse(t);
            }
        });
    }

    @Override
    public void getSqLiteData(int userId, SqliteListener listener) {
        listener.onGetUserTasks(DbClient.getmDbHelper().getUserTask(userId));
    }
}
