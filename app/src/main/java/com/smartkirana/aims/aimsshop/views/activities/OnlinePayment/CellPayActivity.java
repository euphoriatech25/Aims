package com.smartkirana.aims.aimsshop.views.activities.OnlinePayment;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cellcom.cellpay_sdk.api.Config;
import com.cellcom.cellpay_sdk.api.OnCheckOutListener;
import com.cellcom.cellpay_sdk.widget.CellpayButton;
import com.smartkirana.aims.aimsshop.R;

import java.util.HashMap;

public class CellPayActivity extends AppCompatActivity {
    CellpayButton cellpayButton;
    long amount = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell_pay);
        cellpayButton = findViewById(R.id.cellpay_button);
        HashMap<String, String> map = new HashMap<>();
        map.put("merchant_extra", "This is extra data");
        map.put("merchant_extra_2", "This is extra data 2");

        Config config = new Config("publicKey", "merchantId", "invoiceNumber", "description", amount, map, new OnCheckOutListener() {
            @Override
            public void onSuccess(HashMap<String, Object> data) {
                Log.i("Payment confirmed", data + "");
                Toast.makeText(CellPayActivity.this, data + "", Toast.LENGTH_SHORT).show();
                String refId = data.get("cellpay_ref_id").toString();
                String invoice = data.get("merchant_invoice_number").toString();
                String status = data.get("cellpay_transaction_status").toString();
                String amount = data.get("merchant_amount").toString();
            }
            @Override
            public void onError(String action, String message) {
                Log.i(action, message);
                String errorMessage = message;
            }
        });
        cellpayButton.setCheckOutConfig(config);
    }

}
