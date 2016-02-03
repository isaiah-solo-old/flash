package com.notecardgame.isayyuhh.notecardgame;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by isayyuhh on 2/2/16.
 */
public class StackMenuFragment extends Fragment {

    /** Fields */
    private ActivityCallback mCallback;
    private Snackbar sb;

    /** OnAttach */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mCallback = (ActivityCallback) activity;
    }

    /** OnCreateView */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate view, and set Toolbar and ListView
        View view = inflater.inflate(R.layout.stack_menu, container, false);
        this.mCallback.setToolbarTitle("Stacks");
        setListView(view);

        // Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FILENAME = "hello_file";
                String string = "hello world!";

                try {
                    FileOutputStream fos = getActivity().openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    fos.write(string.getBytes());
                    fos.close();
                }
                catch (IOException ioe) {
                    sb = Snackbar.make(view, "Failed to create Notecard", Snackbar.LENGTH_LONG)
                            .setAction("Action", null);
                    sb.show();
                }
                
                if (sb != null) {
                    sb.dismiss();
                }
                else {
                    Snackbar.make(view, "Notecard created", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
        return view;
    }

    /** Attaches Adapter and OnItemClickListener to the ListView */
    private void setListView(View view) {
        ListView listView = (ListView) view.findViewById(R.id.menu_list);
        MenuListListener listListener = new MenuListListener();
        listView.setOnItemClickListener(listListener);
    }

    /** OnItemClickListener */
    private class MenuListListener implements AdapterView.OnItemClickListener {

        /** Fields */
        public MenuListListener() {}

        /** OnItemClick */
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {}
    }
}
