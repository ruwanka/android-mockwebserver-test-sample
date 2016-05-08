package com.example.ruwa.mockservertest.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ruwa.mockservertest.presenter.MainActivityPresenter;
import com.example.ruwa.mockservertest.presenter.MainPresenter;
import com.example.ruwa.mockservertest.R;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener{

    TextView txtResponse;

    TextView txtStatusCode;

    Button btnGet;

    MainPresenter mainPresenter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mainPresenter = new MainActivityPresenter(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        showProgressBar(false);
    }

    private void initViews() {
        txtResponse = (TextView) findViewById(R.id.txtResponse);
        txtStatusCode = (TextView) findViewById(R.id.txtStatusCode);
        ((Button) findViewById(R.id.btnGet)).setOnClickListener(this);
    }

    @Override
    public void showResponse(String response, int statusCode) {
        txtResponse.setText(response);
        //txtStatusCode.setText(statusCode);
    }

    @Override
    public void showProgressBar(boolean show) {
        if(show){
            progressDialog = ProgressDialog.show(this, null, "Loading...", false, false);
        }else if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnGet:
                mainPresenter.callService();
                break;
        }
    }
}
