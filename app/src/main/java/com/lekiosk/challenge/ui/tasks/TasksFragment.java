package com.lekiosk.challenge.ui.tasks;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lekiosk.challenge.R;

public class TasksFragment extends Fragment {

    private int mUserId;
    private static final String USER_ID_TAG = "userId";

    public TasksFragment() {
        // Required empty public constructor
    }

    public static TasksFragment newInstance(int userId) {

        TasksFragment tasksFragment = new TasksFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(USER_ID_TAG, userId);
        tasksFragment.setArguments(arguments);

        return tasksFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUserId = getArguments().getInt(USER_ID_TAG);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
