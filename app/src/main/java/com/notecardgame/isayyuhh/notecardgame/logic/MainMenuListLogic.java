package com.notecardgame.isayyuhh.notecardgame.logic;

import android.util.Log;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.fragment.list.StackListFragment;

/**
 * Created by isayyuhh on 2/15/16.
 */
public class MainMenuListLogic extends ListLogic {
    public MainMenuListLogic (ActivityCallback mCallback) {
        this.mCallback = mCallback;
        this.listArray = mCallback.getStrArr(R.array.main_menu);
    }

    public void onClick (int position) {
        switch (position) {
            case 0:
                StackListFragment newFragment = new StackListFragment();
                this.mCallback.setFragment(newFragment);
                break;
            case 1:
                Log.e("PASS", listArray[position]);
                break;
            default:
                Log.e("MAIN MENU ERROR", "invalid listview position");
        }
    }
}
