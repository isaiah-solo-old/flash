package com.notecardgame.isayyuhh.notecardgame;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class AddStackDialogFragment extends DialogFragment {

    public AddStackDialogFragment () {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_new_stack, container);
        getDialog().setTitle("Legend");
        setList(view);
        return view;
    }

    private void setList(View view) {
    }
}
