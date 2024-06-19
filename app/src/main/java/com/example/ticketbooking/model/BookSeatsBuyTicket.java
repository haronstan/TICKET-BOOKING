package com.example.ticketbooking.model;

// Class to manage booking details for tickets
public class BookSeatsBuyTicket {

    // Private variables to store ticket and user information
    private boolean reserved;
    private String userEmail;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private int remainingSeats;

    // Constructor to initialize remaining seats
    public BookSeatsBuyTicket(int remainingSeats) {
        this.remainingSeats = remainingSeats;
    }

    // Constructor to initialize all user and reservation details
    public BookSeatsBuyTicket(boolean reserved, String userEmail, String firstName, String lastName, int phoneNumber) {
        this.reserved = reserved;
        this.userEmail = userEmail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    // Getter method to check if the seat is reserved
    public boolean isReserved() {
        return reserved;
    }

    // Setter method to set the reservation status
    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    // Getter method for user email
    public String getUserEmail() {
        return userEmail;
    }

    // Setter method for user email
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    // Getter method for user's first name
    public String getFirstName() {
        return firstName;
    }

    // Setter method for user's first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter method for user's last name
    public String getLastName() {
        return lastName;
    }

    // Setter method for user's last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter method for user's phone number
    public int getPhoneNumber() {
        return phoneNumber;
    }

    // Setter method for user's phone number
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter method for remaining seats
    public int getRemainingSeats() {
        return remainingSeats;
    }

    // Setter method for remaining seats
    public void setRemainingSeats(int remainingSeats) {
        this.remainingSeats = remainingSeats;
    }
}
