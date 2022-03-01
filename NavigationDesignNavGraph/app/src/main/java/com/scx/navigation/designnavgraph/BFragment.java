package com.scx.navigation.designnavgraph;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scx.navigation.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class BFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.tv_b_1).setOnClickListener(v -> NavHostFragment.findNavController(BFragment.this).navigate(BFragmentDirections.actionGlobalTipDialogFragment("B")));
        view.findViewById(R.id.tv_b_2).setOnClickListener(v -> NavHostFragment.findNavController(BFragment.this).navigate(BFragmentDirections.actionBFragmentToNestedNavGraph()));
    }
}
