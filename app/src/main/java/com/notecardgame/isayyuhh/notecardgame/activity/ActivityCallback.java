package com.notecardgame.isayyuhh.notecardgame.activity;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.View;

import com.notecardgame.isayyuhh.notecardgame.object.Stack;

/**
 * Created by isayyuhh on 2/2/16.
 */
public interface ActivityCallback {
    /** FragmentManager functions*/
    String getStr(int id);
    String[] getStrArr(int id);
    void setFragment(Fragment newFragment);
    void setDialogFragment(DialogFragment newFragment);
    void setToolbarTitle(String title);
    Stack stacksAt(int position);
    void refreshStacksList(View view);
    void addStack(String name);
    void deleteStack(String name);
}

