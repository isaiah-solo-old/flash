package com.notecardgame.isayyuhh.notecardgame.fragment_item;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.listener.ItemClickListener;
import com.notecardgame.isayyuhh.notecardgame.logic.NotecardItemLogic;
import com.notecardgame.isayyuhh.notecardgame.object.Notecard;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;

/**
 * Created by isayyuhh on 2/17/16.
 */
public class NotecardItemFragment extends Fragment {

    /**
     * Fields
     */
    private ActivityCallback mCallback;
    String stackName;
    Notecard notecard;
    EditText notecardFront;
    EditText notecardBack;

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
     * On created fragment
     * @param savedInstanceState Reference to the saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = this.getArguments();
        this.stackName = b.getString("stack_name");
        String notecardFront = b.getString("notecard_front");
        Stack stack = this.mCallback.findStack(this.stackName);
        this.notecard = stack.find(notecardFront);
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
        View currentView = inflater.inflate(R.layout.item_notecard, container, false);
        this.mCallback.setToolbarTitle(this.mCallback.getStr(R.string.title_notecard));
        setItemView(currentView);
        return currentView;
    }

    /**
     * Sets on-click listener and adapter to listview
     * @param view Inflated view
     */
    private void setItemView(View view) {
        this.notecardFront = (EditText) view.findViewById(R.id.notecard_front_text);
        this.notecardFront.setText(notecard.getFront());
        this.notecardFront.addTextChangedListener(new TextWatcher() {
            private String previous;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                this.previous = notecard.getFront();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Stack stack = mCallback.findStack(stackName);
                int position = stack.getNotecardPosition(notecard.getFront());
                stack.removeNotecard(previous);
                notecard.setFront(s.toString());
                stack.addNotecard(position, notecard);

                mCallback.update();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        this.notecardBack = (EditText) view.findViewById(R.id.notecard_back_text);
        this.notecardBack.setText(notecard.getBack());
        this.notecardBack.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Stack stack = mCallback.findStack(stackName);
                int position = stack.getNotecardPosition(notecard.getFront());
                stack.removeNotecard(notecard.getFront());
                notecard.setBack(s.toString());
                stack.addNotecard(position, notecard);

                mCallback.update();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        TextView notecardFrontHint = (TextView) view.findViewById(R.id.notecard_front_hint);
        notecardFrontHint.setText(this.mCallback.getStr(R.string.literal_front));
        TextView notecardBackHint = (TextView) view.findViewById(R.id.notecard_back_hint);
        notecardBackHint.setText(this.mCallback.getStr(R.string.literal_back));

        CardView cardViewFront = (CardView) view.findViewById(R.id.notecard_front);
        cardViewFront.setCardBackgroundColor(this.mCallback.getCol(R.color.colorNotecard));
        CardView cardViewBack = (CardView) view.findViewById(R.id.notecard_back);
        cardViewBack.setCardBackgroundColor(this.mCallback.getCol(R.color.colorNotecard));

        //NotecardItemLogic itemLogic = new NotecardItemLogic(this.mCallback, notecard);
        //this.cardView.setOnClickListener(new ItemClickListener(itemLogic));

        //CardView cv = (CardView) view.findViewById(R.id.edit_notecard);
        //cv.setCardBackgroundColor(this.mCallback.getCol(R.color.colorPrimary));
    }
}
