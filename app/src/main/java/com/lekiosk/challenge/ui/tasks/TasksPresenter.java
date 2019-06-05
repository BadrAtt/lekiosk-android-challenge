package com.lekiosk.challenge.ui.tasks;

import com.lekiosk.challenge.models.Tache;

import java.util.List;

import retrofit2.Response;

/**
 * Created by Badr Elattaoui
 * on 03/06/2019.
 */

public class TasksPresenter implements TasksContract.TasksPresenter,
        TasksContract.TasksModel.OnFinishListener, TasksContract.TasksModel.SqliteListener {

    private TasksContract.TasksView mTasksView;
    private TasksContract.TasksModel mTasksModel;
    private int mUserId;

    public TasksPresenter(TasksContract.TasksView mTasksView, TasksContract.TasksModel mTasksModel) {
        this.mTasksView = mTasksView;
        this.mTasksModel = mTasksModel;
    }

    @Override
    public void getUsersTasks(int id) {
        mTasksView.showProgress();
        mTasksModel.getUserTasks(id, this);
    }


    @Override
    public void onSuccessResponse(Response<List<Tache>> response) {
        mTasksView.hideProgress();
        mTasksView.setDataToRecyclerView(response.body());
    }

    @Override
    public void onFailureResponse(Throwable throwable) {
        mTasksView.hideProgress();
        mTasksView.displayNetworkError(throwable.getMessage());
    }

    @Override
    public void getOfflineData(int id) {
        mTasksModel.getSqLiteData(id, this);
    }

    @Override
    public void onGetUserTasks(List<Tache> list) {
        mTasksView.setDataToRecyclerView(list);
    }
}
