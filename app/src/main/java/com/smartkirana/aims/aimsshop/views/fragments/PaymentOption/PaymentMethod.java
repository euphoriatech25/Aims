package com.smartkirana.aims.aimsshop.views.fragments.PaymentOption;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.views.activities.base.BaseFragment;

public class PaymentMethod extends BaseFragment {
    RadioGroup radioPaymentGateWay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_payment_method, parent, false);
        radioPaymentGateWay = (RadioGroup) view.findViewById(R.id.radioPaymentGateWay);

        radioPaymentGateWay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.radioCellPay) {
                    PayByCellPay();
                } else if (checkedId == R.id.radioCOD) {
                    Toast.makeText(getContext().getApplicationContext(), "Cash on Delivery",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
        return view;
    }

    private void PayByCellPay() {

    }


}
