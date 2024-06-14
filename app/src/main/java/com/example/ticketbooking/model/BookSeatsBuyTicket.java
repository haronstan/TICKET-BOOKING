package com.example.ticketbooking.model;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticketbooking.R;
import com.example.ticketbooking.TicketsFragment;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookSeatsBuyTicket {
}

/*package com.example.ticketbooking.model;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticketbooking.R;
import com.example.ticketbooking.TicketsFragment;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookSeatsBuyTicket {
    private boolean reserved;
    private String seatType;  // Additional field if you want to store more info, e.g., seat type

    // Constructor
    public BookSeatsBuyTicket(boolean reserved, String seatType) {
        this.reserved = reserved;
        this.seatType = seatType;
    }

    // Getter for reserved
    public boolean isReserved() {
        return reserved;
    }

    // Setter for reserved
    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    // Getter for seat type
    public String getSeatType() {
        return seatType;
    }

    // Setter for seat type
    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }
}


*/


