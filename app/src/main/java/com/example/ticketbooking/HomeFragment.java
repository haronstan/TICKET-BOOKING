package com.example.ticketbooking;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment {

//Fragment Home
    CardView footballct;

    public HomeFragment() {
// require an empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

// Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        footballct = view.findViewById(R.id.ftcategiries);

        footballct.setOnClickListener(v -> {

// Create an Intent to navigate to the football categories activity
            Intent intent = new Intent(getActivity(), Football_categories.class);

// Start the football categories activity with the created intent
            startActivity(intent);
        });

        return view;
    }
}
