package com.example.ruwa.mockservertest.model;

import com.google.gson.annotations.SerializedName;

public class ResponseDetails {

    @SerializedName("response")
    String response;

    @SerializedName("status")
    int status;

    public ResponseDetails(){

    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
