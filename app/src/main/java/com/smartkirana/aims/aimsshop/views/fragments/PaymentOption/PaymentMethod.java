package com.smartkirana.aims.aimsshop.views.fragments.PaymentOption;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.views.activities.base.BaseFragment;

public class PaymentMethod extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_payment_method, parent, false);
        return view;
    }
}
