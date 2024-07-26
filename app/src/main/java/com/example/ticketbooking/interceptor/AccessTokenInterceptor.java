package com.example.ticketbooking.interceptor;

import android.util.Base64;

import androidx.annotation.NonNull;

import com.example.ticketbooking.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AccessTokenInterceptor implements Interceptor {

        // Constructor for AccessTokenInterceptor
    public AccessTokenInterceptor() {
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        // Combine CONSUMER_KEY and CONSUMER_SECRET from BuildConfig
        String keys = BuildConfig.CONSUMER_KEY + ":" + BuildConfig.CONSUMER_SECRET;

        // Create a new request with the "Authorization" header
        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "Basic " + Base64.encodeToString(keys.getBytes(), Base64.NO_WRAP))
                .build();

        // Proceed with the request
        return chain.proceed(request);
    }
}
