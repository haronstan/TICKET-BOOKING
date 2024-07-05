package com.example.ticketbooking;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            Intent intent = new Intent(this, stadiumseats.class);
            startActivity(intent);
             });

    }
}
