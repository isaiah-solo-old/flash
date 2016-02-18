package com.notecardgame.isayyuhh.notecardgame.logic;

import android.util.Log;
import android.view.View;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.fragment_list.StackListFragment;

/**
 * Created by isayyuhh on 2/15/16.
 */
public class MainMenuListLogic extends ListLogic {

    /**
     * Logic constructor
     * @param mCallback Reference to activity
     */
    public MainMenuListLogic (ActivityCallback mCallback) {
        this.mCallback = mCallback;
        this.listArray = mCallback.getStrArr(R.array.menu_main);
    }

    /**
     * Does logic
     * @param position Position of list element
     * @param view Item view
     */
    public void doLogic(int position, View view) {
        switch (position) {
            case 0:
                StackListFragment newFragment = new StackListFragment();
                this.mCallback.setFragment(newFragment);
                break;
            case 1:
                Log.e("PASS", listArray[position]);
                break;
            case 2:
                Log.e("PASS", listArray[position]);
                break;
            default:
                Log.e("MAIN MENU ERROR", "invalid listview position");
        }
    }
}
