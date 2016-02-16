package com.notecardgame.isayyuhh.notecardgame.listener;

import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.adapter.NotecardListAdapter;
import com.notecardgame.isayyuhh.notecardgame.object.Notecard;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;
import com.notecardgame.isayyuhh.notecardgame.adapter.StackListAdapter;

/**
 * Created by isayyuhh on 2/15/16.
 */
public class StackMultiChoiceListener implements AbsListView.MultiChoiceModeListener {
    private ListView listView;
    private StackListAdapter stackListAdapter;

    public StackMultiChoiceListener(ListView listView, StackListAdapter stackListAdapter) {
        this.listView = listView;
        this.stackListAdapter = stackListAdapter;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode,
                                          int position, long id, boolean checked) {
        // Capture total checked items
        final int checkedCount = listView.getCheckedItemCount();
        // Set the CAB title according to total checked items
        mode.setTitle(checkedCount + " Selected");
        // Calls toggleSelection method from ListViewAdapter Class
        stackListAdapter.toggleSelection(position);
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                // Calls getSelectedIds method from ListViewAdapter Class
                SparseBooleanArray selected = stackListAdapter.getSelectedIds();
                // Captures all selected ids with a loop
                for (int i = (selected.size() - 1); i >= 0; i--) {
                    if (selected.valueAt(i)) {
                        Stack selecteditem = stackListAdapter.getItem(selected.keyAt(i));

                        // Remove selected items following the ids
                        stackListAdapter.remove(selecteditem);
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
        stackListAdapter.removeSelection();
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        // TODO Auto-generated method stub
        return false;
    }
}
