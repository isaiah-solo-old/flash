package com.notecardgame.isayyuhh.notecardgame.fragment_list;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.adapter.ListAdapter;
import com.notecardgame.isayyuhh.notecardgame.adapter.MainMenuListAdapter;
import com.notecardgame.isayyuhh.notecardgame.adapter.StackListAdapter;
import com.notecardgame.isayyuhh.notecardgame.object.Note;
import com.notecardgame.isayyuhh.notecardgame.object.Paper;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isayyuhh on 2/2/16.
 */
public class MainMenuListFragment extends ListFragment {

    /**
     * Fields
     */
    private List<Paper> mainMenuArray;

    /**
     * On created fragment
     * @param savedInstanceState Reference to the saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] stringArray = this.ac.getStrArr(R.array.menu_main);
        this.mainMenuArray = new ArrayList<>();
        for (String string: stringArray) this.mainMenuArray.add(new Note(string));
    }

    /**
     * On created view
     * @param inflater View inflater
     * @param container Reference to viewgroup
     * @param savedInstanceState Reference to the saved instance state
     * @return Inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate view, and set Toolbar and ListView
        this.view = inflater.inflate(R.layout.list_main, container, false);
        this.ac.setToolbarTitle(this.ac.getStr(R.string.app_name));
        this.setListView();

        return this.view;
    }

    /**
     * Sets on-click listener and adapter to listview
     */
    @Override
    protected void setListView() {
        ListView listView = (ListView) this.view.findViewById(R.id.lv_main);

        MainMenuListAdapter adp = new MainMenuListAdapter(this.getActivity());
        listView.setAdapter(adp);
        adp.setData(this.mainMenuArray);

        ListItemClickListener listener = new ListItemClickListener(adp, listView);
        listView.setOnItemClickListener(listener);
    }

    /**
     * Sets floating action button
     */
    @Override
    protected void setFab() {}

    /**
     * On item click
     */
    @Override
    protected void onClick(View view, int position) {
        switch (position) {
            case 0:
                this.ac.setListFragment(new StackListFragment());
                break;
            case 1:
                Log.e("PASS", ((Note) this.mainMenuArray.get(position)).value);
                break;
            case 2:
                Log.e("PASS", ((Note) this.mainMenuArray.get(position)).value);
                break;
            default:
                Log.e("MAIN MENU ERROR", "invalid listview position");
        }
    }

    @Override
    protected void onSwap(int positionOne, int positionTwo, ListAdapter adp) {
    }

    @Override
    protected void onDelete(SparseBooleanArray selected, int position, ListAdapter adp) {
    }
}
