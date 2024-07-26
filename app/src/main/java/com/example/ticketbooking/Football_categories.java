package com.example.ticketbooking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Football_categories extends AppCompatActivity {

        // Declaration
    TextView Text1;
    ImageView arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_football_categories);

        // Initialization
        Text1 = findViewById(R.id.text1);
        arrow = findViewById(R.id.back_arrow);

        // Set a click listener on the TextView
        Text1.setOnClickListener(v -> {
            Intent intent = new Intent(Football_categories.this, FkfPrimierLeague.class);
            startActivity(intent);
        });

        // Set a click listener on the ImageView
        arrow.setOnClickListener(v -> {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        });
    }
}
