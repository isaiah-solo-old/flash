package com.notecardgame.isayyuhh.notecardgame.fragment_dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.object.Notecard;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class AddNotecardDialogFragment extends DialogFragment {

    /**
     * Fields
     */
    private ActivityCallback mCallback;
    private String stackName;

    /**
     * Default Constructor
     */
    public AddNotecardDialogFragment() {}

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
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getArguments();
        this.stackName = b.getString("name");
    }

    /**
     * On created dialog
     * @param savedInstanceState Reference to the saved instance state
     * @return Created dialog
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_new_notecard, null))
                .setTitle(getActivity().getResources().getString(R.string.literal_addnotecard))
                .setPositiveButton(this.mCallback.getStr(R.string.literal_positive),
                        new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText etf = (EditText) getDialog().findViewById(R.id.edit_notecard_front);
                        EditText etb = (EditText) getDialog().findViewById(R.id.edit_notecard_back);
                        String front = etf.getText().toString();
                        String back = etb.getText().toString();
                        if (front.trim().length() < 1) {
                            Toast.makeText(getActivity(), "Please enter front of notecard",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if (mCallback.findStack(stackName).find(front) != null) {
                            Toast.makeText(getActivity(), "Notecard already exists",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Notecard notecard = new Notecard(front, back);
                        mCallback.addNotecardToStack(notecard, stackName);
                        getTargetFragment().onActivityResult(getTargetRequestCode(),
                                Activity.RESULT_OK, getActivity().getIntent());
                        dialog.dismiss();
                    }
                        })
                .setNegativeButton(this.mCallback.getStr(R.string.literal_negative),
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
        return builder.create();
    }
}
