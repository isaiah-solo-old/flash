package com.notecardgame.isayyuhh.notecardgame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class AddStackDialogFragment extends DialogFragment {
    /**
     * Fields
     */

    /**
     * Constructors
     */
    public AddStackDialogFragment() {
    }

    /**
     * OnCreateView
     */
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_new_stack, null))
                .setTitle(getActivity().getResources().getString(R.string.add_stack))
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText et = (EditText) getDialog().findViewById(R.id.edit_stack_name);

                        try {
                            FileOutputStream fos = getActivity().openFileOutput(
                                    StackMenuFragment.FILENAME, Context.MODE_PRIVATE);
                            fos.write(et.getText().toString().getBytes());
                            fos.close();
                        } catch (IOException ioe) {
                            Log.e("FAIL", "File output failed");
                            return;
                        }
                        getTargetFragment().onActivityResult(getTargetRequestCode(),
                                Activity.RESULT_OK, getActivity().getIntent());
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
}
