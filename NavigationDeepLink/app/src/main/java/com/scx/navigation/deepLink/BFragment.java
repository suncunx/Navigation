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
import androidx.navigation.NavDeepLinkRequest;
import androidx.navigation.fragment.NavHostFragment;

public class BFragment extends Fragment {
    private static final String TAG = "BFragment";

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
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        view.findViewById(R.id.tv_b_2).setOnClickListener(v -> {
//            Uri.parse("scx://example.com")
//            NavDeepLinkRequest request = new NavDeepLinkRequest(Uri.parse("scx://example.com"), null, null);
//            NavDeepLinkRequest request = new NavDeepLinkRequest(null, "android.intent.action.MY_ACTION", null);
//            NavDeepLinkRequest request = new NavDeepLinkRequest(null, null, "type/video1");
//            Intent intent = new Intent();
//            intent.setAction("android.intent.action.VIEW");
//            intent.setData(Uri.parse("scx://example.com"));
//            NavDeepLinkRequest request = new NavDeepLinkRequest(intent);
//            NavHostFragment.findNavController(BFragment.this).navigate(Uri.parse("scx://example.com"));
//            NavHostFragment.findNavController(BFragment.this).navigate(R.id.nav_graph_nested);
//            NavHostFragment.findNavController(BFragment.this).navigate("cFragment");
//            NavHostFragment.findNavController(BFragment.this).navigate("dFragment");
//        });

//        BFragmentArgs args = BFragmentArgs.fromBundle(requireArguments());
//        Log.d(TAG, "onViewCreated: id = " + args.getId() + ", name = " + args.getName() + ", mdn = " + args.getMdn());
    }
}
