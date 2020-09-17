package com.smartkirana.aims.aimsshop.views.activities.CheckOut;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.GoogleMap;
import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.views.activities.Cart.CartActivity;
import com.smartkirana.aims.aimsshop.views.activities.base.BaseActivity;
import com.smartkirana.aims.aimsshop.views.fragments.PaymentOption.PaymentMethod;
import com.smartkirana.aims.aimsshop.views.fragments.billingAddress.BillingAddressFragment;

public class CheckoutActivity extends BaseActivity implements View.OnClickListener{
//        , OnMapReadyCallback {

    LinearLayout billing_details, sub_billinglayout, delivery_details, new_address_layout, payment_method, payment_method_layout;
    boolean isOpen = false;
    private GoogleMap mMap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        setUpToolbar("My Checkout", true);
        billing_details = findViewById(R.id.billing_details);
        delivery_details = findViewById(R.id.delivery_details);
        sub_billinglayout = findViewById(R.id.sub_billinglayout);
        payment_method = findViewById(R.id.payment_method);
        payment_method_layout = findViewById(R.id.payment_method_layout);
        billing_details.setOnClickListener(this);
        payment_method.setOnClickListener(this);
        delivery_details.setOnClickListener(this);



//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);




        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = new BillingAddressFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.billingAddress, fragment).addToBackStack(null);
        transaction.commit();

        FragmentManager manager1 = getSupportFragmentManager();
        Fragment fragment1 = new BillingAddressFragment();
        FragmentTransaction transaction1 = manager1.beginTransaction();
        transaction1.replace(R.id.deliveryAddress, fragment1).addToBackStack(null);
        transaction1.commit();

       FragmentManager manager2 = getSupportFragmentManager();
        Fragment fragment2 = new PaymentMethod();
        FragmentTransaction transaction2 = manager2.beginTransaction();
        transaction2.replace(R.id.paymentMethod, fragment2).addToBackStack(null);
        transaction2.commit();

        changeStatusBarColor();
    }

    private void changeStatusBarColor() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.account));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, CartActivity.class));
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.billing_details:
//                if (isOpen) {
//
//                } else {
//                    sub_billinglayout.setVisibility(View.GONE);
//                    isOpen = true;
//                }
//                break;
//
//            case R.id.delivery_details:
//                if (isOpen) {
//                    sub_billinglayout.setVisibility(View.VISIBLE);
//                    isOpen = false;
//                } else {
//                    sub_billinglayout.setVisibility(View.GONE);
//                    isOpen = true;
//                }
//                break;
//            case R.id.payment_method:
//                if (isOpen) {
//                    payment_method_layout.setVisibility(View.VISIBLE);
//                    isOpen = false;
//                } else {
//                    payment_method_layout.setVisibility(View.GONE);
//                    isOpen = true;
//                }
//                break;

        }

    }

}
