package com.example.weatherapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

public class AlertDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.error_tile)
        .setMessage(R.string.error_message)
        .setPositiveButton(R.string.error_button_ok_text, null); // null closes the dialog. But an on click listener could be added to run a function

        return builder.create();
    }
}
