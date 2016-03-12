package com.notecardgame.isayyuhh.notecardgame.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
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
import com.notecardgame.isayyuhh.notecardgame.object.Paper;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class NotecardListAdapter extends ListAdapter {

    /**
     * Fields
     */
    private String stackName;

    /**
     * Adapter constructor
     * @param context Activity context
     * @param ac Reference to Activity
     */
    public NotecardListAdapter(Context context, ActivityCallback ac, ListView listView) {
        super(context, R.layout.list_item_stack, ac);
    }

    public void setStackName (String stackName) { this.stackName = stackName; }

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
        Notecard notecard = (Notecard) this.getItem(position);

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
    public void remove(Paper notecard) {
        super.remove(notecard);
        this.ac.removeNotecardFromStack((Notecard) notecard, this.stackName);
        this.notifyDataSetChanged();
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
