package com.example.ticketbooking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ResetPassword extends AppCompatActivity {

    private Button submitBtn;
    private EditText emailEditText, passwordEditText, confirmPasswordEditText;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        emailEditText = findViewById(R.id.registrationNumber);
        passwordEditText = findViewById(R.id.registerPassword);
        confirmPasswordEditText = findViewById(R.id.confirmPassword);
        submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String email = emailEditText.getText().toString().trim();
        String newPassword = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        if (newPassword.equals(confirmPassword)) {
            db.collection("users").document(email).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            firebaseAuth.sendPasswordResetEmail(email)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                firebaseAuth.signInWithEmailAndPassword(email, newPassword)
                                                        .addOnCompleteListener(new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                                                                if (task.isSuccessful()) {
                                                                    firebaseAuth.getCurrentUser().updatePassword(newPassword)
                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                    if (task.isSuccessful()) {
                                                                                        Toast.makeText(ResetPassword.this, "Password reset successful", Toast.LENGTH_SHORT).show();
                                                                                        startActivity(new Intent(getApplicationContext(), login.class));
                                                                                        finish();
                                                                                    } else {
                                                                                        Toast.makeText(ResetPassword.this, "Error updating password", Toast.LENGTH_SHORT).show();
                                                                                    }
                                                                                }
                                                                            });
                                                                } else {
                                                                    Toast.makeText(ResetPassword.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                            } else {
                                                Toast.makeText(ResetPassword.this, "Failed to send reset email", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(ResetPassword.this, "Email not registered", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d("ResetPassword", "Failed with: ", task.getException());
                    }
                }
            });
        } else {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
    }
}


/*
package com.example.ticketbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

// Define a class named ResetPassword, which extends AppCompatActivity
public class ResetPassword extends AppCompatActivity {

// Declare a private member variable
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

// Call the superclass onCreate method passing the savedInstanceState
        super.onCreate(savedInstanceState);

// Set the layout of this activity to activity_reset_password.xml
        setContentView(R.layout.activity_reset_password);

// Initialize the submit button
        submitBtn = findViewById(R.id.submitBtn);

// Set OnClickListener for submit button
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

// Start login activity
                startActivity(new Intent(getApplicationContext(), login.class));
                finish();
            }
        });
    }
}
*/
