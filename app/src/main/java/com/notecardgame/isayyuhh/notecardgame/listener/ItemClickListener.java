package com.notecardgame.isayyuhh.notecardgame.listener;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;

import com.notecardgame.isayyuhh.notecardgame.logic.ItemLogic;
import com.notecardgame.isayyuhh.notecardgame.logic.ListLogic;

/**
 * Created by isayyuhh on 2/15/16.
 */
public class ItemClickListener implements CardView.OnClickListener {

    /**
     * Fields
     */
    private ItemLogic itemLogic;

    /**
     * On-click listener constructor
     * @param itemLogic On-click logic
     */
    public ItemClickListener(ItemLogic itemLogic) {
        this.itemLogic = itemLogic;
    }

    /**
     * Uses logic on on-item click
     * @param v View
     */
    @Override
    public void onClick(View v) {
        this.itemLogic.doLogic(v);
    }
}
