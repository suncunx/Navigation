package com.scx.navigation.programmatically;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.scx.navigation.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.fragment.NavHostFragment;

public class TipDialogFragment extends DialogFragment {
    private static final String TAG = "TipDialogFragment";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialog_AppCompat)
                .setTitle("Tip")
                .setMessage("I am TipDialogFragment")
                .setPositiveButton("ok", ((dialog, which) -> {
                    Log.d(TAG, "on click ok");
                    NavHostFragment.findNavController(TipDialogFragment.this)
                            .getPreviousBackStackEntry()
                            .getSavedStateHandle()
                            .getLiveData("keyDialog")
                            .setValue("from TipDialogFragment");
                }));
        return builder.show();
    }

}
