package com.lekiosk.challenge.ui.tasks;

import android.support.annotation.NonNull;

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
        ApiClient.getRetrofitClient().create(RestApi.class).getTodos(id).enqueue(new Callback<List<Tache>>() {
            @Override
            public void onResponse(@NonNull Call<List<Tache>> call, @NonNull Response<List<Tache>> response) {
                listener.onSuccessResponse(response);
            }

            @Override
            public void onFailure(@NonNull Call<List<Tache>> call, @NonNull Throwable t) {
                listener.onFailureResponse(t);
            }
        });
    }
}
