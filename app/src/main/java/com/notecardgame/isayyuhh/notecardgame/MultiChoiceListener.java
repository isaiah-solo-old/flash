package com.notecardgame.isayyuhh.notecardgame;

import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by isayyuhh on 2/15/16.
 */
public class MultiChoiceListener implements AbsListView.MultiChoiceModeListener {
    ListView listView;
    StackMenuAdapter stackMenuAdapter;

    public MultiChoiceListener (ListView listView, StackMenuAdapter stackMenuAdapter) {
        this.listView = listView;
        this.stackMenuAdapter = stackMenuAdapter;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode,
                                          int position, long id, boolean checked) {
        // Capture total checked items
        final int checkedCount = listView.getCheckedItemCount();
        // Set the CAB title according to total checked items
        mode.setTitle(checkedCount + " Selected");
        // Calls toggleSelection method from ListViewAdapter Class
        stackMenuAdapter.toggleSelection(position);
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                // Calls getSelectedIds method from ListViewAdapter Class
                SparseBooleanArray selected = stackMenuAdapter
                        .getSelectedIds();
                // Captures all selected ids with a loop
                for (int i = (selected.size() - 1); i >= 0; i--) {
                    if (selected.valueAt(i)) {
                        Stack selecteditem = stackMenuAdapter
                                .getItem(selected.keyAt(i));
                        // Remove selected items following the ids
                        stackMenuAdapter.remove(selecteditem);
                    }
                }
                // Close CAB
                mode.finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.menu_select, menu);
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        // TODO Auto-generated method stub
        stackMenuAdapter.removeSelection();
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        // TODO Auto-generated method stub
        return false;
    }
}
