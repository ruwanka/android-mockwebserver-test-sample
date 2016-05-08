package com.example.ruwa.mockservertest.service;

import com.example.ruwa.mockservertest.model.ResponseDetails;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/test")
    Call<ResponseDetails> get();

}
