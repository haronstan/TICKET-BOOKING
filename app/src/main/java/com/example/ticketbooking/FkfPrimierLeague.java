package com.example.ticketbooking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FkfPrimierLeague extends AppCompatActivity {

    ImageView backarrow;
    LinearLayout match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fkf_primier_league);

        backarrow = findViewById(R.id.back_arrow);
        match = findViewById(R.id.gormahiaLinearLayout);

        match.setOnClickListener(v -> {
            Intent intent = new Intent(this, TicketTCardClicked.class);
            startActivity(intent);

        });

        backarrow.setOnClickListener(v -> {
            Intent intent = new Intent(FkfPrimierLeague.this, Football_categories.class);
            startActivity(intent);

        });
    }
}