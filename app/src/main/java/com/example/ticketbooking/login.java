package com.example.ticketbooking;

// Import necessary Android classes
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// Import AppCompatActivity class from androidx.appcompat library
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

// Define a class named login, which extends AppCompatActivity
public class login extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    EditText email;
    EditText password;
    // Declare a private member variable
    private Button loginBtn;
    TextView signUpBtn, forgotPassBtn;

    // Override the onCreate method of AppCompatActivity
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Call the superclass onCreate method passing the savedInstanceState
        super.onCreate(savedInstanceState);

        // Set the layout of this activity to activity_login.xml
        setContentView(R.layout.activity_login);

        signUpBtn = findViewById(R.id.signUpBtn);
        loginBtn = findViewById(R.id.login_Btn);
        forgotPassBtn = findViewById(R.id.forgotPasswordBtn);
        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.txtemail_login);
        password = findViewById(R.id.txtPass_login);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // the below code sends them to
                startActivity(new Intent(getApplicationContext(), SignUp.class));
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myEmail = email.getText().toString().trim();
                String myPassword = password.getText().toString().trim();

                if (TextUtils.isEmpty(myEmail)) {
                    email.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(myPassword)) {
                    password.setError("Password is required");
                    return;
                }

                loginUser(myEmail, myPassword);
            }
        });

        forgotPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // this code sends them to forgot password activity
                startActivity(new Intent(getApplicationContext(), ForgotPassword.class));
                finish();
            }
        });
    }

    private void loginUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Login successful, go to main page
                            Toast.makeText(login.this, "Login Successful", Toast.LENGTH_LONG).show();
                            goToMainPage();
                        } else {
                            // If login fails, display a message to the user.
                            Toast.makeText(login.this, "Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void goToMainPage() {
        Intent intent = new Intent(login.this, Home.class);
        startActivity(intent);
        finish();
    }
}

//class to login
/*package com.example.ticketbooking;

// Import necessary Android classes
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// Import AppCompatActivity class from androidx.appcompat library
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

// Define a class named login, which extends AppCompatActivity
public class login extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    EditText email;
    EditText password;
    // Declare a private member variable
    private Button loginBtn;
    TextView signUpBtn, forgotPassBtn;

    // Override the onCreate method of AppCompatActivity
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Call the superclass onCreate method passing the savedInstanceState
        super.onCreate(savedInstanceState);

        // Set the layout of this activity to activity_login.xml
        setContentView(R.layout.activity_login);

        signUpBtn = findViewById(R.id.signUpBtn);
        loginBtn = findViewById(R.id.login_Btn);
        forgotPassBtn = findViewById(R.id.forgotPasswordBtn);
        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.txtemail_login);
        password = findViewById(R.id.txtPass_login);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //the below code sends them to
                startActivity(new Intent(getApplicationContext(), SignUp.class));
                finish();
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myEmail = email.getText().toString();
                String myPassword = password.getText().toString();
                loginUser(myEmail, myPassword);
            }

        });

        forgotPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this code sends them to forgot password activity
                startActivity(new Intent(getApplicationContext(), ForgotPassword.class));
                finish();
            }
        });

        private fun login() {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (TextUtils.isEmpty(username)) {
                emailEditText.error = "Email is required"
                return
            }

            if (TextUtils.isEmpty(password)) {
                passwordEditText.error = "Password is required"
                return
            }
    }

    private void loginUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Login successful, go to main page
                            Toast.makeText(login.this, "Login Successful", Toast.LENGTH_LONG).show();
                            goToMainPage();
                        } else {
                            // If login fails, display a message to the user.
                            Toast.makeText(login.this, "Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void goToMainPage() {
        Intent intent = new Intent(login.this, Home.class);
        startActivity(intent);
        finish();
    }

}
//class to login
*/