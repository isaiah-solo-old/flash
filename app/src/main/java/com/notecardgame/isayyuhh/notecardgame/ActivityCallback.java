package com.notecardgame.isayyuhh.notecardgame;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by isayyuhh on 2/2/16.
 */
public interface ActivityCallback {
    /** FragmentManager functions*/
    void setFragment(Fragment newFragment);
    void setDialogFragment(DialogFragment newFragment);
    void setToolbarTitle(String title);
    Stack stacksAt(int position);
    void refreshStacks(View view, AdapterView.OnItemClickListener oicl,
                       AdapterView.OnItemLongClickListener oilcl);
    void addStack(String name);
    void deleteStack(String name);
}

