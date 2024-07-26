package com.example.ticketbooking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class FkfPrimierLeague extends AppCompatActivity {

        // Declaration
    ImageView backarrow;
    LinearLayout match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display
        EdgeToEdge.enable(this);
        // Set the content view to the specified layout
        setContentView(R.layout.activity_fkf_primier_league);

        // Initialize the back arrow ImageView
        backarrow = findViewById(R.id.back_arrow);

        match = findViewById(R.id.gormahiaLinearLayout);

        // Set a click listener on the match LinearLayout
        match.setOnClickListener(v -> {

        // Create an Intent to start the TicketTCardClicked activity
            Intent intent = new Intent(this, TicketTCardClicked.class);
            startActivity(intent);
        });

        // Set a click listener on the back arrow ImageView
        backarrow.setOnClickListener(v -> {
            Intent intent = new Intent(FkfPrimierLeague.this, Football_categories.class);
            startActivity(intent);
        });
    }
}
