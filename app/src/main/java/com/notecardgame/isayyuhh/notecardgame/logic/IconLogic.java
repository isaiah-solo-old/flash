package com.notecardgame.isayyuhh.notecardgame.logic;

import android.view.View;

import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.object.Notecard;

/**
 * Created by isayyuhh on 2/15/16.
 */
public abstract class IconLogic {

    /**
     * Fields
     */
    protected ActivityCallback mCallback;

    /**
     * Does logic
     * @param view Item view
     */
    public abstract void doLogic(View view);
}
