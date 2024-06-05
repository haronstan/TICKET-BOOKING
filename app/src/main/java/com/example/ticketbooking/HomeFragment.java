package com.example.ticketbooking;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    TextView footballct;

    public HomeFragment() {
        // require an empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        footballct = view.findViewById(R.id.ft);


        footballct.setOnClickListener(v -> {
            // Create an Intent to navigate to the login activity
            Intent intent = new Intent(getActivity(), Football_categories.class);

            // Start the login activity with the created intent
            startActivity(intent);
            Toast.makeText(getActivity(), "Welcome, please Sign in.", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

   /* private void navigateToFootballCategoriesFragment() {
        footballct football_Categories = new Football_categories();
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, football_Categories);
        transaction.addToBackStack(null);
        transaction.commit();
    }*/
}
