package com.lekiosk.challenge.ui.tasks;

import com.lekiosk.challenge.models.Tache;

import java.util.List;

import retrofit2.Response;

/**
 * Created by Badr Elattaoui
 * on 03/06/2019.
 */

public class TasksPresenter implements TasksContract.TasksPresenter, TasksContract.TasksModel.OnFinishListener {

    private TasksContract.TasksView mTasksView;
    private TasksContract.TasksModel mTasksModel;

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
}
