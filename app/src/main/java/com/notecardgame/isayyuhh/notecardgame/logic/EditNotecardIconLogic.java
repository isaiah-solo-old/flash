package com.notecardgame.isayyuhh.notecardgame.logic;

import android.os.Bundle;
import android.view.View;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.fragment_item.NotecardItemFragment;
import com.notecardgame.isayyuhh.notecardgame.fragment_list.StackListFragment;

/**
 * Created by isayyuhh on 2/15/16.
 */
public class EditNotecardIconLogic extends IconLogic{

    /**
     * Fields
     */
    String stackName;
    String notecardFront;

    /**
     * Logic constructor
     * @param mCallback Reference to activity
     */
    public EditNotecardIconLogic (ActivityCallback mCallback, String stackName,
                                  String notecardFront) {
        this.mCallback = mCallback;
        this.stackName = stackName;
        this.notecardFront = notecardFront;
    }

    /**
     * Does logic
     * @param view Item view
     */
    public void doLogic(View view) {
        Bundle b = new Bundle();
        b.putString("stack_name", this.stackName);
        b.putString("notecard_front", this.notecardFront);

        NotecardItemFragment newFragment = new NotecardItemFragment();
        newFragment.setArguments(b);
        this.mCallback.setFragment(newFragment);
    }
}
