package com.scx.navigation.designnavgraph;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.scx.navigation.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class TipDialogFragment extends DialogFragment {
    private static final String TAG = "TipDialogFragment";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        TipDialogFragmentArgs args = TipDialogFragmentArgs.fromBundle(requireArguments());
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialog_AppCompat)
                .setTitle("Tip")
                .setMessage("from:" + args.getFrom())
                .setPositiveButton("ok", ((dialog, which) -> {
                    Log.d(TAG, "on click ok");
                }));
        return builder.show();
    }
}
