package com.scx.navigation.basicuse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scx.navigation.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 获取接收参数类对象，获取方法是固定的
        BFragmentArgs args = BFragmentArgs.fromBundle(requireArguments());
        // 获取fromWho参数
        String fromWho = args.getFromWho();
        // 获取showFromWho参数
        boolean showFromWho = args.getShowFromWho();
        TextView textView = view.findViewById(R.id.tv_from_who);
        // 如果showFromWho参数为true则显示fromWho
        if (showFromWho) {
            textView.setText("from:" + fromWho);
        }
    }
}
