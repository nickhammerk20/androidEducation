package com.kalashnyk.denys.task_4_courses_from_vadim.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.kalashnyk.denys.task_4_courses_from_vadim.R;

/**
 * Created by User on 27.10.2016.
 */
public class DialogMail extends DialogFragment implements DialogInterface.OnClickListener {
    @Override
    public void onClick(DialogInterface dialogInterface, int which) {
        switch (which)
        {
            case Dialog.BUTTON_POSITIVE:
                break;
            case Dialog.BUTTON_NEGATIVE:
                dismiss();
                break;
            case Dialog.BUTTON_NEUTRAL:
                break;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setCancelable(false);
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setTitle("Email!").setPositiveButton(R.string.yes, this)
                .setNegativeButton(R.string.no, this)
                .setMessage(R.string.message_text_mail);
        return adb.create();
    }
}
