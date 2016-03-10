package com.notecardgame.isayyuhh.notecardgame.fragment_list;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;

import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;

/**
 * Created by isayyuhh on 2/2/16.
 */
public abstract class ListFragment extends Fragment {

    /**
     * Fields
     */
    protected final static int MY_REQUEST_CODE = 1;
    protected ActivityCallback ac;
    protected View view;

    /**
     * On attach fragment to activity
     * @param activity Activity to attach to
     */
    @Override
    public void onAttach (Activity activity) {
        super.onAttach(activity);
        this.ac = (ActivityCallback) activity;
    }

    /**
     * Sets on-click listener and adapter to listview
     */
    protected abstract void setListView ();

    /**
     * Sets floating action button
     */
    protected abstract void setFab ();

    /**
     * On item click
     */
    protected abstract void onClick (View view, int position);

    /**
     * On item click listener
     */
    protected class ListItemClickListener implements AdapterView.OnItemClickListener {

        /**
         * Uses logic on on-item click
         * @param parent Parent view
         * @param view View of item
         * @param position Position of item
         * @param id Id of item
         */
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            onClick(view, position);
        }
    }
}
