package com.notecardgame.isayyuhh.notecardgame.listener;

import android.view.View;
import android.widget.AdapterView;

import com.notecardgame.isayyuhh.notecardgame.logic.ListLogic;

/**
 * Created by isayyuhh on 2/15/16.
 */
public class ItemClickListener implements AdapterView.OnItemClickListener {

    /**
     * Fields
     */
    private ListLogic listLogic;

    /**
     * On-click listener constructor
     * @param listLogic On-click logic
     */
    public ItemClickListener(ListLogic listLogic) {
        this.listLogic = listLogic;
    }

    /**
     * Uses logic on on-item click
     * @param parent Parent view
     * @param view View of item
     * @param position Position of item
     * @param id Id of item
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.listLogic.doLogic(position, view);
    }
}
