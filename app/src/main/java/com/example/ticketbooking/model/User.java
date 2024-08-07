package com.example.ticketbooking.model;

public class User {
    // Member variables to store user information
    String email;
    String yourName;
    int phoneNumber;
    String userid;

    // Constructor to initialize user details
    public User(String email, String firstName, int phoneNumber, String userid) {
        this.email = email;
        this.yourName = yourName;
        this.phoneNumber = phoneNumber;
        this.userid = userid;
    }

    // Getter method for user's email
    public String getEmail() {
        return email;
    }

    // Setter method for user's email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter method for user's first name
    public String getFirstName() {
        return yourName;
    }

    // Setter method for user's first name
    public void setFirstName(String firstName) {
        this.yourName = firstName;
    }

    // Getter method for user's phone number
    public int getPhoneNumber() {
        return phoneNumber;
    }

    // Setter method for user's phone number
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter method for user's unique identifier
    public String getUserid() {
        return userid;
    }

    // Setter method for user's unique identifier
    public void setUserid(String userid) {
        this.userid = userid;
    }
}
