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

    /**
     * Fields
     */
    private SparseBooleanArray mSelectedItemsIds;
    private ActivityCallback mCallback;
    private ListLogic listLogic;

    /**
     * Adapter constructor
     * @param context Activity context
     * @param mCallback Reference to Activity
     */
    public StackListAdapter(Context context, ActivityCallback mCallback) {
        super(context, R.layout.list_item_stack);
        this.mCallback = mCallback;
        this.mSelectedItemsIds = new SparseBooleanArray();
    }

    /**
     * Sets data to adapter
     * @param stacks Reference to stacks
     * @param listLogic Adapter on-click logic
     */
    public void setData(List<Stack> stacks, ListLogic listLogic) {
        this.clear();
        for(Stack stack : stacks) {
            this.add(stack);
        }
        this.listLogic = listLogic;
        this.notifyDataSetChanged();
    }

    /**
     * Sets item view
     * @param position Reference to position
     * @param convertView View to inflate
     * @param parent Reference to ViewGroup
     * @return View to inflate
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_stack, null);
        }
        Stack stack = this.getItem(position);
        TextView stackName = (TextView) convertView.findViewById(R.id.stack_name);
        stackName.setText(stack.getName());
        return convertView;
    }

    /**
     * Removes stack from stacks and listview
     * @param stack Stack to remove from stacks and listview
     */
    @Override
    public void remove(Stack stack) {
        super.remove(stack);
        mCallback.deleteStack(stack.getName());
        this.notifyDataSetChanged();
    }

    /**
     * Toggles selection
     * @param position Position of selection
     */
    public void toggleSelection(int position) {
        this.selectView(position, !mSelectedItemsIds.get(position));
    }

    /**
     * Removes selections
     */
    public void removeSelection() {
        this.mSelectedItemsIds = new SparseBooleanArray();
        this.notifyDataSetChanged();
    }

    /**
     * Selects view from selections
     * @param position Position of selection
     * @param value If selection exists
     */
    public void selectView(int position, boolean value) {
        if (value)
            this.mSelectedItemsIds.put(position, value);
        else
            this.mSelectedItemsIds.delete(position);
        this.notifyDataSetChanged();
    }

    /**
     * Gets selections
     * @return Array of selections
     */
    public SparseBooleanArray getSelectedIds() {
        return this.mSelectedItemsIds;
    }
}
