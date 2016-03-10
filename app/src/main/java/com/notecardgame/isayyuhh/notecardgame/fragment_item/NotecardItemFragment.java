package com.notecardgame.isayyuhh.notecardgame.fragment_item;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
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
public class NotecardItemFragment extends ItemFragment {

    /**
     * Fields
     */
    private Stack stack;
    private Notecard notecard;

    /**
     * On created fragment
     *
     * @param savedInstanceState Reference to the saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = this.getArguments();
        this.stack = this.ac.findStack(b.getString(this.ac.getStr(R.string.bundle_name)));
        this.notecard = this.stack.find(b.getString(this.ac.getStr(R.string.bundle_json)));
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
     * Sets on-click listener and adapter to listview
     *
     * @param view Inflated view
     */
    protected void setItemView(View view) {
        EditText notecardFront = (EditText) view.findViewById(R.id.notecard_front_text);
        notecardFront.setText(notecard.getFront());
        notecardFront.addTextChangedListener(new TextWatcher() {
            private String previous;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                this.previous = notecard.getFront();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int position = stack.getNotecardPosition(notecard.getFront());
                stack.removeNotecard(previous);
                notecard.setFront(s.toString());
                stack.addNotecard(position, notecard);

                ac.update();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        EditText notecardBack = (EditText) view.findViewById(R.id.notecard_back_text);
        notecardBack.setText(notecard.getBack());
        notecardBack.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int position = stack.getNotecardPosition(notecard.getFront());
                stack.removeNotecard(notecard.getFront());
                notecard.setBack(s.toString());
                stack.addNotecard(position, notecard);

                ac.update();
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
