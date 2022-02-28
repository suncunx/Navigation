package com.scx.navigation.basicuse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scx.navigation.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class AFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.tv_a).setOnClickListener(v -> {
            // 获取NavController
            NavController navController = NavHostFragment.findNavController(AFragment.this);
            /*
            × 通过NavController+NavDirections执行导航操作
            * 通过AFragmentDirections.actionAFragmentToBFragment()获取NavDirections对象
            */
            navController.navigate(AFragmentDirections.actionAFragmentToBFragment("A").setShowFromWho(true));

//            AFragmentDirections.ActionAFragmentToBFragment action = AFragmentDirections.actionAFragmentToBFragment("A");
//            action.setShowFromWho(true);
//            navController.navigate(action);


//            Bundle bundle = new BFragmentArgs.Builder("A")
//                    .setShowFromWho(true)
//                    .build()
//                    .toBundle();
//            // 通过目的地id导航，通过bundle传递参数
//            navController.navigate(R.id.bFragment, bundle);


//            // 通过action id导航，通过bundle传递参数
//            navController.navigate(R.id.action_aFragment_to_bFragment, bundle);
        });
    }
}
