package com.lekiosk.challenge.ui.tasks;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lekiosk.challenge.R;
import com.lekiosk.challenge.models.Tache;
import com.lekiosk.challenge.models.Utilisateur;

import java.util.List;

public class TasksFragment extends Fragment implements TasksContract.TasksView, View.OnClickListener {

    private int mUserId;
    private static final String USER_ID_TAG = "userId";
    private static final String TABLET_MODE_TAG = "tablet";
    private RecyclerView mTasksRecycler;
    private ProgressBar mLoadingProgress;
    private TasksListAdapter mAdapter;
    private TasksPresenter mTasksPresenter;
    private OnTaskFragmentInteraction mListener;
    private ImageView mBtnBack;
    private View mParentView;

    public TasksFragment() {
        // Required empty public constructor
    }

    public static TasksFragment newInstance(int userId, boolean isTabletMod) {

        TasksFragment tasksFragment = new TasksFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(USER_ID_TAG, userId);
        arguments.putBoolean(TABLET_MODE_TAG, isTabletMod);
        tasksFragment.setArguments(arguments);

        return tasksFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUserId = getArguments().getInt(USER_ID_TAG);
        }
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mParentView = view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView(mParentView);
        mTasksPresenter = new TasksPresenter(this, new TasksModel());
        mTasksPresenter.getUsersTasks(mUserId);
    }

    private void initView(View parent){
        mTasksRecycler = parent.findViewById(R.id.tasks_recycler);
        mLoadingProgress  = parent.findViewById(R.id.loading_progress);
        mBtnBack = parent.findViewById(R.id.btn_back);

        if(getArguments() != null && getArguments().getBoolean(TABLET_MODE_TAG)){
            mBtnBack.setVisibility(View.GONE);
        }
        else {
            mBtnBack.setOnClickListener(this);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnTaskFragmentInteraction) {
            mListener = (OnTaskFragmentInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTaskFragmentInteraction");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
    public void setDataToRecyclerView(List<Tache> tasksList) {
        mAdapter = new TasksListAdapter(tasksList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mTasksRecycler.setLayoutManager(linearLayoutManager);
        mTasksRecycler.setAdapter(mAdapter);
    }

    @Override
    public void displayNetworkError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_back){
            mListener.onTaskFragmentBackPressed();
        }
    }

    public interface OnTaskFragmentInteraction {
        void onTaskFragmentBackPressed();
    }
}
