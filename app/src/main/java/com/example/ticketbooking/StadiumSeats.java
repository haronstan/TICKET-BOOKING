package com.example.ticketbooking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticketbooking.model.BookSeatsBuyTicket;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StadiumSeats extends AppCompatActivity {

    // UI components
    ImageView backbuyticketarrow;
    Button buyticket;
    FirebaseFirestore db;
    GridLayout gridRegular, gridVIPSeat, gridLeftSeats, gridBottomSeats;
    TextView totalSeatsTextView, remainingSeatsTextView;
    TextView vipCountTextView, regularCountTextView, totalTextView;

    // Seat and price variables
    private int totalSeats = 72;
    private int vipPrice = 90;
    private int regularPrice = 60;
    private List<TextView> selectedSeats = new ArrayList<>();
    // User information
    String userEmail, userName, lastName;
    int phoneNumber;
    private int remainingSeats;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadium_seats);

        // Retrieve the user name from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        userEmail = sharedPreferences.getString("userEmail", null);
        userName = sharedPreferences.getString("userName", "user");
        // "User" is the default value if "userName" is not found

        // Initialize UI components
        backbuyticketarrow = findViewById(R.id.buyticketarrow);
        db = FirebaseFirestore.getInstance();
        gridRegular = findViewById(R.id.gridlayoutseats); // Assuming gridlayoutseats is for regular seats
        gridVIPSeat = findViewById(R.id.gridlayouttopseats); // Assuming gridlayouttopseats is for VIP seats
        gridLeftSeats = findViewById(R.id.gridLeftSeats); // Assuming gridlayoutleftseats is for left seats
        gridBottomSeats = findViewById(R.id.gridBottomSeats); // Assuming gridlayoutbottomseats is for bottom seats
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
        setupGrid(gridLeftSeats);
        setupGrid(gridBottomSeats);

        // Handle back arrow click
        backbuyticketarrow.setOnClickListener(v -> {
            Intent intent = new Intent(this, TicketTCardClicked.class);
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
                        seat.setEnabled(false);
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
            seat.setBackgroundColor(Color.GREEN);
            updateSeatCountAndPrice(seatId, false);
        } else {
            selectedSeats.add(seat);
            seat.setBackgroundColor(Color.GREEN);
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
                    BookSeatsBuyTicket bookSeatsBuyTicket = new BookSeatsBuyTicket(remainingSeats);
                    db.collection("metadata").document("seating").set(bookSeatsBuyTicket);
                    remainingSeatsTextView.setText(remainingSeats + " remaining");
                }
            }
        });
    }

    // Fetch user details from Firestore
    private void fetchUserDetails() {
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        userEmail = prefs.getString("userEmail", "");
        if (userEmail.isEmpty()) {
            Toast.makeText(this, "User email not found. Please log in again.", Toast.LENGTH_SHORT).show();
            return;
        }
        db.collection("users").document(userEmail).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    userEmail = document.getString("userEmail");
                    userName = document.getString("firstName");

                } else {
                    Toast.makeText(this, "User details not found. Please complete your profile.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Failed to fetch user details. Please try again.", Toast.LENGTH_SHORT).show();
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
            seatData.put("userEmail", userEmail);
            seatData.put("userName", userName);

            db.collection("seats").document(seatId).set(seatData).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    seat.setBackgroundColor(Color.RED);
                    seat.setEnabled(false);
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

            // Pass total price to Payment activity // Navigate to PaymentActivity
            Intent intent = new Intent(StadiumSeats.this, Payment.class);
            //intent.putExtra("totalAmount", totalPrice);
            startActivity(intent);
            
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Failed to update remaining seats. Please try again.", Toast.LENGTH_SHORT).show();
        });
    }
}










