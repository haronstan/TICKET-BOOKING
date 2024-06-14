package com.example.ticketbooking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Buyticket extends AppCompatActivity {

    // UI components
    ImageView backbuyticketarrow;
    Button buyticket;
    private FirebaseFirestore db;
    private GridLayout gridRegular, gridVIPSeat;
    private TextView totalSeatsTextView, remainingSeatsTextView;
    private TextView vipCountTextView, regularCountTextView, totalTextView;

    // Seat and price variables
    private int totalSeats = 30;
    private int remainingSeats;
    private int vipPrice = 90;
    private int regularPrice = 60;
    private List<TextView> selectedSeats = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyticket);

        // Initialize UI components
        backbuyticketarrow = findViewById(R.id.buyticketarrow);
        db = FirebaseFirestore.getInstance();
        gridRegular = findViewById(R.id.gridregular);
        gridVIPSeat = findViewById(R.id.gridvip);
        totalSeatsTextView = findViewById(R.id.totalSeatsTextView);
        remainingSeatsTextView = findViewById(R.id.remainingSeatsTextView);
        vipCountTextView = findViewById(R.id.vipCount);
        regularCountTextView = findViewById(R.id.regularCount);
        totalTextView = findViewById(R.id.totalTextView);
        buyticket = findViewById(R.id.buyTicketButton);

        // Display total seats
        totalSeatsTextView.setText(totalSeats + " seats");

        // Fetch and display remaining seats from Firestore
        updateRemainingSeats();

        // Setup seat selection grids
        setupGrid(gridRegular);
        setupGrid(gridVIPSeat);

        // Handle back arrow click
        backbuyticketarrow.setOnClickListener(v -> {
            Intent intent = new Intent(Buyticket.this, TicketsFragment.class);
            startActivity(intent);
        });

        // Handle buy ticket button click
        buyticket.setOnClickListener(v -> reserveSelectedSeats());
    }

    // Setup seat selection grid
    private void setupGrid(GridLayout gridLayout) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            final TextView seat = (TextView) gridLayout.getChildAt(i);
            final String seatId = seat.getResources().getResourceEntryName(seat.getId());

            // Fetch seat reservation status from Firestore
            db.collection("seats").document(seatId).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists() && (boolean) document.get("reserved")) {
                        seat.setBackgroundColor(Color.RED);
                        seat.setEnabled(false);  // Disable reserved seats
                    } else {
                        seat.setOnClickListener(view -> toggleSeatSelection(seat, seatId));
                    }
                }
            });
        }
    }

    // Toggle seat selection
    private void toggleSeatSelection(TextView seat, String seatId) {
        if (selectedSeats.contains(seat)) {
            selectedSeats.remove(seat);
            seat.setBackgroundColor(Color.WHITE);  // Change to unselected color
            updateSeatCountAndPrice(seatId, false);
        } else {
            selectedSeats.add(seat);
            seat.setBackgroundColor(Color.GREEN);  // Change to selected color
            updateSeatCountAndPrice(seatId, true);
        }
    }

    // Update seat count and total price
    private void updateSeatCountAndPrice(String seatId, boolean isSelected) {
        int vipCount = Integer.parseInt(vipCountTextView.getText().toString());
        int regularCount = Integer.parseInt(regularCountTextView.getText().toString());

        // Update VIP or regular seat count
        if (seatId.startsWith("vip")) {
            vipCount = isSelected ? vipCount + 1 : vipCount - 1;
            vipCountTextView.setText(String.valueOf(vipCount));
        } else {
            regularCount = isSelected ? regularCount + 1 : regularCount - 1;
            regularCountTextView.setText(String.valueOf(regularCount));
        }

        // Calculate and display total price
        int totalPrice = (vipCount * vipPrice) + (regularCount * regularPrice);
        totalTextView.setText("KSH. " + totalPrice);
    }

    // Fetch and display remaining seats from Firestore
    private void updateRemainingSeats() {
        db.collection("metadata").document("seating").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    remainingSeats = ((Number) document.get("remainingSeats")).intValue();
                    remainingSeatsTextView.setText(remainingSeats + " remaining");
                } else {
                    remainingSeats = totalSeats;
                   Map<String, Object> data = new HashMap<>();
                    data.put("remainingSeats", remainingSeats);
                    db.collection("metadata").document("seating").set(data);
                    remainingSeatsTextView.setText(remainingSeats + " remaining");
                }
            }
        });
    }

    // Reserve selected seats and update Firestore
    private void reserveSelectedSeats() {
        if (selectedSeats.isEmpty()) {
            Toast.makeText(this, "Please select at least one seat.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Reserve each selected seat in Firestore
        for (TextView seat : selectedSeats) {
            final String seatId = seat.getResources().getResourceEntryName(seat.getId());
            Map<String, Object> seatData = new HashMap<>();
            seatData.put("reserved", true);

            db.collection("seats").document(seatId).set(seatData).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    seat.setBackgroundColor(Color.RED);
                    seat.setEnabled(false);  // Disable reserved seat
                } else {
                    Toast.makeText(this, "Failed to reserve seat. Please try again.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // Update remaining seats in a transaction to ensure consistency
        db.runTransaction(transaction -> {
            DocumentReference seatingRef = db.collection("metadata").document("seating");
            DocumentSnapshot snapshot = transaction.get(seatingRef);
            int newRemainingSeats = snapshot.getLong("remainingSeats").intValue() - selectedSeats.size();
            transaction.update(seatingRef, "remainingSeats", newRemainingSeats);
            return null;
        }).addOnSuccessListener(aVoid -> {
            selectedSeats.clear();
            updateRemainingSeats();
            Toast.makeText(this, "Seats reserved successfully!", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Failed to update remaining seats. Please try again.", Toast.LENGTH_SHORT).show();
        });
    }
}
