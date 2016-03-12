package com.notecardgame.isayyuhh.notecardgame.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.fragment_item.NotecardItemFragment;
import com.notecardgame.isayyuhh.notecardgame.object.Notecard;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class NotecardListAdapter extends ArrayAdapter<Notecard>
        implements AbsListView.MultiChoiceModeListener {

    /**
     * Fields
     */
    private SparseBooleanArray mSelectedItemsIds;
    private ActivityCallback ac;
    private String stackName;
    private ListView listView;

    /**
     * Adapter constructor
     * @param context Activity context
     * @param ac Reference to Activity
     */
    public NotecardListAdapter(Context context, ActivityCallback ac, ListView listView) {
        super(context, R.layout.list_item_stack);
        this.ac = ac;
        this.listView = listView;
        this.mSelectedItemsIds = new SparseBooleanArray();
    }

    /**
     * Sets data to adapter
     * @param stack Reference to stack
     */
    public void setData(Stack stack) {
        this.clear();
        for(Notecard notecard: stack.getNotecards()) {
            this.add(notecard);
        }
        this.stackName = stack.getName();
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
            convertView = inflater.inflate(R.layout.list_item_notecard, null);
        }
        Notecard notecard = this.getItem(position);

        TextView notecardFront = (TextView) convertView.findViewById(R.id.notecard_side);
        notecardFront.setText(notecard.getFront());

        TextView notecardHint = (TextView) convertView.findViewById(R.id.notecard_hint);
        notecardHint.setText(this.ac.getStr(R.string.literal_front));

        CardView cv = (CardView) convertView.findViewById(R.id.notecard);
        cv.setCardBackgroundColor(this.ac.getCol(R.color.colorNotecard));

        LinearLayout iv = (LinearLayout) convertView.findViewById(R.id.edit_notecard_icon);
        iv.setOnClickListener(new IconClickListener(notecard.getFront()));
        return convertView;
    }

    /**
     * Removes notecard from stack and listview
     * @param notecard Notecard to remove from stack and listview
     */
    @Override
    public void remove(Notecard notecard) {
        super.remove(notecard);
        this.ac.removeNotecardFromStack(notecard, this.stackName);
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

    /**
     * On item's state changed
     * @param mode Action mode
     * @param position Position of item
     * @param id Id of item
     * @param checked If item is checked
     */
    @Override
    public void onItemCheckedStateChanged(ActionMode mode,
                                          int position, long id, boolean checked) {
        final int checkedCount = listView.getCheckedItemCount();
        mode.setTitle(checkedCount + this.ac.getStr(R.string.multi_amountselected));
        this.selectView(position, !mSelectedItemsIds.get(position));
    }

    /**
     * On action mode item clicked
     * @param mode Action mode
     * @param item Action mode item
     * @return If action mode item clicked
     */
    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                SparseBooleanArray selected = this.getSelectedIds();
                for (int i = (selected.size() - 1); i >= 0; i--) {
                    if (selected.valueAt(i)) {
                        Notecard notecard = this.getItem(selected.keyAt(i));
                        this.remove(notecard);
                    }
                }
                mode.finish();
                return true;
            default:
                return false;
        }
    }

    /**
     * On action mode created
     * @param mode Action mode
     * @param menu Action mode menu
     * @return If action mode is created
     */
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.menu_select, menu);
        return true;
    }

    /**
     * On action mode destroyed
     * @param mode Action mode
     */
    @Override
    public void onDestroyActionMode(ActionMode mode) {
        this.mSelectedItemsIds = new SparseBooleanArray();
        this.notifyDataSetChanged();
    }

    /**
     * On action mode prepared
     * @param mode Action mode
     * @param menu Action mode menu
     * @return If action mode is prepared
     */
    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    /**
     * Icon click listener
     */
    private class IconClickListener implements LinearLayout.OnClickListener {

        /**
         * Fields
         */
        private String notecardFront;

        /**
         * On-click listener constructor
         * @param notecardFront String on front of notecard
         */
        public IconClickListener(String notecardFront) {
            this.notecardFront = notecardFront;
        }

        /**
         * Uses logic on on-item click
         * @param v View
         */
        @Override
        public void onClick(View v) {
            Bundle b = new Bundle();
            b.putString(ac.getStr(R.string.bundle_name), stackName);
            b.putString(ac.getStr(R.string.bundle_json), this.notecardFront);

            NotecardItemFragment newFragment = new NotecardItemFragment();
            newFragment.setArguments(b);
            ac.setItemFragment(newFragment);
        }
    }
}
