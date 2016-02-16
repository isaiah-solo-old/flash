package com.notecardgame.isayyuhh.notecardgame.logic;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;

/**
 * Created by isayyuhh on 2/15/16.
 */
public class StackListLogic extends ListLogic {
    public StackListLogic (ActivityCallback mCallback) {
        this.mCallback = mCallback;
        this.listArray = mCallback.getStrArr(R.array.main_menu);
    }

    public void onClick (int position) {
        //Log.e("STRING", ((TextView) view.findViewById(R.id.stack_name)).getText().toString());
    }
}
