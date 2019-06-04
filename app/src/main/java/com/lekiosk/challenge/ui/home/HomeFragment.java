package com.lekiosk.challenge.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lekiosk.challenge.App;
import com.lekiosk.challenge.R;
import com.lekiosk.challenge.models.Utilisateur;

import java.util.List;


public class HomeFragment extends Fragment implements HomeContract.HomeView,
        HomeContract.HomeView.onRecyclerItemClickListener {

    private OnHomeFragmentInteractionListener mListener;

    private ProgressBar mLoadingProgress;
    private RecyclerView mUsersRecyClerView;
    private HomePresenter mHomePresenter;
    private UsersListAdapter mAdapter;
    private View mParentView;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mParentView  = view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView(mParentView);
        mHomePresenter = new HomePresenter(new HomeModel(), this);

        if(App.isConnected(App.getmAppContext())){//fetch data from remote
            mHomePresenter.getDataFromServer();
        }
        else { //get offline data from sqlite
            mHomePresenter.getOfflineData();
        }
    }

    private void initView(View parent){
        mLoadingProgress = parent.findViewById(R.id.loading_progress);
        mUsersRecyClerView = parent.findViewById(R.id.users_recycler);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHomeFragmentInteractionListener) {
            mListener = (OnHomeFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnHomeFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mAdapter = null;
        mHomePresenter = null;
        mLoadingProgress = null;
        mUsersRecyClerView = null;
    }

    @Override
    public void showProgress() {
        mLoadingProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mLoadingProgress.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(List<Utilisateur> usersList) {
        mAdapter = new UsersListAdapter(usersList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mUsersRecyClerView.setLayoutManager(linearLayoutManager);
        mUsersRecyClerView.setAdapter(mAdapter);
    }

    @Override
    public void displayNetworkError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(int userId) {
        mListener.getUserTasks(userId);
    }


    public interface OnHomeFragmentInteractionListener {
        void getUserTasks(int userId);
    }
}
