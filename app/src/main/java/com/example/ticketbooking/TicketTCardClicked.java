package com.example.ticketbooking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

// The ticket card activity class
public class TicketTCardClicked extends AppCompatActivity {

// OnCreate method to define the activity's layout and functionality
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_tcard_clicked);

// Find the booking button by its ID and set a click listener on it
        Button bookYourSeat = findViewById(R.id.bookyourseat);

//BookYourSeat button OnClickListener to start the Buyticket activity

        bookYourSeat.setOnClickListener(v -> {
            Intent intent = new Intent(TicketTCardClicked.this, StadiumSeats.class);
            startActivity(intent);
             });

    }
}
