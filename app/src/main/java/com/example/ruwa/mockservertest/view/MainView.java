package com.example.ruwa.mockservertest.view;

public interface MainView {

    void showResponse(String response, int statusCode);

    void showProgressBar(boolean show);
}
