package com.example.ticketbooking.services;

import com.example.ticketbooking.model.accesstoken;
import com.example.ticketbooking.model.stkpush;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface STKPushService {
    @POST("mpesa/stkpush/v1/processrequest")
    Call<stkpush> sendPush(@Body stkpush stkPush);

    @GET("oauth/v1/generate?grant_type=client_credentials")
    Call<accesstoken> getAccessToken();
}