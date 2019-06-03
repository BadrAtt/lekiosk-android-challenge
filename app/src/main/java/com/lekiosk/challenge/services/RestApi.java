package com.lekiosk.challenge.services;

import com.lekiosk.challenge.models.Tache;
import com.lekiosk.challenge.models.Utilisateur;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Badr Elattaoui
 * on 01/06/2019.
 */


public interface RestApi {

    @GET("/users/")
    Call<List<Utilisateur>> getUsers();

    @GET("/todos")
    Call<List<Tache>> getTodos(@Query("userId") int userId);
}
