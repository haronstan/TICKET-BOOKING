package com.example.ticketbooking;

// Import necessary classes from Android framework
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// Import AppCompatActivity class from support library
import androidx.appcompat.app.AppCompatActivity;

// Define MainActivity class which extends AppCompatActivity
public class MainActivity extends AppCompatActivity {
    // Declare a Button variable
   private Button button;

    // Override onCreate method which gets called when activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for this activity from activity_main.xml
        setContentView(R.layout.activity_main);

        // Find the Button with id "button" from the layout
        button = findViewById(R.id.btn);

        // Set a click listener on the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to the login activity
                // MainActivity.this is used to reference the current activity
                Intent intent = new Intent(MainActivity.this, login.class);

                // Start the login activity with the created intent
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Welcome, please Sign in.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
