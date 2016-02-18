package com.notecardgame.isayyuhh.notecardgame.fragment_list;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.adapter.MainMenuListAdapter;
import com.notecardgame.isayyuhh.notecardgame.listener.ListItemClickListener;
import com.notecardgame.isayyuhh.notecardgame.logic.ListLogic;
import com.notecardgame.isayyuhh.notecardgame.logic.MainMenuListLogic;

/**
 * Created by isayyuhh on 2/2/16.
 */
public class MainMenuListFragment extends Fragment {

    /**
     * Fields
     */
    private ActivityCallback mCallback;

    /**
     * On attach fragment to activity
     * @param activity Activity to attach to
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mCallback = (ActivityCallback) activity;
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
        View view = inflater.inflate(R.layout.list_main, container, false);
        this.mCallback.setToolbarTitle(this.mCallback.getStr(R.string.app_name));
        setListView(view);
        return view;
    }

    /**
     * Sets on-click listener and adapter to listview
     * @param view Inflated view
     */
    private void setListView(View view) {
        ListView listView = (ListView) view.findViewById(R.id.lv_main);

        String[] array = this.mCallback.getStrArr(R.array.menu_main);
        ListLogic listLogic = new MainMenuListLogic(mCallback);

        MainMenuListAdapter adp = new MainMenuListAdapter(getActivity());
        listView.setAdapter(adp);
        adp.setData(array);

        listView.setOnItemClickListener(new ListItemClickListener(listLogic));
    }
}
