package com.notecardgame.isayyuhh.notecardgame.listener;

import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.adapter.NotecardListAdapter;
import com.notecardgame.isayyuhh.notecardgame.object.Notecard;

/**
 * Created by isayyuhh on 2/15/16.
 */
public class NotecardMultiChoiceListener implements AbsListView.MultiChoiceModeListener {

    /**
     * Fields
     */
    private ActivityCallback mCallback;
    private ListView listView;
    private NotecardListAdapter notecardListAdapter;

    /**
     * Multi-choice listener constructor
     * @param mCallback Reference to activity
     * @param listView Listview to listen to
     * @param notecardListAdapter Reference to list adapter
     */
    public NotecardMultiChoiceListener(ActivityCallback mCallback, ListView listView,
                                       NotecardListAdapter notecardListAdapter) {
        this.mCallback = mCallback;
        this.listView = listView;
        this.notecardListAdapter = notecardListAdapter;
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
        mode.setTitle(checkedCount + this.mCallback.getStr(R.string.literal_amountselected));
        notecardListAdapter.toggleSelection(position);
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
                SparseBooleanArray selected = notecardListAdapter.getSelectedIds();
                for (int i = (selected.size() - 1); i >= 0; i--) {
                    if (selected.valueAt(i)) {
                        Notecard notecard = notecardListAdapter.getItem(selected.keyAt(i));
                        notecardListAdapter.remove(notecard);
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
        // TODO Auto-generated method stub
        notecardListAdapter.removeSelection();
    }

    /**
     * On action mode prepared
     * @param mode Action mode
     * @param menu Action mode menu
     * @return If action mode is prepared
     */
    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        // TODO Auto-generated method stub
        return false;
    }
}
