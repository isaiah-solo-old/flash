package com.notecardgame.isayyuhh.notecardgame.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.logic.ListLogic;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;

import java.util.List;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class StackListAdapter extends ArrayAdapter<Stack> {
    private SparseBooleanArray mSelectedItemsIds;
    private ActivityCallback mCallback;
    private ListLogic listLogic;

    public StackListAdapter(Context context, ActivityCallback mCallback) {
        super(context, R.layout.item_stack);
        this.mCallback = mCallback;
        this.mSelectedItemsIds = new SparseBooleanArray();
    }

    public void setData(List<Stack> stacks, ListLogic listLogic) {
        this.clear();
        for(Stack stack : stacks) {
            this.add(stack);
        }
        this.listLogic = listLogic;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_stack, null);
        }
        Stack stack = this.getItem(position);
        TextView stackName = (TextView) convertView.findViewById(R.id.stack_name);
        stackName.setText(stack.getName());
        return convertView;
    }

    @Override
    public void remove(Stack object) {
        super.remove(object);
        mCallback.deleteStack(object.getName());
        this.notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        this.selectView(position, !mSelectedItemsIds.get(position));
    }

    public void removeSelection() {
        this.mSelectedItemsIds = new SparseBooleanArray();
        this.notifyDataSetChanged();
    }

    public void selectView(int position, boolean value) {
        if (value)
            this.mSelectedItemsIds.put(position, value);
        else
            this.mSelectedItemsIds.delete(position);
        this.notifyDataSetChanged();
    }

    public SparseBooleanArray getSelectedIds() {
        return this.mSelectedItemsIds;
    }
}
