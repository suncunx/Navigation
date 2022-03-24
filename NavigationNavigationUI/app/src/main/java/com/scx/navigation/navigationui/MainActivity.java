package com.scx.navigation.navigationui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.scx.navigation.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Log.d(TAG, "onCreate: this = " + this + ", taskId = " + getTaskId() + ", flag = " + intent.getFlags() + ", intent = " + intent);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setOnMenuItemClickListener(item -> {
            Log.d(TAG, "onCreate: item = " + item);
            NavigationUI.onNavDestinationSelected(item, navController);
            return false;
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        // 如果Navigation组件使用Toolbar，那么不应该将它设置为ActionBar
//        setSupportActionBar(toolbar);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.cFragment)
                .setFallbackOnNavigateUpListener(new AppBarConfiguration.OnNavigateUpListener() {
                    @Override
                    public boolean onNavigateUp() {
                        Log.d(TAG, "onNavigateUp: ");
                        return true;
                    }
                }).build();

        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    // Toolbar必须是Actionbar才能使用本方法
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: this = " + this + ", taskId = " + getTaskId());
    }
}