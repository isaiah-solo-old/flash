package com.notecardgame.isayyuhh.notecardgame.logic;

import android.os.Bundle;

import com.google.gson.Gson;
import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.fragment.NotecardListFragment;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;

/**
 * Created by isayyuhh on 2/15/16.
 */
public class StackListLogic extends ListLogic {
    public StackListLogic (ActivityCallback mCallback) {
        this.mCallback = mCallback;
        this.listArray = mCallback.getStrArr(R.array.main_menu);
    }

    public void onClick (int position) {
        Stack stack = this.mCallback.stacksAt(position);
        Bundle b = new Bundle();
        Gson gson = new Gson();
        b.putString("json", gson.toJson(stack));

        NotecardListFragment newFragment = new NotecardListFragment();
        newFragment.setArguments(b);
        this.mCallback.setFragment(newFragment);
    }
}
