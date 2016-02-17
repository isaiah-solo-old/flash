package com.notecardgame.isayyuhh.notecardgame.logic;

import android.view.View;

import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.object.Notecard;

/**
 * Created by isayyuhh on 2/15/16.
 */
public abstract class ListLogic {
    protected ActivityCallback mCallback;
    protected Notecard notecard;
    protected String[] listArray;

    public void setStackName (String name) {}
    public abstract void onClick (int position, View view);
}
