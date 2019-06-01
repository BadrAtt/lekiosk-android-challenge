package com.lekiosk.challenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lekiosk.challenge.ui.home.HomeFragment;

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
    public void onHomeFragmentInteraction() {

    }
}
