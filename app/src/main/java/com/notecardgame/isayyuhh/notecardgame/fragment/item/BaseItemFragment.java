package com.notecardgame.isayyuhh.notecardgame.fragment.item;

import android.view.View;

import com.notecardgame.isayyuhh.notecardgame.fragment.BaseFragment;

/**
 * Created by isayyuhh on 2/17/16.
 */
public abstract class BaseItemFragment extends BaseFragment {

    /**
     * Sets on-click listener and adapter to listview
     *
     * @param view Inflated view
     */
    protected abstract void setItemView(View view);
}
