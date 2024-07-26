package com.example.ticketbooking;

import android.util.Base64;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class Utils {
    // Method to get the current timestamp in the specified format
public static String getTimestamp() {
        return new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
        }

    // Method to sanitize phone numbers into the proper format
public static String sanitizePhoneNumber(String phone) {
        if (phone.equals("")) {
        return "";
        }

    // If the phone number starts with '0' and has less than 11 digits, replace '0' with '254'
        if (phone.length() < 11 & phone.startsWith("0")) {
        String p = phone.replaceFirst("^0", "254");
        return p;
        }

    // If the phone number starts with '+' and has 13 digits, remove the '+'
        if (phone.length() == 13 && phone.startsWith("+")) {
        String p = phone.replaceFirst("^+", "");
        return p;
        }

    // Return the phone number as is if it doesn't match the above conditions
        return phone;
        }

    // Method to generate a password using the business short code, passkey, and timestamp
public static String getPassword(String businessShortCode, String passkey, String timestamp) {
        String str = businessShortCode + passkey + timestamp;

    // Encode the password to Base64
        return Base64.encodeToString(str.getBytes(), Base64.NO_WRAP);
        }
        }
