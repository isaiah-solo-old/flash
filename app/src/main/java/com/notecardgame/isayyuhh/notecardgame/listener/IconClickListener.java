package com.notecardgame.isayyuhh.notecardgame.listener;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.notecardgame.isayyuhh.notecardgame.logic.IconLogic;

/**
 * Created by isayyuhh on 2/17/16.
 */
public class IconClickListener implements LinearLayout.OnClickListener {

    /**
     * Fields
     */
    private IconLogic iconLogic;

    /**
     * On-click listener constructor
     * @param iconLogic On-click logic
     */
    public IconClickListener(IconLogic iconLogic) {
        this.iconLogic = iconLogic;
    }

    /**
     * Uses logic on on-item click
     * @param v View
     */
    @Override
    public void onClick(View v) {
        this.iconLogic.doLogic(v);
    }
}
