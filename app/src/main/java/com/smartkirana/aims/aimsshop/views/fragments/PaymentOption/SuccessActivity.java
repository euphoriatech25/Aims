package com.smartkirana.aims.aimsshop.views.fragments.PaymentOption;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.smartkirana.aims.aimsshop.R;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell_pay);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        TextView textView = findViewById(R.id.successDetails);
//        String responseData = getIntent().getStringExtra("refID") + " - " + getIntent().getStringExtra("amount") + " - " + getIntent().getStringExtra("invoice") + " - " + getIntent().getStringExtra("status");
//        textView.setText(responseData);
//
//        String failureResponse = "Error Message: " + getIntent().getStringExtra("On_Failure");
//        textView.setText(failureResponse);
    }

}
