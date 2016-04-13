package com.notecardgame.isayyuhh.notecardgame.fragment.item;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.object.Notecard;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;

/**
 * Created by isayyuhh on 2/17/16.
 */
public class NotecardItemFragment extends BaseItemFragment {

    /**
     * Fields
     */
    private String currentStackName;
    private String currentNotecardFront;
    private String currentNotecardBack;
    private String newFront;
    private String newBack;

    /**
     * On created fragment
     *
     * @param savedInstanceState Reference to the saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setHasOptionsMenu(true);

        Bundle b = this.getArguments();
        this.currentStackName = b.getString(this.ac.getStr(R.string.bundle_name));
        Stack stack = this.ac.findStack(this.currentStackName);
        this.currentNotecardFront = b.getString(this.ac.getStr(R.string.bundle_json));
        Notecard notecard = stack.find(this.currentNotecardFront);
        this.currentNotecardBack = notecard.getBack();

        this.newFront = this.currentNotecardFront;
        this.newBack = this.currentNotecardBack;
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
        View currentView = inflater.inflate(R.layout.item_notecard, container, false);
        this.ac.setToolbarTitle(this.ac.getStr(R.string.title_notecard));
        setItemView(currentView);

        return currentView;
    }

    /**
     * When options menu is created
     *
     * @param menu     Menu to inflate in
     * @param inflater Inflates menu
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_notecard, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * Handles item events
     *
     * @param item Item to handle
     * @return If option item is selected
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (this.currentNotecardFront.compareTo(this.newFront) != 0 ||
                this.currentNotecardBack.compareTo(this.newBack) != 0) {
            Notecard notecard = this.ac.findNotecardInStack(this.currentStackName,
                    this.currentNotecardFront);
            Notecard newNotecard = new Notecard(this.newFront, this.newBack);

            this.ac.removeNotecardFromStack(notecard, this.currentStackName);
            this.ac.addNotecardToStack(newNotecard, this.currentStackName, 0);
        }
        this.ac.popFragment();
        return true;
    }

    /**
     * Sets on-click listener and adapter to listview
     *
     * @param view Inflated view
     */
    protected void setItemView(View view) {
        final EditText editFront = (EditText) view.findViewById(R.id.notecard_front_text);
        editFront.setText(currentNotecardFront);
        editFront.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newFront = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        EditText editBack = (EditText) view.findViewById(R.id.notecard_back_text);
        editBack.setText(currentNotecardBack);
        editBack.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newBack = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        TextView notecardFrontHint = (TextView) view.findViewById(R.id.notecard_front_hint);
        TextView notecardBackHint = (TextView) view.findViewById(R.id.notecard_back_hint);
        notecardFrontHint.setText(this.ac.getStr(R.string.literal_front));
        notecardBackHint.setText(this.ac.getStr(R.string.literal_back));

        CardView cardViewFront = (CardView) view.findViewById(R.id.notecard_front);
        CardView cardViewBack = (CardView) view.findViewById(R.id.notecard_back);
        cardViewFront.setCardBackgroundColor(this.ac.getCol(R.color.colorNotecard));
        cardViewBack.setCardBackgroundColor(this.ac.getCol(R.color.colorNotecard));
    }
}
