package com.notecardgame.isayyuhh.notecardgame.fragment_dialog;

import android.app.Activity;
import android.support.v4.app.DialogFragment;

import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;

/**
 * Created by isayyuhh on 2/3/16.
 */
public abstract class AddDialogFragment extends DialogFragment {

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
