package com.example.ticketbooking;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Buyticket extends AppCompatActivity {

    private GridLayout seatLayout;
    private TextView totalTextView;
    private int vipCount = 1;
    private int regularCount = 1;
    private int vipPrice = 90;
    private int regularPrice = 60;
    private FirebaseFirestore database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyticket);

        // Initialize Firebase Database reference
        database = FirebaseFirestore.getInstance();

        seatLayout = findViewById(R.id.seat_layout);
        totalTextView = findViewById(R.id.totalTextView);

        // Set up seat selection
        setupSeats();

        // Set up ticket count buttons and price calculation
        setupTicketButtons();

        // Set up Buy Ticket button
        setupBuyTicketButton();
    }

    private void setupSeats() {
        // Loop through all children of the seat layout
        for (int i = 0; i < seatLayout.getChildCount(); i++) {
            final TextView seat = (TextView) seatLayout.getChildAt(i);
            seat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Toggle seat selection
                    if (seat.isSelected()) {
                        seat.setSelected(false);
                        seat.setBackgroundResource(R.drawable.seat_background);
                    } else {
                        seat.setSelected(true);
                        seat.setBackgroundResource(R.drawable.seat_background);/*selected*/
                    }
                    updateTotalPrice();
                }
            });
        }
    }

    private void setupTicketButtons() {
        Button vipMinus = findViewById(R.id.vipMinus);
        Button vipPlus = findViewById(R.id.vipPlus);
        final TextView vipCountText = findViewById(R.id.vipCount);

        Button regularMinus = findViewById(R.id.regularMinus);
        Button regularPlus = findViewById(R.id.regularPlus);
        final TextView regularCountText = findViewById(R.id.regularCount);

        vipMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vipCount > 1) {
                    vipCount--;
                    vipCountText.setText(String.valueOf(vipCount));
                    updateTotalPrice();
                }
            }
        });

        vipPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vipCount++;
                vipCountText.setText(String.valueOf(vipCount));
                updateTotalPrice();
            }
        });

        regularMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (regularCount > 1) {
                    regularCount--;
                    regularCountText.setText(String.valueOf(regularCount));
                    updateTotalPrice();
                }
            }
        });

        regularPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regularCount++;
                regularCountText.setText(String.valueOf(regularCount));
                updateTotalPrice();
            }
        });
    }

    private void updateTotalPrice() {
        int total = (vipCount * vipPrice) + (regularCount * regularPrice);
        totalTextView.setText("Total: KSH. " + total);
    }

    private void setupBuyTicketButton() {
        Button buyTicketButton = findViewById(R.id.buyTicketButton);
        buyTicketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < seatLayout.getChildCount(); i++) {
                    TextView seat = (TextView) seatLayout.getChildAt(i);
                    if (seat.isSelected()) {
                        String seatId = seat.getText().toString();
                        // Save selected seat to Firebase
                        saveSeatToDatabase(seatId);
                        // Disable seat rebooking
                        seat.setEnabled(false);
                        seat.setSelected(false);
                        seat.setBackgroundResource(R.drawable.seat_background);/*booked_*/
                    }
                }
                Toast.makeText(Buyticket.this, "Seats booked successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveSeatToDatabase(String seatId) {
        Map<String, String> seatInfo = new HashMap<>();
        seatInfo.put("seatId", seatId);
        database.collection("bookedSeats").document(seatId).set(seatInfo);
    }
}
