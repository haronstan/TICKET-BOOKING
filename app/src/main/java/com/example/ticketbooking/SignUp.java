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

import com.example.ticketbooking.R;
import com.example.ticketbooking.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.Future;

public class SignUp extends AppCompatActivity {
    private Button signUpBtn;
    TextView signInBtn;
    EditText email;
    EditText password;
    EditText firstName;
    EditText lastName;
    EditText phoneNumber;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    @SuppressLint("MissingInflatedIed")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpBtn = findViewById(R.id.signUpBtn);
        signInBtn = findViewById(R.id.signInBtn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.loginPassword);
        firstName = findViewById(R.id.first_Name);
        lastName = findViewById(R.id.last_Name);
        phoneNumber = findViewById(R.id.phoneNo);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore  = FirebaseFirestore.getInstance();


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             String myEmail = email.getText().toString();
             String myPassword = password.getText().toString();
             int myPhoneNumber = Integer.parseInt(phoneNumber.getText().toString());
             String myfirstName = firstName.getText().toString();
             String mylastName = lastName.getText().toString();
              createUser(myEmail, myPassword,myfirstName, mylastName, myPhoneNumber);
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //the below code sends them to admin tab
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
}
    private void createUser(String email, String password, String firstName, String lastName, int phoneNumber) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Saveuserdetail(email, firstName, lastName, phoneNumber);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUp.this, "Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void Saveuserdetail(String email, String firstName, String lastName, int phoneNumber) {

        User user = new User(
            email, firstName, lastName, phoneNumber
        );

        firebaseFirestore.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // Account creation successful, go to login page
                        Toast.makeText(SignUp.this, "Account Created Successfully", Toast.LENGTH_LONG).show();
                        goToLoginPage();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUp.this, "Error creating account", Toast.LENGTH_LONG).show();

                    }
                });

    }

    private void goToLoginPage() {
        Intent intent = new Intent(SignUp.this, login.class);
        startActivity(intent);
        finish();
    }

}
//createUser(email.getText().toString(),password.getText().toString());