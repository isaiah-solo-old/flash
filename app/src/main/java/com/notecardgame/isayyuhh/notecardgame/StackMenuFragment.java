package com.notecardgame.isayyuhh.notecardgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

/**
 * Created by isayyuhh on 2/2/16.
 */
public class StackMenuFragment extends Fragment {

    /**
     * Fields
     */
    private final static int MY_REQUEST_CODE = 1;
    private StackMenuFragment smf = this;
    private ActivityCallback mCallback;
    private View currentView;

    /**
     * OnAttach
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mCallback = (ActivityCallback) activity;
    }

    /**
     * OnCreateView
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate view, and set Toolbar and ListView
        this.currentView = inflater.inflate(R.layout.stack_menu, container, false);
        this.mCallback.setToolbarTitle("Stacks");
        setListView(currentView);

        // FloatingActionButton
        FloatingActionButton fab = (FloatingActionButton) currentView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Starts Dialog
                AddStackDialogFragment newFragment = new AddStackDialogFragment();
                newFragment.setTargetFragment(smf, MY_REQUEST_CODE);
                mCallback.setDialogFragment(newFragment);
            }
        });
        return this.currentView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        setListView(this.currentView);
    }

    /**
     * Attaches Adapter and OnItemClickListener to the ListView
     */
    private void setListView(View view) {
        this.mCallback.refreshStacks(view, new MenuListListener(), new MenuListLongListener());
    }

    /**
     * OnItemClickListener
     */
    private class MenuListListener implements AdapterView.OnItemClickListener {
        /**
         * Fields
         */
        public MenuListListener() {}

        /**
         * OnItemClick
         */
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.e("STRING", ((TextView) view.findViewById(R.id.stack_name)).getText().toString());
        }
    }

    /**
     * OnItemLongClickListener
     */
    private class MenuListLongListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            /*
            mCallback.deleteStack(mCallback.stacksAt(position).getStackName());
            mCallback.refreshStacks(currentView, new MenuListListener(),
                    new MenuListLongListener());
            */
            return true;
        }
    }
}
