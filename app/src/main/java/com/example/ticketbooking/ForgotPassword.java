package com.example.ticketbooking;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private String strEmail;
    private Button signInBtn, resetBtn;
    private EditText editEmail;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Initialize UI elements
        resetBtn = findViewById(R.id.resetBtn);
        signInBtn = findViewById(R.id.signInBtn);
        editEmail = findViewById(R.id.RegistrationEmail);
        progressBar = findViewById(R.id.forgetPasswordProgressbar);


        // Initialize Firebase authentication
        firebaseAuth = FirebaseAuth.getInstance();

        // Set onClick listener for the reset button
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strEmail = editEmail.getText().toString().trim();
                if (!TextUtils.isEmpty(strEmail)) {
                    resetPassword();
                } else {
                    editEmail.setError("Email field can't be empty!");
                }
            }
        });

        // Set onClick listener for the sign-in button
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), login.class));
                finish();
            }
        });
    }

    private void resetPassword() {
        progressBar.setVisibility(View.VISIBLE);
        resetBtn.setVisibility(View.GONE);

        firebaseAuth.sendPasswordResetEmail(strEmail)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressBar.setVisibility(View.GONE);
                        resetBtn.setVisibility(View.VISIBLE);
                        Toast.makeText(ForgotPassword.this, "Reset password link has been sent to your registered email. Check your inbox and reset the password.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ForgotPassword.this, login.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        resetBtn.setVisibility(View.VISIBLE);
                        Toast.makeText(ForgotPassword.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
