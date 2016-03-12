package com.notecardgame.isayyuhh.notecardgame.fragment_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.adapter.ListAdapter;
import com.notecardgame.isayyuhh.notecardgame.adapter.StackListAdapter;
import com.notecardgame.isayyuhh.notecardgame.fragment_dialog.AddStackDialogFragment;
import com.notecardgame.isayyuhh.notecardgame.fragment_dialog.EditStackDialogFragment;
import com.notecardgame.isayyuhh.notecardgame.object.Paper;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isayyuhh on 2/2/16.
 */
public class StackListFragment extends ListFragment {

    /**
     * On created view
     *
     * @param inflater           View inflater
     * @param container          Reference to viewgroup
     * @param savedInstanceState Reference to the saved instance state
     * @return Inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.list_stack, container, false);
        this.ac.setToolbarTitle(this.ac.getStr(R.string.title_stacks));
        this.setListView();
        this.setFab();

        return this.view;
    }

    /**
     * Gathers data from dialog fragment
     *
     * @param requestCode Request code
     * @param resultCode  Result code
     * @param data        Activity intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        setListView();
    }

    /**
     * Sets on-click listener and adapter to listview
     */
    @Override
    protected void setListView() {
        ListView listView = (ListView) this.view.findViewById(R.id.lv_stack);

        StackListAdapter adp = new StackListAdapter(getActivity(), this.ac);
        listView.setAdapter(adp);
        List<Paper> stacks = new ArrayList<>();
        for (Stack stack : this.ac.getStacks()) stacks.add(stack);
        adp.setData(stacks);

        ListItemListener listener = new ListItemListener(adp, listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setOnItemClickListener(listener);
        listView.setMultiChoiceModeListener(listener);
    }

    /**
     * Sets floating action button
     */
    @Override
    protected void setFab() {
        final StackListFragment slf = this;

        FloatingActionButton fab = (FloatingActionButton) this.view.findViewById(R.id.fab_stack);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Starts Dialog
                AddStackDialogFragment fragment = new AddStackDialogFragment();
                fragment.setTargetFragment(slf, MY_REQUEST_CODE);
                ac.setDialogFragment(fragment);
            }
        });
    }

    /**
     * On item click
     */
    @Override
    protected void onClick(View view, int position) {
        Stack stack = this.ac.stacksAt(position);

        Gson gson = new Gson();
        Bundle b = new Bundle();
        b.putString(this.ac.getStr(R.string.bundle_json), gson.toJson(stack));

        NotecardListFragment fragment = new NotecardListFragment();
        fragment.setArguments(b);
        this.ac.setListFragment(fragment);
    }

    /**
     * On multi edit
     *
     * @param selected Array containing selected items
     * @param position Position of element
     * @param adp      Array adapter
     */
    @Override
    protected void onEdit(SparseBooleanArray selected, int position, ListAdapter adp) {
        final StackListFragment slf = this;

        String stackName = this.ac.stacksAt(position).getName();

        Bundle b = new Bundle();
        b.putString(this.ac.getStr(R.string.bundle_name), stackName);

        EditStackDialogFragment fragment = new EditStackDialogFragment();
        fragment.setTargetFragment(slf, MY_REQUEST_CODE);
        fragment.setArguments(b);
        this.ac.setDialogFragment(fragment);
    }

    /**
     * On multi swap
     *
     * @param positionOne Position of first element
     * @param positionTwo Position of second element
     * @param adp         Array adapter
     */
    @Override
    protected void onSwap(int positionOne, int positionTwo, ListAdapter adp) {
        this.ac.swapStacks(positionOne, positionTwo);
        List<Paper> stacks = new ArrayList<>();
        for (Stack stack : this.ac.getStacks()) stacks.add(stack);
        adp.setData(stacks);
    }

    /**
     * On multi delete
     *
     * @param selected Array containing selected items
     * @param position Position of element
     * @param adp      Array adapter
     */
    @Override
    protected void onDelete(SparseBooleanArray selected, int position, ListAdapter adp) {
        Stack selecteditem = (Stack) adp.getItem(selected.keyAt(position));
        adp.remove(selecteditem);
    }
}
