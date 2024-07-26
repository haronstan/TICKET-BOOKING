package com.example.ticketbooking.model;

import com.google.gson.annotations.SerializedName;

public class STKPush {

    // SerializedName annotation maps the JSON key "BusinessShortCode" to the businessShortCode field
    @SerializedName("BusinessShortCode")
    private String businessShortCode;

    // SerializedName annotation maps the JSON key "Password" to the password field
    @SerializedName("Password")
    private String password;

    // SerializedName annotation maps the JSON key "Timestamp" to the timestamp field
    @SerializedName("Timestamp")
    private String timestamp;

    // SerializedName annotation maps the JSON key "TransactionType" to the transactionType field
    @SerializedName("TransactionType")
    private String transactionType;

    // SerializedName annotation maps the JSON key "Amount" to the amount field
    @SerializedName("Amount")
    private Integer amount;

    // SerializedName annotation maps the JSON key "PartyA" to the partyA field
    @SerializedName("PartyA")
    private String partyA;

    // SerializedName annotation maps the JSON key "PartyB" to the partyB field
    @SerializedName("PartyB")
    private String partyB;

    // SerializedName annotation maps the JSON key "PhoneNumber" to the phoneNumber field
    @SerializedName("PhoneNumber")
    private String phoneNumber;

    // SerializedName annotation maps the JSON key "CallBackURL" to the callBackURL field
    @SerializedName("CallBackURL")
    private String callBackURL;

    // SerializedName annotation maps the JSON key "AccountReference" to the accountReference field
    @SerializedName("AccountReference")
    private String accountReference;

    // SerializedName annotation maps the JSON key "TransactionDesc" to the transactionDesc field
    @SerializedName("TransactionDesc")
    private String transactionDesc;

    // Constructor to initialize all fields
    public STKPush(String businessShortCode, String password, String timestamp, String transactionType,
                   Integer amount, String partyA, String partyB, String phoneNumber, String callBackURL,
                   String accountReference, String transactionDesc) {
        this.businessShortCode = businessShortCode;
        this.password = password;
        this.timestamp = timestamp;
        this.transactionType = transactionType;
        this.amount = amount;
        this.partyA = partyA;
        this.partyB = partyB;
        this.phoneNumber = phoneNumber;
        this.callBackURL = callBackURL;
        this.accountReference = accountReference;
        this.transactionDesc = transactionDesc;
    }
}
