package com.notecardgame.isayyuhh.notecardgame.fragment_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.adapter.NotecardListAdapter;
import com.notecardgame.isayyuhh.notecardgame.fragment_dialog.AddNotecardDialogFragment;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;

/**
 * Created by isayyuhh on 2/2/16.
 */
public class NotecardListFragment extends ListFragment {

    /**
     * Fields
     */
    private Stack stack;

    /**
     * On created fragment
     * @param savedInstanceState Reference to the saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Gson gson = new Gson();
        Bundle b = this.getArguments();
        String json = b.getString(this.ac.getStr(R.string.bundle_json));

        this.stack = gson.fromJson(json, Stack.class);
    }

    /**
     * On created view
     * @param inflater View inflater
     * @param container Reference to viewgroup
     * @param savedInstanceState Reference to the saved instance state
     * @return Inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.list_notecard, container, false);
        this.ac.setToolbarTitle(this.stack.getName() + this.ac.getStr(R.string.title_notecards));
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
        this.setListView();
    }

    /**
     * Sets on-click listener and adapter to listview
     */
    @Override
    protected void setListView() {
        ListView listView = (ListView) this.view.findViewById(R.id.lv_notecard);

        this.stack = this.ac.findStack(this.stack.getName());

        NotecardListAdapter adp = new NotecardListAdapter(this.getActivity(), this.ac, listView);
        listView.setAdapter(adp);
        adp.setData(this.stack);

        listView.setOnItemClickListener(new ListItemClickListener());
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(adp);
    }

    /**
     * Sets floating action button
     */
    @Override
    protected void setFab () {
        final NotecardListFragment nlf = this;

        FloatingActionButton fab = (FloatingActionButton) this.view.findViewById(R.id.fab_notecard);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Starts Dialog
                AddNotecardDialogFragment newFragment = new AddNotecardDialogFragment();

                Bundle b = new Bundle();
                b.putString("name", stack.getName());
                newFragment.setArguments(b);

                newFragment.setTargetFragment(nlf, MY_REQUEST_CODE);
                ac.setDialogFragment(newFragment);
            }
        });
    }

    /**
     * On item click
     */
    @Override
    protected void onClick(View view, int position) {
        TextView tv = (TextView) view.findViewById(R.id.notecard_side);
        TextView tvHint = (TextView) view.findViewById(R.id.notecard_hint);
        String text = tv.getText().toString();
        String back = this.stack.at(position).getBack();

        if (back.trim().length() < 1) return;
        else if (text.compareTo(back) == 0) {
            tv.setText(this.stack.at(position).getFront());
            tvHint.setText(this.ac.getStr(R.string.literal_front));
        }
        else {
            tv.setText(this.stack.at(position).getBack());
            tvHint.setText(this.ac.getStr(R.string.literal_back));
        }
    }
}
