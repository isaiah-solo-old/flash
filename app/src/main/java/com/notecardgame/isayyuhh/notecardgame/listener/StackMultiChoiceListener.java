package com.notecardgame.isayyuhh.notecardgame.listener;

import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;
import com.notecardgame.isayyuhh.notecardgame.adapter.StackListAdapter;

/**
 * Created by isayyuhh on 2/15/16.
 */
public class StackMultiChoiceListener implements AbsListView.MultiChoiceModeListener {

    /**
     * Fields
     */
    private ActivityCallback mCallback;
    private ListView listView;
    private StackListAdapter stackListAdapter;

    /**
     * Multi-choice listener constructor
     * @param mCallback Reference to activity
     * @param listView Listview to listen to
     * @param stackListAdapter Reference to list adapter
     */
    public StackMultiChoiceListener(ActivityCallback mCallback,
                                    ListView listView, StackListAdapter stackListAdapter) {
        this.mCallback = mCallback;
        this.listView = listView;
        this.stackListAdapter = stackListAdapter;
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
        stackListAdapter.toggleSelection(position);
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
                SparseBooleanArray selected = stackListAdapter.getSelectedIds();
                for (int i = (selected.size() - 1); i >= 0; i--) {
                    if (selected.valueAt(i)) {
                        Stack selecteditem = stackListAdapter.getItem(selected.keyAt(i));
                        stackListAdapter.remove(selecteditem);
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
        stackListAdapter.removeSelection();
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
