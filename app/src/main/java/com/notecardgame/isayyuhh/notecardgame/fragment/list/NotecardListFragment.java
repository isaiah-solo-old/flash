package com.notecardgame.isayyuhh.notecardgame.fragment.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.adapter.NotecardListAdapter;
import com.notecardgame.isayyuhh.notecardgame.fragment.dialog.AddNotecardDialogFragment;
import com.notecardgame.isayyuhh.notecardgame.listener.ItemClickListener;
import com.notecardgame.isayyuhh.notecardgame.listener.NotecardMultiChoiceListener;
import com.notecardgame.isayyuhh.notecardgame.logic.ListLogic;
import com.notecardgame.isayyuhh.notecardgame.logic.NotecardListLogic;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;

/**
 * Created by isayyuhh on 2/2/16.
 */
public class NotecardListFragment extends Fragment {

    /**
     * Fields
     */
    private final static int MY_REQUEST_CODE = 1;
    private Stack stack;
    private NotecardListFragment nlf = this;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = this.getArguments();
        Gson gson = new Gson();
        String json = b.getString("json");

        this.stack = gson.fromJson(json, Stack.class);
    }

    /**
     * OnCreateView
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate view, and set Toolbar and ListView
        this.currentView = inflater.inflate(R.layout.list_notecard, container, false);
        this.mCallback.setToolbarTitle(this.stack.getName() + " Notecards");
        setListView(currentView);

        // FloatingActionButton
        FloatingActionButton fab = (FloatingActionButton) currentView.findViewById(
                R.id.fab_notecard);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Starts Dialog
                AddNotecardDialogFragment newFragment = new AddNotecardDialogFragment();

                Bundle b = new Bundle();
                b.putString("name", stack.getName());
                newFragment.setArguments(b);

                newFragment.setTargetFragment(nlf, MY_REQUEST_CODE);
                mCallback.setDialogFragment(newFragment);
            }
        });
        return this.currentView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.setListView(this.currentView);
    }

    /**
     * Attaches Adapter and OnItemClickListener to the ListView
     */
    private void setListView(View view) {
        // Listview
        ListView listView = (ListView) view.findViewById(R.id.lv_notecard);

        // Variables
        this.stack = this.mCallback.findStack(this.stack.getName());
        ListLogic listLogic = new NotecardListLogic(mCallback);

        // Adapter
        NotecardListAdapter adp = new NotecardListAdapter(getActivity(), this.mCallback);
        listView.setAdapter(adp);
        adp.setData(this.stack, listLogic);

        // Set listview
        listView.setOnItemClickListener(new ItemClickListener(listLogic));
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new NotecardMultiChoiceListener(listView, adp));
    }
}
