package com.notecardgame.isayyuhh.notecardgame.listener;

import android.view.View;
import android.widget.AdapterView;

import com.notecardgame.isayyuhh.notecardgame.logic.ListLogic;

/**
 * Created by isayyuhh on 2/15/16.
 */
public class ItemClickListener implements AdapterView.OnItemClickListener {

    /** Fields */
    private ListLogic listLogic;

    /** Constructors */
    public ItemClickListener(ListLogic listLogic) {
        this.listLogic = listLogic;
    }

    /** OnItemClick */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.listLogic.onClick(position, view);
    }
}
