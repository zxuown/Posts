package com.example.lesson08;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class UserDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.dialog_user, null);

        ((TextView) view.findViewById(R.id.username))
                .setText(MainActivity.currentUser.getUsername());
        ((TextView) view.findViewById(R.id.phone))
                .setText(MainActivity.currentUser.getPhone());
        ((TextView) view.findViewById(R.id.city))
                .setText(MainActivity.currentUser.getAddress().getCity());

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);

        return builder.create();
    }
}
