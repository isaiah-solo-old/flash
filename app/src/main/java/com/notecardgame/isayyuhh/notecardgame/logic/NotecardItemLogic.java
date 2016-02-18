package com.notecardgame.isayyuhh.notecardgame.logic;

import android.view.View;
import android.widget.TextView;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.object.Notecard;

/**
 * Created by isayyuhh on 2/15/16.
 */
public class NotecardItemLogic extends ItemLogic {

    /**
     * Fields
     */
    protected ActivityCallback mCallback;
    private Notecard notecard;

    /**
     * Logic constructor
     * @param mCallback Reference to activity
     */
    public NotecardItemLogic (ActivityCallback mCallback, Notecard notecard) {
        this.mCallback = mCallback;
        this.notecard = notecard;
    }

    /**
     * Does logic
     * @param view Item view
     */
    public void doLogic(View view) {
        TextView tv = (TextView) view.findViewById(R.id.notecard_side);
        String text = tv.getText().toString();
        if (text.compareTo(notecard.getBack()) == 0) tv.setText(notecard.getFront());
        else tv.setText(notecard.getBack());
    }
}
