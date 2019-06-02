package com.lekiosk.challenge.ui.home;

import android.support.annotation.NonNull;

import com.lekiosk.challenge.models.Utilisateur;
import com.lekiosk.challenge.services.ApiClient;
import com.lekiosk.challenge.services.RestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Badr Elattaoui
 * on 01/06/2019.
 */

public class HomeModel implements HomeContract.HomeModel {


    @Override
    public void getData(final onFinishListener listener) {
        ApiClient.getRetrofitClient().create(RestApi.class).getUsers().enqueue(new Callback<List<Utilisateur>>() {
            @Override
            public void onResponse(@NonNull Call<List<Utilisateur>> call, @NonNull Response<List<Utilisateur>> response) {
                listener.onSuccessResponse(response);
            }

            @Override
            public void onFailure(@NonNull Call<List<Utilisateur>> call, @NonNull Throwable t) {
                listener.onFailureResponse(t);
            }
        });
    }
}
