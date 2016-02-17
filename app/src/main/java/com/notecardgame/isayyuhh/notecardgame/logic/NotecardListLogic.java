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
    private ActivityCallback mCallback;
    private boolean back;
    private String stackName;

    public NotecardListLogic(ActivityCallback mCallback) {
        this.mCallback = mCallback;
        this.listArray = mCallback.getStrArr(R.array.menu_main);
        this.back = false;
    }

    @Override
    public void setStackName (String name) {
        this.stackName = name;
    }

    @Override
    public void onClick(int position, View view) {
        TextView tv = (TextView) view.findViewById(R.id.notecard_side);
        String text = tv.getText().toString();
        Stack stack = this.mCallback.findStack(stackName);
        String back = stack.at(position).getBack();
        if (text.compareTo(back) == 0) {
            tv.setText(stack.at(position).getFront());
            this.back = false;
        } else {
            tv.setText(stack.at(position).getBack());
            this.back = true;
        }
    }
}
