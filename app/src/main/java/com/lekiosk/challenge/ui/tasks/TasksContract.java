package com.lekiosk.challenge.ui.tasks;

import com.lekiosk.challenge.models.Tache;

import java.util.List;

import retrofit2.Response;

/**
 * Created by Badr Elattaoui
 * on 02/06/2019.
 */

public interface TasksContract {

    interface TasksModel{

        void getUserTasks(int id, OnFinishListener listener);

        void getSqLiteData(int userId, TasksModel.SqliteListener listener);

        interface OnFinishListener {
            void onSuccessResponse(Response<List<Tache>> response);
            void onFailureResponse(Throwable throwable);
        }

        interface SqliteListener{
            void onGetUserTasks(List<Tache> list);
        }
    }

    interface TasksView{

        void showProgress();
        void hideProgress();
        void setDataToRecyclerView(List<Tache> usersList);
        void displayNetworkError(String message);
    }

    interface TasksPresenter{
        void getUsersTasks(int id);
        void getOfflineData(int id);
    }
}
