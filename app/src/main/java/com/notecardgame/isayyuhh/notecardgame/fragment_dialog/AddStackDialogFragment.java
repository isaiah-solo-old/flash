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
import com.notecardgame.isayyuhh.notecardgame.object.Stack;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class AddStackDialogFragment extends AddDialogFragment {

    /**
     * On created dialog
     * @param savedInstanceState Reference to the saved instance state
     * @return Created dialog
     */
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_new_stack, null))
                .setTitle(getActivity().getResources().getString(R.string.dialog_addstack))
                .setPositiveButton(this.ac.getStr(R.string.dialog_positive),
                        new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText et = (EditText) getDialog().findViewById(R.id.edit_stack_name);
                        String text = et.getText().toString();
                        if (text.trim().length() < 1) {
                            Toast.makeText(getActivity(),
                                    ac.getStr(R.string.dialog_error_missingname),
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if (ac.findStack(text) != null) {
                            Toast.makeText(getActivity(),
                                    ac.getStr(R.string.dialog_error_existstack),
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Stack stack = new Stack(text);
                        ac.addStack(stack);
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
