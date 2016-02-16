package com.notecardgame.isayyuhh.notecardgame.logic;

import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;

/**
 * Created by isayyuhh on 2/15/16.
 */
public abstract class ListLogic {
    protected ActivityCallback mCallback;
    protected String[] listArray;

    public abstract void onClick (int position);
}
