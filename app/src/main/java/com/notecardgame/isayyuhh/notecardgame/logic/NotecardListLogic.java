package com.notecardgame.isayyuhh.notecardgame.logic;

import android.util.Log;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;

/**
 * Created by isayyuhh on 2/15/16.
 */
public class NotecardListLogic extends ListLogic {
    public NotecardListLogic(ActivityCallback mCallback) {
        this.mCallback = mCallback;
        this.listArray = mCallback.getStrArr(R.array.menu_main);
    }

    @Override
    public void onClick(int position) {

    }
}
