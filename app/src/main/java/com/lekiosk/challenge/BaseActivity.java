package com.lekiosk.challenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.lekiosk.challenge.ui.home.HomeFragment;
import com.lekiosk.challenge.ui.tasks.TasksFragment;

public class BaseActivity extends AppCompatActivity implements HomeFragment.OnHomeFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initView();
    }

    //init our view with the HomeFragment
    public void initView(){

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.base_container, HomeFragment.newInstance())
                .commit();
    }


    @Override
    public void getUserTasks(int userId) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.base_container, TasksFragment.newInstance(userId))
                .commit();
    }
}
