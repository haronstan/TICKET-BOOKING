package com.example.ticketbooking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//Activity Foootball_categories
public class Football_categories extends AppCompatActivity {

// Declare ImageView for the back arrow
    ImageView backarrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

// Enable Edge-to-Edge display
        EdgeToEdge.enable(this);

// Set the layout for the activity
        setContentView(R.layout.activity_football_categories);

// Initialize the back arrow ImageView
        backarrow = findViewById(R.id.back_arrow);

// Set an OnClickListener on the back arrow ImageView
        backarrow.setOnClickListener(v -> {

// Create an Intent to start the HomeFragment activity
            Intent intent = new Intent(Football_categories.this, HomeFragment.class);

// Start the HomeFragment activity
            startActivity(intent);
        });
    }
}
