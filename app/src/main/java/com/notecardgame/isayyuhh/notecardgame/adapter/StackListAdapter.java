package com.notecardgame.isayyuhh.notecardgame.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;

import java.util.List;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class StackListAdapter extends ArrayAdapter<Stack> implements AbsListView.MultiChoiceModeListener {

    /**
     * Fields
     */
    private SparseBooleanArray mSelectedItemsIds;
    private ActivityCallback ac;
    private ListView listView;

    /**
     * Adapter constructor
     * @param context Activity context
     * @param ac Reference to Activity
     */
    public StackListAdapter(Context context, ActivityCallback ac, ListView listView) {
        super(context, R.layout.list_item_stack);
        this.mSelectedItemsIds = new SparseBooleanArray();
        this.ac = ac;
        this.listView = listView;
    }

    /**
     * Sets data to adapter
     * @param stacks Reference to stacks
     */
    public void setData(List<Stack> stacks) {
        this.clear();
        for(Stack stack : stacks) {
            this.add(stack);
        }
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
        this.ac.deleteStack(stack.getName());
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

    @Override
    public void onItemCheckedStateChanged(ActionMode mode,
                                          int position, long id, boolean checked) {
        final int checkedCount = listView.getCheckedItemCount();
        mode.setTitle(checkedCount + this.ac.getStr(R.string.multi_amountselected));
        this.toggleSelection(position);
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
                        Stack selecteditem = this.getItem(selected.keyAt(i));
                        this.remove(selecteditem);
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
}
