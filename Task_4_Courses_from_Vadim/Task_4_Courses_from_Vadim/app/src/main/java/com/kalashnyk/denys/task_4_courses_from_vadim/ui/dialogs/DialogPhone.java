package com.kalashnyk.denys.task_4_courses_from_vadim.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.kalashnyk.denys.task_4_courses_from_vadim.R;
import com.kalashnyk.denys.task_4_courses_from_vadim.model.Person;

/**
 * Created by User on 27.10.2016.
 */
public class DialogPhone extends DialogFragment implements DialogInterface.OnClickListener {

    @Override
    public void onClick(DialogInterface dialogInterface, int which) {
        Person person = new Person();
        switch (which)
        {
            case Dialog.BUTTON_POSITIVE:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: "+ person.getmPhoneNumber()));
                getContext().startActivity(intent);
                Log.d("Phone Number", "onClick");
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
                .setTitle("Phone!").setPositiveButton(R.string.yes, this)
                .setNegativeButton(R.string.no, this)
                .setMessage(R.string.message_text_phone);
        return adb.create();
    }
}
