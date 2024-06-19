package com.example.ticketbooking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ticketbooking.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

// Define a class named SignUp, which extends AppCompatActivity
public class SignUp extends AppCompatActivity {

    // Declare private member variables
    private Button signUpBtn;
    TextView signInBtn;
    EditText email;
    private String userid;
    EditText password;
    EditText firstName;
    EditText lastName;
    EditText phoneNumber;


    // Declare FirebaseAuth and FirebaseFirestore instances
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @SuppressLint("MissingInflatedIed")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

// Set the layout of this activity to activity_sign_up.xml
        setContentView(R.layout.activity_sign_up);

// Initialize UI elements
        signUpBtn = findViewById(R.id.signUpBtn);
        signInBtn = findViewById(R.id.signInBtn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.loginPassword);
        firstName = findViewById(R.id.first_Name);
        lastName = findViewById(R.id.last_Name);
        phoneNumber = findViewById(R.id.phoneNo);

// Initialize FirebaseAuth and FirebaseFirestore instances
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore  = FirebaseFirestore.getInstance();

// Set OnClickListener for sign-up button
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

// Retrieve user input
                String myEmail = email.getText().toString();
                String myPassword = password.getText().toString();
                int myPhoneNumber = Integer.parseInt(phoneNumber.getText().toString());
                String myFirstName = firstName.getText().toString();
                String myLastName = lastName.getText().toString();

// Create user account
                createUser(myEmail, myPassword, myFirstName, myLastName, myPhoneNumber);
            }
        });

// Set OnClickListener for sign-in button
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

// Method to create user account using FirebaseAuth
    private void createUser(String email, String password, String firstName, String lastName, int phoneNumber) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

// If account creation is successful, save user details
                        if (task.isSuccessful()) {
                            saveUserDetails(email, firstName, lastName, phoneNumber, userid);
                        } else {
                            // If account creation fails, display an error message
                            Toast.makeText(SignUp.this, "Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

// Method to save user details to FireStore
    private void saveUserDetails(String email, String firstName, String lastName, int phoneNumber, String userid) {

// Create a User object
        User user = new User(email, firstName, lastName, phoneNumber, userid);

// Add user object to FireStore
        firebaseFirestore.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

// If user details are saved successfully, display a success message and goToLoginPage For Navigating to login page
                        Toast.makeText(SignUp.this, "Account Created Successfully", Toast.LENGTH_LONG).show();
                        goToLoginPage();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

// If an error occurs while saving user details, display an error message
                        Toast.makeText(SignUp.this, "Error creating account", Toast.LENGTH_LONG).show();
                    }
                });
    }

// Method to navigate to login page
    private void goToLoginPage() {
        Intent intent = new Intent(SignUp.this, login.class);
        startActivity(intent);
        finish();
    }
}