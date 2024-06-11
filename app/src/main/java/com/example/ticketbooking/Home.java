package com.example.ticketbooking;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.ticketbooking.databinding.ActivityHomeBinding;
import androidx.appcompat.widget.Toolbar;

public class Home extends AppCompatActivity {
    private ActivityHomeBinding binding;

// Fragments for different sections
    private Fragment homeFragment = new HomeFragment();
    private Fragment ticketsFragment = new TicketsFragment();
    private Fragment transactionsFragment = new TransactionsFragment();
    private Fragment settingsFragment = new SettingsFragment();
    private Fragment adminFragment = new AdminFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

// Inflate the layout using ViewBinding
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

// Set the initial fragment to be displayed
        replaceFragment(homeFragment);

// Set up bottom navigation view to switch between fragments
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
            else if (itemId == R.id.Admin) {
                replaceFragment(adminFragment);
            }
            return true;
        });
    }

// Method to replace the current fragment with a new one
    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }
}
