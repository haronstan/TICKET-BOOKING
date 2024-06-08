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

public class Football_categories extends AppCompatActivity {
    ImageView backarrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_football_categories);

        backarrow = findViewById(R.id.back_arrow);

        // Set a click listener on the button
        backarrow.setOnClickListener(v -> {
            // Create an Intent to navigate to the login activity
            Intent intent = new Intent(Football_categories.this, HomeFragment.class);

            // Start the login activity with the created intent
            startActivity(intent);
        });
    }
}