package com.lekiosk.challenge.ui.home;

import com.lekiosk.challenge.models.Utilisateur;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by Badr Elattaoui
 * on 01/06/2019.
 */

public class HomePresenter implements HomeContract.HomePresenter,
        HomeContract.HomeModel.onFinishListener, HomeContract.HomeModel.SqliteListener {

    private HomeContract.HomeModel mHomeModel;
    private HomeContract.HomeView mHomeView;
    private List<Utilisateur> mUsersList;

    public HomePresenter(HomeContract.HomeModel mHomeModel, HomeContract.HomeView mHomeView) {
        this.mHomeModel = mHomeModel;
        this.mHomeView = mHomeView;
    }

    @Override
    public void getDataFromServer() {
        mHomeView.showProgress();
        mHomeModel.getData(this);
    }

    @Override
    public void getOfflineData() {
        mHomeView.showProgress();
        mHomeModel.getSqLiteData(this);
    }

    @Override
    public void onSuccessResponse(Response<List<Utilisateur>> response) {
        mHomeView.hideProgress();
        if(mUsersList == null){
            mUsersList = new ArrayList<>();
        }
        if(response.body() !=null){
            mUsersList = response.body();
            mHomeView.setDataToRecyclerView(mUsersList);
        }

    }

    @Override
    public void onFailureResponse(Throwable throwable) {
        mHomeView.hideProgress();
        mHomeView.displayNetworkError(throwable.getMessage());
    }

    @Override
    public void onGetAll(List<Utilisateur> list) {
        mHomeView.hideProgress();
        mHomeView.setDataToRecyclerView(list);
    }
}
