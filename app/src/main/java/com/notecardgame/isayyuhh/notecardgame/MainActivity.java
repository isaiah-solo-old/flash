package com.notecardgame.isayyuhh.notecardgame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by isaiah on 6/27/2015.
 */
public class MainActivity extends AppCompatActivity implements ActivityCallback{
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // sets views
        setContentView(R.layout.activity_main);
        initialize();
    }

    /** Initializes app */
    private void initialize() {
        this.fm = getSupportFragmentManager();
        MainActivityFragment newFragment = new MainActivityFragment();
        setFragment(newFragment);
    }

    /** Sets up the current Fragment */
    @Override
    public void setFragment(Fragment newFragment) {
        // Initial Fragment
        FragmentTransaction ft = this.fm.beginTransaction();
        ft.replace(R.id.listFragment, newFragment);
        ft.commit();
    }
}
