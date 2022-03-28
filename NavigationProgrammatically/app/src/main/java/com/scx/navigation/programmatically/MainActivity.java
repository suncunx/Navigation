package com.scx.navigation.programmatically;

import android.os.Bundle;
import android.util.Log;

import com.scx.navigation.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: this = " + this + ", taskId = " + getTaskId());
        setContentView(R.layout.activity_main);

//        createNavHostFragment();

//        setGraphDynamically();

    }

    // 创建NavHostFragment
    private void createNavHostFragment() {
        // 通过NavHostFragment.create()方法创建NavHostFragment
        NavHostFragment finalHost = NavHostFragment.create(R.navigation.nav_graph);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, finalHost)
                .setPrimaryNavigationFragment(finalHost) // equivalent to app:defaultNavHost="true"
                .commit();
    }

    // 动态设置导航图
    private void setGraphDynamically() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        // 第一步：inflate导航图
        NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.nav_graph);
        // 第二步：设置startDestination	会覆盖在XML中指定的startDestination
        navGraph.setStartDestination(R.id.bFragment);
        // 第三步：通过NavController设置导航图       必须最后再调用本方法
        navController.setGraph(navGraph);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: this = " + this + ", taskId = " + getTaskId());
    }
}