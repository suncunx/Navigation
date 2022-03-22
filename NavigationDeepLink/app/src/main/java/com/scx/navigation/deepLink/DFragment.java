package com.scx.navigation.deepLink;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scx.navigation.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DFragment extends Fragment {
    private static final String TAG = "DFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + this + ", arguments = " + getArguments());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_d, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DFragmentArgs args = DFragmentArgs.fromBundle(requireArguments());
        Log.d(TAG, "onViewCreated: id = " + args.getId() + ", name = " + args.getName() + ", phone = " + args.getPhone() + ", time = " + args.getTime());
//        Log.d(TAG, "onViewCreated: id = " + args.getId() + ", name = " + args.getName());
    }
}
