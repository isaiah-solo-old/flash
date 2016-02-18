package com.notecardgame.isayyuhh.notecardgame.fragment_list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.adapter.StackListAdapter;
import com.notecardgame.isayyuhh.notecardgame.fragment_dialog.AddStackDialogFragment;
import com.notecardgame.isayyuhh.notecardgame.listener.ItemClickListener;
import com.notecardgame.isayyuhh.notecardgame.listener.StackMultiChoiceListener;
import com.notecardgame.isayyuhh.notecardgame.logic.ListLogic;
import com.notecardgame.isayyuhh.notecardgame.logic.StackListLogic;

/**
 * Created by isayyuhh on 2/2/16.
 */
public class StackListFragment extends Fragment {

    /**
     * Fields
     */
    private final static int MY_REQUEST_CODE = 1;
    private StackListFragment slf = this;
    private ActivityCallback mCallback;
    private View currentView;

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
        this.currentView = inflater.inflate(R.layout.list_stack, container, false);
        this.mCallback.setToolbarTitle(this.mCallback.getStr(R.string.title_stacks));
        setListView(currentView);

        // FloatingActionButton
        FloatingActionButton fab = (FloatingActionButton) currentView.findViewById(R.id.fab_stack);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Starts Dialog
                AddStackDialogFragment newFragment = new AddStackDialogFragment();
                newFragment.setTargetFragment(slf, MY_REQUEST_CODE);
                mCallback.setDialogFragment(newFragment);
            }
        });
        return this.currentView;
    }

    /**
     * Gathers data from dialog fragment
     * @param requestCode Request code
     * @param resultCode Result code
     * @param data Activity intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        setListView(this.currentView);
    }

    /**
     * Sets on-click listener and adapter to listview
     * @param view Inflated view
     */
    private void setListView(View view) {
        ListView listView = (ListView) view.findViewById(R.id.lv_stack);

        ListLogic listLogic = new StackListLogic(mCallback);

        StackListAdapter adp = new StackListAdapter(getActivity(), mCallback);
        listView.setAdapter(adp);
        adp.setData(this.mCallback.getStacks(), listLogic);

        listView.setOnItemClickListener(new ItemClickListener(listLogic));
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new StackMultiChoiceListener(this.mCallback,
                listView, adp));
    }
}
