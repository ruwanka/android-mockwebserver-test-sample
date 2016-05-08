package com.example.ruwa.mockservertest.service;

import com.example.ruwa.mockservertest.model.ResponseDetails;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class TestService {

    public interface ServiceCallback<T> {
        void onResponse(T model, int statusCode);
        void onFailure(String message);
    }

    private final ApiService apiService;

    public TestService(){
        apiService = ServiceFactory.create(ApiService.class);
    }

    public void get(final ServiceCallback<ResponseDetails> callback){
        apiService.get().enqueue(new Callback<ResponseDetails>() {
            @Override
            public void onResponse(Call<ResponseDetails> call, Response<ResponseDetails> response) {
                if(response.isSuccessful()){
                    callback.onResponse(response.body(), response.code());
                }else{
                    Converter<ResponseBody, ResponseDetails> converter =
                            ServiceFactory.getRetrofit().responseBodyConverter(ResponseDetails.class, new Annotation[0]);
                    try {
                        callback.onResponse(converter.convert(response.errorBody()), response.code());
                    } catch (IOException e) {
                        callback.onFailure(e.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseDetails> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

}
