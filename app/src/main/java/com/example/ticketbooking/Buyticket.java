package com.example.ticketbooking;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Buyticket extends AppCompatActivity {

    private FirebaseFirestore db;
    private GridLayout gridRegular, gridVIPSeat;
    private TextView totalSeatsTextView, remainingSeatsTextView;
    private int totalSeats = 573;
    private int remainingSeats = 240; // Initial remaining seats

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyticket);

        db = FirebaseFirestore.getInstance();
        gridRegular = findViewById(R.id.gridregular);
        gridVIPSeat = findViewById(R.id.gridvip);
        totalSeatsTextView = findViewById(R.id.totalSeatsTextView);
        remainingSeatsTextView = findViewById(R.id.remainingSeatsTextView);

        totalSeatsTextView.setText(totalSeats + " seats");
        remainingSeatsTextView.setText(remainingSeats + " remaining");

        setupGrid(gridRegular);
        setupGrid(gridVIPSeat);
    }

    private void setupGrid(GridLayout gridLayout) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            final TextView seat = (TextView) gridLayout.getChildAt(i);
            final String seatId = seat.getResources().getResourceEntryName(seat.getId());

            // Check if the seat is already reserved
            db.collection("seats").document(seatId).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists() && (boolean) document.get("reserved")) {
                        seat.setBackgroundColor(Color.RED);
                        seat.setEnabled(false);
                    } else {
                        seat.setOnClickListener(view -> reserveSeat(seat, seatId));
                    }
                }
            });
        }
    }

    private void reserveSeat(TextView seat, String seatId) {
        Map<String, Object> seatData = new HashMap<>();
        seatData.put("reserved", true);

        db.collection("seats").document(seatId).set(seatData).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                seat.setBackgroundColor(Color.RED);
                seat.setEnabled(false);
                remainingSeats--;
                remainingSeatsTextView.setText(remainingSeats + " remaining");
                Toast.makeText(this, "Seat " + seat.getText() + " reserved successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to reserve seat. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
