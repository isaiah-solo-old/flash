package com.notecardgame.isayyuhh.notecardgame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

/**
 * Created by isaiah on 6/27/2015.
 */
public class MainActivity extends AppCompatActivity implements ActivityCallback {

    /** Fields */
    private FragmentManager fm;
    private Toolbar mToolbar;

    /** OnCreate */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Sets View
        setContentView(R.layout.activity_main);
        initialize();
    }

    /** Initializes app */
    private void initialize() {
        // Sets Toolbar and title
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));

        // Sets FragmentManager
        this.fm = getSupportFragmentManager();
        MainMenuFragment newFragment = new MainMenuFragment();

        // Begins initial FragmentTransaction
        FragmentTransaction ft = this.fm.beginTransaction();
        ft.replace(R.id.listFragment, newFragment);
        ft.commit();
    }

    /** Sets up the current Fragment */
    @Override
    public void setFragment(Fragment newFragment) {
        // Starts FragmentTransaction
        FragmentTransaction ft = this.fm.beginTransaction();
        ft.replace(R.id.listFragment, newFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    /** Changes current Toolbar title */
    @Override
    public void setToolbarTitle(String title) {
        mToolbar.setTitle(title);
    }
}
