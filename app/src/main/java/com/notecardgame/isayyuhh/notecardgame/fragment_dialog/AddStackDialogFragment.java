package com.notecardgame.isayyuhh.notecardgame.fragment_dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class AddStackDialogFragment extends DialogFragment {

    /**
     * Fields
     */
    private ActivityCallback mCallback;

    /**
     * Default Constructor
     */
    public AddStackDialogFragment() {}

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
     * On created dialog
     * @param savedInstanceState Reference to the saved instance state
     * @return Created dialog
     */
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_new_stack, null))
                .setTitle(getActivity().getResources().getString(R.string.literal_addstack))
                .setPositiveButton(this.mCallback.getStr(R.string.literal_positive),
                        new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText et = (EditText) getDialog().findViewById(R.id.edit_stack_name);
                        String text = et.getText().toString();
                        Stack stack = new Stack(text);
                        mCallback.addStack(stack);
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
