package com.notecardgame.isayyuhh.notecardgame.fragment.list;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.listener.ItemClickListener;
import com.notecardgame.isayyuhh.notecardgame.logic.MainMenuListLogic;

/**
 * Created by isayyuhh on 2/2/16.
 */
public class MainMenuListFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.list_main, container, false);
        this.mCallback.setToolbarTitle(this.mCallback.getStr(R.string.app_name));
        setListView(view);
        return view;
    }

    /** Attaches OnItemClickListener to the ListView */
    private void setListView(View view) {
        ListView listView = (ListView) view.findViewById(R.id.lv_main);
        ItemClickListener listListener = new ItemClickListener(mCallback,
                new MainMenuListLogic(mCallback));
        listView.setOnItemClickListener(listListener);
    }
}
