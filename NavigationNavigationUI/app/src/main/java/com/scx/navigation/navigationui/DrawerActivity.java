package com.scx.navigation.navigationui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.scx.navigation.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class DrawerActivity extends AppCompatActivity {
    private static final String TAG = "DrawerActivity";
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Log.d(TAG, "onCreate: this = " + this + ", taskId = " + getTaskId() + ", flag = " + intent.getFlags() + ", intent = " + intent);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        // 直接通过NavHostFragment获取NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();


        // 绑定DrawerLayout
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.aFragment)
                .setOpenableLayout(drawerLayout)
                .build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);

        // 绑定NavigationView
        NavigationView navigationView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: this = " + this + ", taskId = " + getTaskId());
    }
}