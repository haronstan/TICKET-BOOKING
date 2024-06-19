package com.example.ticketbooking;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class TicketsFragment extends Fragment {

// Declared ImageView variable
    ImageView gormahia;

    public TicketsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

// Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tickets, container, false);
        gormahia = view.findViewById(R.id.GorMahia);
        gormahia.setOnClickListener(v -> {

// Create an Intent to navigate to the BuyTicket activity
            Intent intent = new Intent(getActivity(), Buyticket.class);
            startActivity(intent);
        });

        return view;
    }
}
