package com.smartkirana.aims.aimsshop.views.fragments.PaymentOption;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cellcom.cellpay_sdk.api.Config;
import com.cellcom.cellpay_sdk.api.OnCheckOutListener;
import com.cellcom.cellpay_sdk.helper.CellpayCheckOut;
import com.cellcom.cellpay_sdk.widget.CellpayButton;
import com.smartkirana.aims.aimsshop.R;

import java.util.HashMap;

public class CellPayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell_pay);

        HashMap<String, String> map = new HashMap<>();
        map.put("merchant_extra", "This is extra data");
        map.put("merchant_extra_2", "This is extra data 2");

        Config config = new Config("cellpayTestKey", "9801977888", "11234", "SASTODEAL PAYMENT", 1000L, map, new OnCheckOutListener() {

            @Override
            public void onSuccess(HashMap<String, Object> data) {
                Log.i("Payment confirmed", data + "");

                String refId = data.get("cellpay_ref_id").toString();
                String invoice = data.get("merchant_invoice_number").toString();
                String status = data.get("cellpay_transaction_status").toString();
                String amount = data.get("merchant_amount").toString();

                Intent intent = new Intent(CellPayActivity.this, SuccessActivity.class);

                intent.putExtra("invoice", invoice);
                intent.putExtra("status", status);
                intent.putExtra("amount", amount);
                intent.putExtra("refID", refId);
                startActivity(intent);
            }

            @Override
            public void onError(String action, String message) {
                Log.i(action, message);

                String errorMessage = message;
                Intent intent = new Intent(CellPayActivity.this, SuccessActivity.class);
                intent.putExtra("On_Failure", errorMessage);
                startActivity(intent);
            }
        });

        CellpayCheckOut cellpayCheckOut = new CellpayCheckOut(this, config);

        CellpayButton cellpayButton = (CellpayButton) findViewById(R.id.cellpay_button);
        cellpayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cellpayCheckOut.show();
            }
        });
    }
}
