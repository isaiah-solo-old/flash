package com.notecardgame.isayyuhh.notecardgame.fragment_dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.object.Notecard;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class AddNotecardDialogFragment extends AddDialogFragment {

    /**
     * Fields
     */
    private String stackName;

    /**
     * On created fragment
     *
     * @param savedInstanceState Reference to the saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getArguments();
        this.stackName = b.getString("name");
    }

    /**
     * On created dialog
     *
     * @param savedInstanceState Reference to the saved instance state
     * @return Created dialog
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_new_notecard, null))
                .setTitle(getActivity().getResources().getString(R.string.dialog_addnotecard))
                .setPositiveButton(this.ac.getStr(R.string.dialog_positive),
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                EditText etf = (EditText) getDialog().findViewById(R.id.edit_notecard_front);
                                EditText etb = (EditText) getDialog().findViewById(R.id.edit_notecard_back);
                                String front = etf.getText().toString();
                                String back = etb.getText().toString();
                                if (front.trim().length() < 1) {
                                    Toast.makeText(getActivity(),
                                            ac.getStr(R.string.dialog_error_missingfront),
                                            Toast.LENGTH_SHORT).show();
                                    return;
                                } else if (ac.findStack(stackName).find(front) != null) {
                                    Toast.makeText(getActivity(),
                                            ac.getStr(R.string.dialog_error_existnotecard),
                                            Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                Notecard notecard = new Notecard(front, back);
                                ac.addNotecardToStack(notecard, stackName);
                                getTargetFragment().onActivityResult(getTargetRequestCode(),
                                        Activity.RESULT_OK, getActivity().getIntent());
                                dialog.dismiss();
                            }
                        })
                .setNegativeButton(this.ac.getStr(R.string.dialog_negative),
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
        return builder.create();
    }
}
