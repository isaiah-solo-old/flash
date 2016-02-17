package com.notecardgame.isayyuhh.notecardgame.activity;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.notecardgame.isayyuhh.notecardgame.object.Notecard;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;

import java.util.List;

/**
 * Created by isayyuhh on 2/2/16.
 */
public interface ActivityCallback {
    /** Activity functions */
    Activity activity ();

    /** FragmentManager functions*/
    void setFragment (Fragment newFragment);
    void setDialogFragment (DialogFragment newFragment);
    void setToolbarTitle (String title);

    /** Resource functions */
    String getStr (int id);
    String[] getStrArr (int id);

    /** Stack functions */
    void addStack (Stack stack);
    void deleteStack (String name);
    Stack findStack (String name);
    Stack stacksAt (int position);
    List<Stack> getStacks ();
    void update ();

    /** Notecard functions */
    void addNotecardToStack (Notecard notecard, String name);
    void removeNotecardFromStack (Notecard notecard, String name);
    String getNotecardBack (String front);
    String getNotecardFront (String back);
}

