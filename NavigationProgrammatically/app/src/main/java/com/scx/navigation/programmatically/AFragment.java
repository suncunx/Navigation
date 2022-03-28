package com.scx.navigation.programmatically;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scx.navigation.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;

public class AFragment extends Fragment {
    private static final String TAG = "AFragment";


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
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        observeFullFragmentResult();

        observeDialogResult();

        view.findViewById(R.id.tv_a_1).setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(R.id.bFragment));
        view.findViewById(R.id.tv_a_2).setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(R.id.tipDialogFragment));
    }

    // 监听普通Fragment返回数据
    private void observeFullFragmentResult() {
        NavController navController = NavHostFragment.findNavController(this);
        // 获取当前NavBackStackEntry的SavedStateHandle的LiveData
        MutableLiveData<String> liveData = navController.getCurrentBackStackEntry()
                .getSavedStateHandle()
                .getLiveData("keyFullFragment");
        liveData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String result) {
                // 处理监听到的数据
                Toast.makeText(getContext(), "result: " + result, Toast.LENGTH_SHORT).show();
                // 清除LiveData
                navController.getCurrentBackStackEntry().getSavedStateHandle().remove("keyFullFragment");
            }
        });
    }

    // 监听DialogFragment返回数据
    private void observeDialogResult() {
        NavController navController = NavHostFragment.findNavController(this);
        // 通过目的地id获取NavBackStackEntry。如果返回栈有两个相同id的目的地，那么返回最上面的
        // 因为configuration change之后getCurrentBackStackEntry()方法获取的是DialogFragment的NavBackStackEntry。
        final NavBackStackEntry navBackStackEntry = navController.getBackStackEntry(R.id.aFragment);

        // 创建Observer在NavBackStackEntry's lifecycle ON_RESUME时获取返回结果，这里没有用LiveData
        final LifecycleEventObserver observer = new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                if (event.equals(Lifecycle.Event.ON_RESUME)
                        && navBackStackEntry.getSavedStateHandle().contains("keyDialog")) {
                    String result = navBackStackEntry.getSavedStateHandle().get("keyDialog");
                    // 处理监听到的数据
                    Toast.makeText(getContext(), "result: " + result, Toast.LENGTH_SHORT).show();
                    // 清除LiveData
                    navController.getCurrentBackStackEntry().getSavedStateHandle().remove("keyDialog");
                }
            }
        };
        // 当前View Lifecycle处于STARTED状态，如果使用DialogFragment设置LiveData的数据会导致前目的地立马更新界面，而不是等待DialogFragment弹出再更新界面
        navBackStackEntry.getLifecycle().addObserver(observer);

        // 在view destroy后removeObserver
        getViewLifecycleOwner().getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                if (event.equals(Lifecycle.Event.ON_DESTROY)) {
                    navBackStackEntry.getLifecycle().removeObserver(observer);
                }
            }
        });
    }

}
