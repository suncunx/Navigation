package com.scx.navigation.deepLink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.scx.navigation.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Log.d(TAG, "onCreate: this = " + this + ", taskId = " + getTaskId() + ", flag = " + intent.getFlags() + ", intent = " + intent);
        // 可以在这里修改Flag
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 直接通过NavHostFragment获取NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: this = " + this + ", taskId = " + getTaskId() + ", intent = " + intent);
        // 对于singleTask/singleTop启动模式，可以在这里修改Flag
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        navController.handleDeepLink(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: this = " + this + ", taskId = " + getTaskId());
    }
}