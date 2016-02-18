package com.notecardgame.isayyuhh.notecardgame.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.logic.ListLogic;
import com.notecardgame.isayyuhh.notecardgame.object.Notecard;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class NotecardListAdapter extends ArrayAdapter<Notecard> {

    /**
     * Fields
     */
    private SparseBooleanArray mSelectedItemsIds;
    private ActivityCallback mCallback;
    private String stackName;
    private ListLogic listLogic;

    /**
     * Adapter constructor
     * @param context Activity context
     * @param mCallback Reference to Activity
     */
    public NotecardListAdapter(Context context, ActivityCallback mCallback) {
        super(context, R.layout.item_stack);
        this.mCallback = mCallback;
        this.mSelectedItemsIds = new SparseBooleanArray();
    }

    /**
     * Sets data to adapter
     * @param stack Reference to stack
     * @param listLogic Adapter on-click logic
     */
    public void setData(Stack stack, ListLogic listLogic) {
        this.clear();
        for(Notecard notecard: stack.getNotecards()) {
            this.add(notecard);
        }
        this.stackName = stack.getName();
        this.listLogic = listLogic;
        this.listLogic.setStackName(this.stackName);
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
            convertView = inflater.inflate(R.layout.item_notecard, null);
        }
        Notecard notecard = this.getItem(position);

        TextView notecardFront = (TextView) convertView.findViewById(R.id.notecard_side);
        notecardFront.setText(notecard.getFront());
        return convertView;
    }

    /**
     * Removes notecard from stack and listview
     * @param notecard Notecard to remove from stack and listview
     */
    @Override
    public void remove(Notecard notecard) {
        super.remove(notecard);
        this.mCallback.removeNotecardFromStack(notecard, this.stackName);
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
        if (value) this.mSelectedItemsIds.put(position, value);
        else this.mSelectedItemsIds.delete(position);
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
