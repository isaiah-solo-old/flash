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
import com.notecardgame.isayyuhh.notecardgame.object.Notecard;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class NotecardListAdapter extends ArrayAdapter<Notecard> {
    private SparseBooleanArray mSelectedItemsIds;
    private ActivityCallback mCallback;
    private Stack stack;

    public NotecardListAdapter(Context context, ActivityCallback mCallback) {
        super(context, R.layout.item_stack);
        this.mCallback = mCallback;
        this.mSelectedItemsIds = new SparseBooleanArray();
    }

    public void setData(Stack stack) {
        this.clear();
        for(int i = 0; i < stack.size(); i++) {
            this.add(stack.at(i));
        }
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_notecard, null);
        }
        Notecard notecard = this.getItem(position);
        TextView notecardFront = (TextView) convertView.findViewById(R.id.notecard_front);
        notecardFront.setText(notecard.getFront());
        return convertView;
    }

    @Override
    public void remove(Notecard object) {
        super.remove(object);
        this.stack.removeNotecard(object);
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

    public int getSelectedCount() {
        return this.mSelectedItemsIds.size();
    }

    public SparseBooleanArray getSelectedIds() {
        return this.mSelectedItemsIds;
    }
}
