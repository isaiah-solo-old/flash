package com.notecardgame.isayyuhh.notecardgame.logic;

import android.view.View;

import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.object.Notecard;

/**
 * Created by isayyuhh on 2/15/16.
 */
public abstract class ListLogic {

    /**
     * Fields
     */
    protected ActivityCallback mCallback;
    protected Notecard notecard;
    protected String[] listArray;

    /**
     * Sets reference of the name of the stack
     * @param name Reference to name of stack
     */
    public void setStackName (String name) {}

    /**
     * Does logic
     * @param position Position of list element
     * @param view Item view
     */
    public abstract void doLogic(int position, View view);
}
