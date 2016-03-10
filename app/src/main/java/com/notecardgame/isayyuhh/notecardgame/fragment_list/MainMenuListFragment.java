package com.notecardgame.isayyuhh.notecardgame.fragment_list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.adapter.MainMenuListAdapter;

/**
 * Created by isayyuhh on 2/2/16.
 */
public class MainMenuListFragment extends ListFragment {

    /**
     * Fields
     */
    private String[] mainMenuArray;

    /**
     * On created fragment
     * @param savedInstanceState Reference to the saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mainMenuArray = this.ac.getStrArr(R.array.menu_main);
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

        listView.setOnItemClickListener(new ListItemClickListener());
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
                Log.e("PASS", mainMenuArray[position]);
                break;
            case 2:
                Log.e("PASS", mainMenuArray[position]);
                break;
            default:
                Log.e("MAIN MENU ERROR", "invalid listview position");
        }
    }
}
