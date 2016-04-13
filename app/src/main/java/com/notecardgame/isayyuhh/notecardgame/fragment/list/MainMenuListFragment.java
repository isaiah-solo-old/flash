package com.notecardgame.isayyuhh.notecardgame.fragment.list;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.adapter.BaseListAdapter;
import com.notecardgame.isayyuhh.notecardgame.adapter.MainMenuListAdapter;
import com.notecardgame.isayyuhh.notecardgame.enums.MainMenuEnum;
import com.notecardgame.isayyuhh.notecardgame.fragment.BaseFragment;
import com.notecardgame.isayyuhh.notecardgame.object.Note;
import com.notecardgame.isayyuhh.notecardgame.object.Paper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by isayyuhh on 2/2/16.
 */
public class MainMenuListFragment extends BaseListFragment {

    /**
     * Fields
     */
    private List<Paper> mainMenuArray;
    private List<MainMenuEnum> array = Arrays.asList(MainMenuEnum.values());

    /**
     * On created fragment
     *
     * @param savedInstanceState Reference to the saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] stringArray = this.ac.getStrArr(R.array.menu_main);
        this.mainMenuArray = new ArrayList<>();
        for (String string : stringArray) this.mainMenuArray.add(new Note(string));
    }

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

        MainMenuListAdapter adp = new MainMenuListAdapter(this.getActivity(), ac);
        listView.setAdapter(adp);
        adp.setData(this.mainMenuArray);

        ListItemListener listener = new ListItemListener(adp, listView);
        listView.setOnItemClickListener(listener);
    }

    /**
     * Sets floating action button
     */
    @Override
    protected void setFab() {
    }

    /**
     * On item click
     */
    @Override
    protected void onClick(View view, int position) {
        try {
            Constructor constructor = this.array.get(position).getFragmentClass().getConstructor();
            this.ac.setFragment((BaseFragment) constructor.newInstance());
        } catch (Exception e) {
        }
    }

    /**
     * On multi edit
     *
     * @param selected Array containing selected items
     * @param position Position of element
     * @param adp      Array adapter
     */
    @Override
    protected void onEdit(SparseBooleanArray selected, int position, BaseListAdapter adp) {
    }

    /**
     * On multi swap
     *
     * @param positionOne Position of first element
     * @param positionTwo Position of second element
     * @param adp         Array adapter
     */
    @Override
    protected void onSwap(int positionOne, int positionTwo, BaseListAdapter adp) {
    }

    /**
     * On multi delete
     *
     * @param selected Array containing selected items
     * @param position Position of element
     * @param adp      Array adapter
     */
    @Override
    protected void onDelete(SparseBooleanArray selected, int position, BaseListAdapter adp) {
    }
}
