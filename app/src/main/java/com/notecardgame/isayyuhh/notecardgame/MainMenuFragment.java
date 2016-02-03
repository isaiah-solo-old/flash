package com.notecardgame.isayyuhh.notecardgame;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by isayyuhh on 2/2/16.
 */
public class MainMenuFragment extends Fragment {

    /** Fields */
    private ActivityCallback mCallback;

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
        View view = inflater.inflate(R.layout.main_menu, container, false);
        this.mCallback.setToolbarTitle("NotecardGame");
        setListView(view);
        return view;
    }

    /** Attaches OnItemClickListener to the ListView */
    private void setListView(View view) {
        ListView listView = (ListView) view.findViewById(R.id.menu_list);
        String[] menu_array = getResources().getStringArray(R.array.main_menu);
        MenuListListener listListener = new MenuListListener(mCallback, menu_array);
        listView.setOnItemClickListener(listListener);
    }


    /** OnItemClickListener */
    private class MenuListListener implements AdapterView.OnItemClickListener {

        /** Fields */
        String[] menu_array;
        ActivityCallback mCallback;

        /** Constructors */
        public MenuListListener(ActivityCallback mCallback, String[] menu_array) {
            this.menu_array = menu_array;
            this.mCallback = mCallback;
        }

        /** OnItemClick */
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    StackMenuFragment newFragment = new StackMenuFragment();
                    this.mCallback.setFragment(newFragment);
                    break;
                case 1:
                    Log.e("PASS", menu_array[position]);
                    break;
                default:
                    Log.e("MAIN MENU ERROR", "invalid listview position");
            }
        }
    }
}
