package com.notecardgame.isayyuhh.notecardgame.fragment_list;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.adapter.ListAdapter;

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
     *
     * @param activity Activity to attach to
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.ac = (ActivityCallback) activity;
    }

    /**
     * Sets on-click listener and adapter to list view
     */
    protected abstract void setListView();

    /**
     * Sets floating action button
     */
    protected abstract void setFab();

    /**
     * On item click
     */
    protected abstract void onClick(View view, int position);

    /**
     * On multi edit
     *
     * @param selected Array containing selected items
     * @param position Position of element
     * @param adp      Array adapter
     */
    protected abstract void onEdit(SparseBooleanArray selected, int position, ListAdapter adp);

    /**
     * On multi swap
     *
     * @param positionOne Position of first element
     * @param positionTwo Position of second element
     * @param adp         Array adapter
     */
    protected abstract void onSwap(int positionOne, int positionTwo, ListAdapter adp);

    /**
     * On multi delete
     *
     * @param selected Array containing selected items
     * @param position Position of element
     * @param adp      Array adapter
     */
    protected abstract void onDelete(SparseBooleanArray selected, int position, ListAdapter adp);

    /**
     * On item click listener
     */
    protected class ListItemListener implements AdapterView.OnItemClickListener,
            AbsListView.MultiChoiceModeListener {

        /**
         * Fields
         */
        private ListAdapter adp;
        private ListView listView;
        private SparseBooleanArray mSelectedItemsIds;

        /**
         * Constructor
         */
        public ListItemListener(ListAdapter adp, ListView listView) {
            this.adp = adp;
            this.listView = listView;
            this.mSelectedItemsIds = new SparseBooleanArray();
        }

        /**
         * Uses logic on on-item click
         *
         * @param parent   Parent view
         * @param view     View of item
         * @param position Position of item
         * @param id       Id of item
         */
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            onClick(view, position);
        }

        /**
         * On item's state changed
         *
         * @param mode     Action mode
         * @param position Position of item
         * @param id       Id of item
         * @param checked  If item is checked
         */
        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id,
                                              boolean checked) {
            final int checkedCount = listView.getCheckedItemCount();

            if (checkedCount == 1) mode.getMenu().findItem(R.id.edit).setVisible(true);
            else mode.getMenu().findItem(R.id.edit).setVisible(false);
            if (checkedCount == 2) mode.getMenu().findItem(R.id.swap).setVisible(true);
            else mode.getMenu().findItem(R.id.swap).setVisible(false);
            ac.updateMenu();

            mode.setTitle(checkedCount + ac.getStr(R.string.multi_amountselected));

            this.selectView(position, !mSelectedItemsIds.get(position));
        }

        /**
         * On action mode created
         *
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
         * On action mode prepared
         *
         * @param mode Action mode
         * @param menu Action mode menu
         * @return If action mode is prepared
         */
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        /**
         * On action mode item clicked
         *
         * @param mode Action mode
         * @param item Action mode item
         * @return If action mode item clicked
         */
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            SparseBooleanArray selected = this.mSelectedItemsIds;
            switch (item.getItemId()) {
                case R.id.edit:
                    for (int i = (selected.size() - 1); i >= 0; i--) {
                        if (selected.valueAt(i)) {
                            onEdit(selected, i, this.adp);
                        }
                    }
                    mode.finish();
                    return true;
                case R.id.swap:
                    int positionOne = -1, positionTwo = -1;
                    for (int i = (selected.size() - 1); i >= 0; i--) {
                        if (selected.valueAt(i)) {
                            if (positionOne == -1) positionOne = selected.keyAt(i);
                            else if (positionTwo == -1) positionTwo = selected.keyAt(i);
                        }
                    }
                    if (positionOne != -1 && positionTwo != -1) {
                        onSwap(positionOne, positionTwo, this.adp);
                    }
                    mode.finish();
                    return true;
                case R.id.delete:
                    for (int i = (selected.size() - 1); i >= 0; i--) {
                        if (selected.valueAt(i)) {
                            onDelete(selected, i, this.adp);
                        }
                    }
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        /**
         * On action mode destroyed
         *
         * @param mode Action mode
         */
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            this.mSelectedItemsIds = new SparseBooleanArray();
            this.adp.notifyDataSetChanged();
        }

        /**
         * Selects view from selections
         *
         * @param position Position of selection
         * @param value    If selection exists
         */
        public void selectView(int position, boolean value) {
            if (value) this.mSelectedItemsIds.put(position, value);
            else this.mSelectedItemsIds.delete(position);
            this.adp.notifyDataSetChanged();
        }
    }
}
