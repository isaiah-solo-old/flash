package com.notecardgame.isayyuhh.notecardgame.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;

/**
 * Created by isayyuhh on 4/12/16.
 */
public abstract class BaseFragment extends Fragment {

    /**
     * Fields
     */
    protected ActivityCallback ac;

    /**
     * On attach fragment to activity
     *
     * @param activity Activity to attach to
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.ac = (ActivityCallback) activity;
    }
}
