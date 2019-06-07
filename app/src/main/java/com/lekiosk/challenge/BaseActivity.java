package com.lekiosk.challenge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.lekiosk.challenge.ui.home.HomeFragment;
import com.lekiosk.challenge.ui.tasks.TasksFragment;

public class BaseActivity extends AppCompatActivity implements HomeFragment.OnHomeFragmentInteractionListener,
        TasksFragment.OnTaskFragmentInteraction {

    private HomeFragment homeFragment;
    private TasksFragment tasksFragment;
    private final String HOME_FRAGMENT_TAG = HomeFragment.class.getCanonicalName();
    private final String TASKS_FRAGMENT_TAG = TasksFragment.class.getCanonicalName();
    private boolean mIsTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        mIsTablet = findViewById(R.id.layout_tab) !=null;

        if(mIsTablet){
            initTabletView(); // init the main view with the two panes
        }
        else {
            initView(); // init the view with users list fragment
        }

        if (getCurrentFragment() != null) {
            //if screen rotated retain Fragment
            changeFragmentTo(getCurrentFragment(), getCurrentFragment().getTag());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //the tasks fragment is loaded until the users clicks on a user to see its tasks
    private void initTabletView(){
        homeFragment = HomeFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.base_users_container, homeFragment)
                .commit();
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

        if(!mIsTablet){
            tasksFragment = TasksFragment.newInstance(userId,  false);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.base_container, tasksFragment)
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(TASKS_FRAGMENT_TAG)
                    .commit();
        }
        else {
            tasksFragment = TasksFragment.newInstance(userId,  true);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.base_tasks_container, tasksFragment)
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }
    }

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id
                .base_container);
    }

    //whenever we rotate the screen android will set the fragment to the first one
    //so we have to maintain the current fragment instance to avoid that
    public void changeFragmentTo(Fragment fragmentToLoad, String fragmentTag) {

        if (getSupportFragmentManager().findFragmentByTag(fragmentTag) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.base_container, fragmentToLoad, fragmentTag)
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(fragmentTag)
                    .commit();

        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.base_container, fragmentToLoad, fragmentTag)
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }
    }

    @Override
    public void onTaskFragmentBackPressed() {

        getSupportFragmentManager().popBackStack();
    }
}
