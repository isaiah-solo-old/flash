package com.notecardgame.isayyuhh.notecardgame.fragment_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.adapter.StackListAdapter;
import com.notecardgame.isayyuhh.notecardgame.fragment_dialog.AddStackDialogFragment;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;

/**
 * Created by isayyuhh on 2/2/16.
 */
public class StackListFragment extends ListFragment {

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
        this.view = inflater.inflate(R.layout.list_stack, container, false);
        this.ac.setToolbarTitle(this.ac.getStr(R.string.title_stacks));
        this.setListView();
        this.setFab();

        return this.view;
    }

    /**
     * Gathers data from dialog fragment
     * @param requestCode Request code
     * @param resultCode Result code
     * @param data Activity intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        setListView();
    }

    /**
     * Sets on-click listener and adapter to listview
     */
    @Override
    protected void setListView() {
        ListView listView = (ListView) this.view.findViewById(R.id.lv_stack);

        StackListAdapter adp = new StackListAdapter(getActivity(), ac, listView);
        listView.setAdapter(adp);
        adp.setData(this.ac.getStacks());

        listView.setOnItemClickListener(new ListItemClickListener());
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(adp);
    }

    /**
     * Sets floating action button
     */
    @Override
    protected void setFab () {
        final StackListFragment slf = this;

        FloatingActionButton fab = (FloatingActionButton) this.view.findViewById(R.id.fab_stack);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Starts Dialog
                AddStackDialogFragment fragment = new AddStackDialogFragment();
                fragment.setTargetFragment(slf, MY_REQUEST_CODE);
                ac.setDialogFragment(fragment);
            }
        });
    }

    /**
     * On item click
     */
    @Override
    protected void onClick(View view, int position) {
        Stack stack = ac.stacksAt(position);

        Gson gson = new Gson();
        Bundle b = new Bundle();
        b.putString(ac.getStr(R.string.bundle_json), gson.toJson(stack));

        NotecardListFragment fragment = new NotecardListFragment();
        fragment.setArguments(b);
        ac.setListFragment(fragment);
    }
}
