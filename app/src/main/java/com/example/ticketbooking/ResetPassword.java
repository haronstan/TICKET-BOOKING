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


/*<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"
xmlns:tools="http://schemas.android.com/tools"
xmlns:app="http://schemas.android.com/apk/res-auto"
android:padding="16dp"
android:background="#FFFFFF"
tools:context=".Buyticket">

        <com.google.android.material.bottomsheet.BottomSheetDragHandleView
android:id="@+id/bottomSheetDragHandleView"
android:layout_width="match_parent"
android:layout_marginTop="-13dp"
android:layout_centerHorizontal="true"
android:layout_height="wrap_content" />


    <!-- Back Arrow ImageView -->

    <!-- Match Details -->
    <ImageView
android:id="@+id/buyticketarrow"
android:layout_width="60dp"
android:layout_height="wrap_content"
android:layout_below="@+id/bottomSheetDragHandleView"
android:layout_alignParentStart="true"
android:layout_marginStart="5dp"
android:layout_marginTop="-31dp"
android:layout_marginBottom="100dp"
android:src="@drawable/baseline_back_24" />

    <TextView
android:id="@+id/matchDetailsTextView"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_centerHorizontal="true"
android:text="Gor Mahia FC vs AFC Leopards"
android:textSize="18sp"
android:textStyle="bold"
android:layout_gravity="center"
android:layout_marginTop="3dp" />

        <androidx.cardview.widget.CardView
android:id="@+id/detailscardview"
android:layout_width="match_parent"
android:layout_height="135dp"
android:orientation="vertical"
android:backgroundTint="#F5F5F5"
android:layout_marginTop="40dp"
app:cardCornerRadius="16dp">


        <LinearLayout
android:id="@+id/date"
android:layout_width="match_parent"
android:layout_height="23dp"
android:layout_marginStart="10dp"
android:layout_marginTop="5dp">

            <ImageView
android:id="@+id/dateImageView"
android:layout_width="24dp"
android:layout_height="24dp"
android:src="@drawable/date"
android:layout_marginStart="10dp"/>

            <TextView
android:id="@+id/dateTextView"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="24th Mar, 2024"
android:layout_marginStart="15dp"
android:layout_marginEnd="16dp" />
            </LinearLayout>

            <LinearLayout
android:id="@+id/location"
android:layout_width="match_parent"
android:layout_height="23dp"
android:layout_marginTop="30dp"
android:layout_marginStart="10dp"
android:layout_below="@+id/date">

            <ImageView
android:id="@+id/locationImageView"
android:layout_width="24dp"
android:layout_height="24dp"
android:layout_marginStart="10dp"
android:src="@drawable/location" />

            <TextView
android:id="@+id/locationTextView"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Kasarani Stadium"
android:layout_marginStart="15dp"
android:layout_marginEnd="16dp" />
            </LinearLayout>

            <LinearLayout
android:id="@+id/time"
android:layout_width="match_parent"
android:layout_height="23dp"
android:layout_marginTop="56dp"
android:layout_marginStart="10dp"
android:layout_below="@+id/location">

            <ImageView
android:id="@+id/timeImageView"
android:layout_width="24dp"
android:layout_height="24dp"
android:layout_marginStart="10dp"
android:src="@drawable/time" />

            <TextView
android:id="@+id/timeTextView"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="17:00 HRS"
android:layout_marginStart="15dp" />
            </LinearLayout>

        <LinearLayout
android:layout_below="@+id/time"
android:layout_width="match_parent"
android:layout_height="50dp"
android:orientation="horizontal"
android:layout_marginStart="15dp"
android:layout_marginTop="80dp">

            <ImageView
android:id="@+id/seatImageView"
android:layout_width="wrap_content"
android:layout_height="44dp"
android:layout_marginStart="10dp"
android:src="@drawable/seat" />

            <LinearLayout
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:orientation="vertical"
android:layout_marginStart="15dp"
android:layout_marginEnd="16dp">

                <TextView
android:id="@+id/totalSeatsTextView"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="573 seats" />

                <TextView
android:id="@+id/remainingSeatsTextView"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="240 remaining"
android:textColor="#FF0000"
android:layout_marginTop="4dp" /> <!-- Optional margin to provide a little spacing -->
            </LinearLayout>

        </LinearLayout>
        </androidx.cardview.widget.CardView>

       <!--Choose your seat section-->
        <TextView
android:layout_below="@+id/detailscardview"
android:id="@+id/chooseSeatsTextView"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Choose your seats"
android:drawableEnd="@drawable/chooseseattick"
android:textSize="18sp"
android:textStyle="bold"
android:layout_marginTop="16dp"
android:layout_gravity="center"
android:layout_centerHorizontal="true"/>

    <HorizontalScrollView
android:layout_width="match_parent"
android:id="@+id/hrscrolview"
android:layout_height="60dp"
android:layout_below="@+id/chooseSeatsTextView"
android:layout_marginStart="5dp">
        <LinearLayout
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:orientation="horizontal"
android:layout_marginStart="35dp"
android:layout_below="@+id/chooseSeatsTextView"
android:layout_marginTop="8dp">

            <Button
android:id="@+id/showAllSeatsButton"
android:layout_width="140dp"
android:layout_height="wrap_content"
android:text="Show All Seats"
android:drawablePadding="8dp"
android:backgroundTint="#03A9F4"
android:textColor="#FFFFFF"
android:layout_marginEnd="8dp" />

            <Button
android:id="@+id/showRegularSeatsButton"
android:layout_width="140dp"
android:layout_height="wrap_content"
android:text="Show Regular Seats"
android:drawablePadding="8dp"
android:textColor="#FFFFFF"
android:backgroundTint="#03A9F4"
android:layout_marginEnd="8dp" />

            <Button
android:id="@+id/showVipSeatsButton"
android:layout_width="140dp"
android:layout_height="wrap_content"
android:text="Show VIP Seats"
android:drawablePadding="8dp"
android:backgroundTint="#03A9F4"
android:textColor="#FFFFFF" />
        </LinearLayout>
    </HorizontalScrollView>

    <GridLayout
android:id="@+id/gridvip"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:columnCount="5"
android:rowCount="8"
android:layout_marginEnd="5dp"
android:layout_marginStart="200dp"
android:layout_marginTop="15dp"
android:layout_below="@+id/hrscrolview"
android:layout_marginRight="8dp">
        <!--Row 1 -->

            <TextView android:id="@+id/seat_16" android:textStyle="bold"    android:textSize="12dp" android:layout_width="30dp" android:layout_height="40dp" android:background="@drawable/img" android:text="16" android:gravity="center"/>
            <TextView android:id="@+id/seat_17" android:textStyle="bold"    android:textSize="12dp" android:layout_width="30dp" android:layout_height="40dp" android:background="@drawable/img" android:text="17" android:gravity="center"/>
            <TextView android:id="@+id/seat_18" android:textStyle="bold"    android:textSize="12dp" android:layout_width="30dp" android:layout_height="40dp" android:background="@drawable/img" android:text="18" android:gravity="center"/>
            <TextView android:id="@+id/seat_19" android:textStyle="bold"    android:textSize="12dp" android:layout_width="30dp" android:layout_height="40dp" android:background="@drawable/img" android:text="19" android:gravity="center"/>
            <TextView android:id="@+id/seat_20" android:textStyle="bold"    android:textSize="12dp" android:layout_width="30dp" android:layout_height="40dp" android:background="@drawable/img" android:text="20" android:gravity="center"/>

            <!--Row 2 -->
            <TextView android:id="@+id/seat_21" android:textStyle="bold"    android:textSize="12dp" android:layout_width="30dp" android:layout_height="40dp" android:background="@drawable/img" android:text="21" android:gravity="center"/>
            <TextView android:id="@+id/seat_22" android:textStyle="bold"    android:textSize="12dp" android:layout_width="30dp" android:layout_height="40dp" android:background="@drawable/img" android:text="22" android:gravity="center"/>
            <TextView android:id="@+id/seat_23" android:textStyle="bold"    android:textSize="12dp" android:layout_width="30dp" android:layout_height="40dp" android:background="@drawable/img" android:text="23" android:gravity="center"/>
            <TextView android:id="@+id/seat_24" android:textStyle="bold"    android:textSize="12dp" android:layout_width="30dp" android:layout_height="40dp" android:background="@drawable/img" android:text="24" android:gravity="center"/>
            <TextView android:id="@+id/seat_25" android:textStyle="bold"    android:textSize="12dp" android:layout_width="30dp" android:layout_height="40dp" android:background="@drawable/img" android:text="25" android:gravity="center"/>

            <!--Row 3 -->
            <TextView android:id="@+id/seat_26" android:textStyle="bold"    android:textSize="12dp" android:layout_width="30dp" android:layout_height="40dp" android:background="@drawable/img" android:text="26" android:gravity="center"/>
            <TextView android:id="@+id/seat_27" android:textStyle="bold"    android:textSize="12dp" android:layout_width="30dp" android:layout_height="40dp" android:background="@drawable/img" android:text="27" android:gravity="center"/>
            <TextView android:id="@+id/seat_28" android:textStyle="bold"    android:textSize="12dp" android:layout_width="30dp" android:layout_height="40dp" android:background="@drawable/img" android:text="28" android:gravity="center"/>
            <TextView android:id="@+id/seat_29" android:textStyle="bold"    android:textSize="12dp" android:layout_width="30dp" android:layout_height="40dp" android:background="@drawable/img" android:text="29" android:gravity="center"/>
            <TextView android:id="@+id/seat_30" android:textStyle="bold"    android:textSize="12dp" android:layout_width="30dp" android:layout_height="40dp" android:background="@drawable/img" android:text="30" android:gravity="center"/>

        </GridLayout>

       <!-- Seats A1 to A10-->
    <GridLayout
android:id="@+id/gridregular"
android:layout_width="330dp"
android:layout_height="119dp"
android:layout_below="@+id/hrscrolview"
android:layout_marginStart="5dp"
android:layout_marginTop="15dp"
android:columnCount="5"
android:orientation="horizontal"
android:rowCount="12">

        <!--Row 1-->
        <TextView android:id="@+id/seat_1" android:layout_width="30dp" android:layout_height="40dp" android:background="@drawable/regular" android:gravity="center" android:text="1" android:textStyle="bold" android:textSize="12dp"/>
        <TextView android:id="@+id/seat_2" android:layout_width="30dp" android:layout_height="40dp" android:background="@drawable/regular" android:gravity="center" android:text="2" android:textStyle="bold" android:textSize="12dp"/>

        <TextView
android:id="@+id/seat_3"
android:layout_width="30dp"
android:layout_height="40dp"
android:background="@drawable/regular"
android:gravity="center"
android:text="3"
android:textStyle="bold"
android:textSize="12dp"/>

        <TextView
android:id="@+id/seat_4"
android:layout_width="30dp"
android:layout_height="40dp"
android:background="@drawable/regular"
android:gravity="center"
android:text="4"
android:textStyle="bold"
android:textSize="12dp"/>

        <TextView
android:id="@+id/seat_5"
android:layout_width="30dp"
android:layout_height="40dp"
android:background="@drawable/regular"
android:gravity="center"
android:text="5"
android:textStyle="bold"
android:textSize="12dp"/>

        <!-- Row 2 -->
        <TextView
android:id="@+id/seat_6"
android:layout_width="30dp"
android:layout_height="40dp"
android:background="@drawable/regular"
android:gravity="center"
android:text="6"
android:textStyle="bold"
android:textSize="12dp"/>

        <TextView
android:id="@+id/seat_7"
android:layout_width="30dp"
android:layout_height="40dp"
android:background="@drawable/regular"
android:gravity="center"
android:text="7"
android:textStyle="bold"
android:textSize="12dp"/>

        <TextView
android:id="@+id/seat_8"
android:layout_width="30dp"
android:layout_height="40dp"
android:background="@drawable/regular"
android:gravity="center"
android:text="8"
android:textStyle="bold"
android:textSize="12dp"/>

        <TextView
android:id="@+id/seat_9"
android:layout_width="30dp"
android:layout_height="40dp"
android:background="@drawable/regular"
android:gravity="center"
android:text="9"
android:textStyle="bold"
android:textSize="12dp"/>

        <TextView
android:id="@+id/seat_10"
android:layout_width="30dp"
android:layout_height="40dp"
android:background="@drawable/regular"
android:gravity="center"
android:text="10"
android:textStyle="bold"
android:textSize="12dp"/>

        <!--Row 3-->
        <TextView
android:id="@+id/seat_11"
android:layout_width="30dp"
android:layout_height="40dp"
android:background="@drawable/regular"
android:gravity="center"
android:text="11"
android:textStyle="bold"
android:textSize="12dp"/>

        <TextView
android:id="@+id/seat_12"
android:layout_width="30dp"
android:layout_height="40dp"
android:background="@drawable/regular"
android:gravity="center"
android:text="12"
android:textStyle="bold"
android:textSize="12dp"/>

        <TextView
android:id="@+id/seat_13"
android:layout_width="30dp"
android:layout_height="40dp"
android:background="@drawable/regular"
android:gravity="center"
android:text="13"
android:textStyle="bold"
android:textSize="12dp"/>

        <TextView
android:id="@+id/seat_14"
android:layout_width="30dp"
android:layout_height="40dp"
android:background="@drawable/regular"
android:gravity="center"
android:text="14"
android:textStyle="bold"
android:textSize="12dp"/>

        <TextView
android:id="@+id/seat_15"
android:layout_width="30dp"
android:layout_height="40dp"
android:background="@drawable/regular"
android:gravity="center"
android:text="15"
android:textStyle="bold"
android:textSize="12dp"/>
    </GridLayout>

 <TextView
android:id="@+id/bookSeatsTextView"
android:layout_width="wrap_content"
android:layout_below="@+id/gridregular"
android:layout_height="wrap_content"
android:layout_centerHorizontal="true"
android:text="Book your seat"
android:textSize="18sp"
android:textStyle="bold"
android:layout_marginTop="16dp"
android:layout_gravity="center" />

    <LinearLayout
android:id="@+id/calcsection"
android:layout_below="@+id/bookSeatsTextView"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:orientation="horizontal"
android:gravity="center"
android:layout_marginTop="8dp">

        <TextView
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Type of seat"
android:layout_weight="1" />

        <TextView
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Price"
android:layout_weight="1" />

        <TextView
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="No. of tickets"
android:layout_weight="1" />
    </LinearLayout>

    <LinearLayout
android:id="@+id/vipseat"
android:layout_below="@+id/calcsection"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:orientation="horizontal"
android:gravity="center"
android:layout_marginTop="8dp">

        <TextView
android:id="@+id/vipSeatTextView"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="1 VIP Seat"
android:layout_weight="1" />

        <TextView
android:id="@+id/vipPriceTextView"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="KSH. 90"
android:textColor="#4CAF50"
android:layout_weight="1" />

        <LinearLayout
android:id="@+id/vipprice"
android:layout_below="@+id/vipseat"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:orientation="horizontal"
android:gravity="center">

            <Button
android:id="@+id/vipMinus"
android:layout_width="60dp"
android:layout_height="wrap_content"
android:text="-"
android:textSize="25dp"
android:textStyle="bold"
android:backgroundTint="#FFFFFF"
android:textColor="#F60707" />

            <TextView
android:id="@+id/vipCount"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="0"
android:padding="8dp" />

            <Button
android:id="@+id/vipPlus"
android:layout_width="60dp"
android:layout_height="wrap_content"
android:text="+"
android:textSize="25dp"
android:textStyle="bold"
android:backgroundTint="#FFFFFF"
android:textColor="#0737F6" />
        </LinearLayout>
    </LinearLayout>

    <LinearLayout
android:id="@+id/regularp"
android:layout_below="@+id/vipseat"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:orientation="horizontal"
android:gravity="center"
android:layout_marginTop="8dp">

        <TextView
android:id="@+id/regularSeatTextView"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="1 Regular Seat"
android:layout_weight="1" />

        <TextView
android:id="@+id/regularPriceTextView"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="KSH. 60"
android:textColor="#4CAF50"
android:layout_weight="1" />

        <LinearLayout
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:orientation="horizontal"
android:gravity="center">

            <Button
android:id="@+id/regularMinus"
android:layout_width="60dp"
android:layout_height="wrap_content"
android:text="-"
android:textSize="25dp"
android:textStyle="bold"
android:backgroundTint="#FFFFFF"
android:textColor="#F60707" />

            <TextView
android:id="@+id/regularCount"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="0"
android:padding="8dp" />

            <Button
android:id="@+id/regularPlus"
android:layout_width="60dp"
android:layout_height="wrap_content"
android:text="+"
android:textSize="25dp"
android:textStyle="bold"
android:backgroundTint="#FFFFFF"
android:textColor="#0737F6" />
        </LinearLayout>
    </LinearLayout>

    <LinearLayout
android:id="@+id/totalprice"
android:layout_below="@+id/regularp"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:orientation="horizontal"
android:gravity="center"
android:layout_marginTop="16dp">

        <TextView
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Total:"
android:textSize="16sp"
android:layout_marginStart="120dp"
android:layout_weight="1" />

        <TextView
android:id="@+id/totalTextView"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="KSH. 150"
android:textSize="16sp"
android:layout_marginEnd="120dp"
android:textColor="#4CAF50"
android:layout_weight="1" />
    </LinearLayout>

    <Button
android:id="@+id/buyTicketButton"
android:layout_width="136dp"
android:layout_height="wrap_content"
android:text="Buy Ticket"
android:backgroundTint="#277ee4"
android:layout_centerHorizontal="true"
android:layout_below="@+id/totalprice"
android:textColor="#FFFFFF"
android:layout_marginTop="16dp"
android:padding="16dp"/>
</RelativeLayout>
*/
