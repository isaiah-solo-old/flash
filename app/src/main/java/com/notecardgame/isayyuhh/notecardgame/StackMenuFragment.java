package com.notecardgame.isayyuhh.notecardgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by isayyuhh on 2/2/16.
 */
public class StackMenuFragment extends Fragment {
    private final static int MY_REQUEST_CODE = 1;
    private StackMenuFragment smf = this;
    private View view;

    /**
     * Fields
     */
    public final static String FILENAME = "hello_file";

    private ActivityCallback mCallback;
    private Snackbar sb;

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
        this.view = inflater.inflate(R.layout.stack_menu, container, false);
        this.mCallback.setToolbarTitle("Stacks");
        setListView(view);

        // Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Starts Dialog
                AddStackDialogFragment newFragment = new AddStackDialogFragment();
                newFragment.setTargetFragment(smf, MY_REQUEST_CODE);
                mCallback.setDialogFragment(newFragment);
            }
        });
        return this.view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        setListView(this.view);
    }

    /**
     * Attaches Adapter and OnItemClickListener to the ListView
     */
    private void setListView(View view) {
        StringBuilder fileInput = new StringBuilder();
        try {
            FileInputStream fis = getActivity().openFileInput(FILENAME);
            for (int i = fis.read(); i != -1; i = fis.read()) {
                fileInput.append((char) i);
            }
            fis.close();
        } catch (IOException ioe) {
            Log.e("FAIL", "File input failed");
            return;
        }
        String stackName = fileInput.toString();

        Stack newStack = new Stack(stackName);
        List<Stack> stacks = new ArrayList<>();
        stacks.add(newStack);

        ListView listView = (ListView) view.findViewById(R.id.menu_list);
        StackMenuAdapter adp = new StackMenuAdapter(getActivity());
        listView.setAdapter(adp);
        adp.setData(stacks);
        MenuListListener listListener = new MenuListListener();
        listView.setOnItemClickListener(listListener);
    }

    /**
     * OnItemClickListener
     */
    private class MenuListListener implements AdapterView.OnItemClickListener {

        /**
         * Fields
         */
        public MenuListListener() {
        }

        /**
         * OnItemClick
         */
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.e("STRING", ((TextView) view.findViewById(R.id.stack_name)).getText().toString());
        }
    }
}
