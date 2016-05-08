package com.example.ruwa.mockservertest.presenter;

import com.example.ruwa.mockservertest.model.ResponseDetails;
import com.example.ruwa.mockservertest.service.ApiService;
import com.example.ruwa.mockservertest.service.TestService;
import com.example.ruwa.mockservertest.view.MainView;

public class MainActivityPresenter implements MainPresenter {

    private ApiService apiService;

    private MainView view;

    TestService service;

    public MainActivityPresenter(MainView v){
        this.view = v;
        service = new TestService();
    }

    @Override
    public void callService() {
        view.showProgressBar(true);
        service.get(new TestService.ServiceCallback<ResponseDetails>() {
            @Override
            public void onResponse(ResponseDetails responseDetails, int statusCode) {
                view.showResponse(responseDetails.getResponse(), statusCode);
                view.showProgressBar(false);
            }

            @Override
            public void onFailure(String message) {
                view.showProgressBar(false);
            }
        });
    }
}
