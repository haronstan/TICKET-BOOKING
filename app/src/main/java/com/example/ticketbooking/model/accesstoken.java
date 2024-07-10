package com.example.ticketbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class accesstoken {
    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("expires_in")
    @Expose
    private String expiresIn;

    public accesstoken(String accessToken, String expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }
}
