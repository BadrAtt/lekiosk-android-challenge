package com.lekiosk.challenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.lekiosk.challenge.ui.home.HomeFragment;
import com.lekiosk.challenge.ui.tasks.TasksFragment;

public class BaseActivity extends AppCompatActivity implements HomeFragment.OnHomeFragmentInteractionListener,
        TasksFragment.OnTaskFragmentInteraction {

    private HomeFragment homeFragment;
    private TasksFragment tasksFragment;
    private final String HOME_FRAGMENT_TAG = "HOMEFRAGMENT";
    private final String TASKS_FRAGMENT_TAG = "TASKSFRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initView();
    }

    //init our view with the HomeFragment
    public void initView(){

        homeFragment = HomeFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.base_container, homeFragment)
                .commit();
    }


    @Override
    public void getUserTasks(int userId) {
        tasksFragment = TasksFragment.newInstance(userId);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.base_container, tasksFragment)
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_in_left)
                .addToBackStack(TASKS_FRAGMENT_TAG)
                .commit();
    }

    @Override
    public void onTaskFragmentBackPressed() {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .remove(tasksFragment)
//                .replace(R.id.base_container, homeFragment)
//                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
//                .commit();

        getSupportFragmentManager().popBackStack();
    }
}
