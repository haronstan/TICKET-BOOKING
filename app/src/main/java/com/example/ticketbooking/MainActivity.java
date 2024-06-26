package com.example.ticketbooking;

// Import statements
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ticketbooking.R;
import com.example.ticketbooking.databinding.ActivityMainBinding; // Note: this import seems unused in the current code
import androidx.appcompat.widget.Toolbar;

// MainActivity class definition
public class MainActivity extends AppCompatActivity {
    // Declare a Button variable
    private Button button;

    // onCreate method is the entry point for the activity lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    // Initialize the button by finding it from the layout
        button = findViewById(R.id.btn);

    // Set an OnClickListener to the button to handle click events
        button.setOnClickListener(v -> {

    // Intent to navigate from MainActivity to login activity
            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);
            Toast.makeText(MainActivity.this, "Welcome, please Sign in.", Toast.LENGTH_SHORT).show();
            finish();
        });
    }}
