package com.example.ticketbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccessToken {

    // SerializedName annotation maps the JSON key "access_token" to the accessToken field
    @SerializedName("access_token")
    @Expose
    public String accessToken;

    // SerializedName annotation maps the JSON key "expires_in" to the expiresIn field
    @SerializedName("expires_in")
    @Expose
    private String expiresIn;

    // Constructor to initialize the accessToken and expiresIn fields
    public AccessToken(String accessToken, String expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }
}
