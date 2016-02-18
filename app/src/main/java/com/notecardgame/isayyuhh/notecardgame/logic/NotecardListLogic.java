package com.notecardgame.isayyuhh.notecardgame.logic;

import android.view.View;
import android.widget.TextView;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;

/**
 * Created by isayyuhh on 2/15/16.
 */
public class NotecardListLogic extends ListLogic {

    /**
     * Fields
     */
    private String stackName;

    /**
     * Logic constructor
     * @param mCallback Reference to activity
     */
    public NotecardListLogic(ActivityCallback mCallback) {
        this.mCallback = mCallback;
        this.listArray = mCallback.getStrArr(R.array.menu_main);
    }

    /**
     * Sets reference of the name of the stack
     * @param name Reference to name of stack
     */
    @Override
    public void setStackName (String name) {
        this.stackName = name;
    }

    /**
     * Does logic
     * @param position Position of list element
     * @param view Item view
     */
    @Override
    public void doLogic(int position, View view) {
        TextView tv = (TextView) view.findViewById(R.id.notecard_side);
        TextView tvHint = (TextView) view.findViewById(R.id.notecard_hint);
        String text = tv.getText().toString();
        Stack stack = this.mCallback.findStack(stackName);
        String back = stack.at(position).getBack();
        if (back.trim().length() < 1) return;
        else if (text.compareTo(back) == 0) {
            tv.setText(stack.at(position).getFront());
            tvHint.setText(this.mCallback.getStr(R.string.literal_front));
        }
        else {
            tv.setText(stack.at(position).getBack());
            tvHint.setText(this.mCallback.getStr(R.string.literal_back));
        }
    }
}
