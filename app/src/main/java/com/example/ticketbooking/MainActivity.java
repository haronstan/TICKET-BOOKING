package com.example.ticketbooking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.ticketbooking.R;
import com.example.ticketbooking.databinding.ActivityMainBinding;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {
     Toolbar toolbar;
    ActivityMainBinding binding;
    Fragment homeFragment = new HomeFragment();
    Fragment ticketsFragment = new TicketsFragment();
    Fragment transactionsFragment = new TransactionsFragment();
    Fragment settingsFragment = new SettingsFragment();
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        replaceFragment(homeFragment);
        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener (item -> {
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

        // Find the Button with id "button" from the layout
        button = findViewById(R.id.btn);

        // Set a click listener on the button
        button.setOnClickListener(v -> {
            // Create an Intent to navigate to the login activity
            Intent intent = new Intent(MainActivity.this, login.class);

            // Start the login activity with the created intent
            startActivity(intent);
            Toast.makeText(MainActivity.this, "Welcome, please Sign in.", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frame_layout, fragment).commit();
    }
}

class stanleyClass{

}