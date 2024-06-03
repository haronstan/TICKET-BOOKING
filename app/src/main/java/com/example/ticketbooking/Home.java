package com.example.ticketbooking;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.ticketbooking.databinding.ActivityHomeBinding;
import androidx.appcompat.widget.Toolbar;

public class Home extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private Toolbar toolbar;
    private Fragment homeFragment = new HomeFragment();
    private Fragment ticketsFragment = new TicketsFragment();
    private Fragment transactionsFragment = new TransactionsFragment();
    private Fragment settingsFragment = new SettingsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up toolbar
//        toolbar = binding.toolbar;
//        setSupportActionBar(toolbar);

        // Set initial fragment
        replaceFragment(homeFragment);

        // Set up bottom navigation view
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                replaceFragment(homeFragment);
            } else if (itemId == R.id.ticko) {
                replaceFragment(ticketsFragment);
            } else if (itemId == R.id.trans) {
                replaceFragment(transactionsFragment);
            } else if (itemId == R.id.settings) {
                replaceFragment(settingsFragment);
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }
}
/*<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".Home">

    <androidx.appcompat.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:elevation="4dp"
        android:theme="@style/ThemeOverlay.AppCompat.ActionBar"
        app:popupTheme="@style/ThemeOverlay.AppCompat.Light"/>

    <FrameLayout
        android:id="@+id/frame_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_below="@id/toolbar"
        android:layout_above="@id/bottomNavigationView"/>

    <TextView
        android:id="@+id/welcome_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        android:layout_centerHorizontal="true"
        android:text="Welcome Home"
        android:textColor="@android:color/black"
        android:textSize="24sp"
        android:textStyle="bold"/>

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottomNavigationView"
        android:layout_width="match_parent"
        android:layout_height="65dp"
        android:layout_alignParentBottom="true"
        android:background="?android:attr/windowBackground"
        android:elevation="8dp"
        app:labelVisibilityMode="labeled"
        app:itemIconSize="24dp"
        app:itemTextColor="@color/black"
        app:menu="@menu/bottom_menu"/>
</RelativeLayout>
*/