package com.example.ticketbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/*********************************************************************************************************************
 *
 * This code is what controls display and functionality of forgot password screen
 * It uses activity_forgotpassword.xml layout file for the display.
 *
 * IT'S ENTIRELY OPTIONAL SO THERE'S NO NEED TO MESS WITH IT OR IMPLEMENT ANY FUNCTIONALITY.
 * IN FACT AT PRODUCTION IT'LL BE REMOVED SO DON'T WASTE TIME HERE
 *
 * **********************************************************************************************************************/

public class ForgotPassword extends AppCompatActivity {

    private Button signInBtn, resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        resetBtn = findViewById(R.id.resetBtn);
        signInBtn = findViewById(R.id.signInBtn);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ResetPassword.class));
                finish();
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }
}
