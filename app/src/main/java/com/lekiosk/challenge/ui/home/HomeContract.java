package com.lekiosk.challenge.ui.home;

import com.lekiosk.challenge.models.Utilisateur;

import java.util.List;

import retrofit2.Response;

/**
 * Created by Badr Elattaoui
 * on 01/06/2019.
 */

public interface HomeContract {

    interface HomeModel{

        void getData(onFinishListener listener);
        void getSqLiteData(SqliteListener listener);

        interface onFinishListener {
            void onSuccessResponse(Response<List<Utilisateur>> response);
            void onFailureResponse(Throwable throwable);
        }

        interface SqliteListener{
            void onGetAll(List<Utilisateur> list);
        }
    }

    interface HomeView{

        void showProgress();
        void hideProgress();
        void setDataToRecyclerView(List<Utilisateur> usersList);
        void displayNetworkError(String message);

        interface onRecyclerItemClickListener{
            void onItemClick(int userId);
        }
    }

    interface HomePresenter{
        void getDataFromServer();
        void getOfflineData();
    }
}
