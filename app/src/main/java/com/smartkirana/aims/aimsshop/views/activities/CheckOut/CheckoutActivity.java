package com.smartkirana.aims.aimsshop.views.activities.CheckOut;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.cellcom.cellpay_sdk.api.Config;
import com.cellcom.cellpay_sdk.api.OnCheckOutListener;
import com.cellcom.cellpay_sdk.helper.CellpayCheckOut;
import com.cellcom.cellpay_sdk.widget.CellpayButton;
import com.google.android.gms.maps.GoogleMap;
import com.smartkirana.aims.aimsshop.Conn;
import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.utils.AppUtils;
import com.smartkirana.aims.aimsshop.utils.Constants;
import com.smartkirana.aims.aimsshop.utils.PrefConstants;
import com.smartkirana.aims.aimsshop.views.activities.Cart.CartActivity;
import com.smartkirana.aims.aimsshop.views.activities.Cart.CartModel;
import com.smartkirana.aims.aimsshop.views.activities.Home.HomeActivity;
import com.smartkirana.aims.aimsshop.views.activities.base.BaseActivity;
import com.smartkirana.aims.aimsshop.views.fragments.PaymentOption.PaymentMethod;
import com.smartkirana.aims.aimsshop.views.fragments.ShippingDetails.ShippingFragment;
import com.smartkirana.aims.aimsshop.views.fragments.billingAddress.BillingAddressFragment;
import com.swifttechnology.imepaysdk.ENVIRONMENT;
import com.swifttechnology.imepaysdk.IMEPayment;
import com.swifttechnology.imepaysdk.IMEPaymentCallback;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckoutActivity extends BaseActivity implements  ICheckout.View,View.OnClickListener {
    //        , OnMapReadyCallback {
    CardView billing_details, delivery_details,shipping_details;
    LinearLayout  payment_method, payment_method_layout;
    boolean isOpen = false;
    private GoogleMap mMap;
    LinearLayout selectedProductsLayout;
    List<CartModel.Product> selectedProducts = new ArrayList<CartModel.Product>();
    FrameLayout billingAddress,deliveryAddress,shippingDetails;
    Button confirm_button,payIMEPay;
    SharedPreferences prefs;
    String api_token,customer_id;
    private CheckoutPresenterImpl presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        setUpToolbar("My Checkout", true);
        billing_details = findViewById(R.id.billing_details);
        deliveryAddress=findViewById(R.id.deliveryAddress);
        shippingDetails=findViewById(R.id.shippingDetails);
        delivery_details = findViewById(R.id.delivery_details);
        payment_method = findViewById(R.id.payment_method);
        shipping_details = findViewById(R.id.shipping_details);
        payment_method_layout = findViewById(R.id.payment_method_layout);
        selectedProductsLayout = findViewById(R.id.selectedProducts);
        billingAddress = findViewById(R.id.billingAddress);
        confirm_button=findViewById(R.id.confirm_button);
        payIMEPay=findViewById(R.id.payByIMEPay);
        payIMEPay.setOnClickListener(this);
        billing_details.setOnClickListener(this);
        payment_method.setOnClickListener(this);
        delivery_details.setOnClickListener(this);
        shipping_details.setOnClickListener(this);
        confirm_button.setOnClickListener(this);
        prefs = getSharedPreferences(PrefConstants.USER_DETAILS_PREF, MODE_PRIVATE);
        api_token = prefs.getString(PrefConstants.API_TOKEN, PrefConstants.DEFAULT_VALUE);
        customer_id = prefs.getString(PrefConstants.CUSTOMER_ID, PrefConstants.DEFAULT_VALUE);
        presenter = new CheckoutPresenterImpl(this, new CheckoutControllerImpl());

        selectedProducts = Conn.selectedProduct.get(Constants.SELECTED_PRODUCT);
        if (selectedProducts != null) {
            for (int i = 0; i < selectedProducts.size(); i++) {
                View view = LayoutInflater.from(this).inflate(R.layout.selected_products, selectedProductsLayout, false);
                TextView product_name = view.findViewById(R.id.product_name);
                TextView product_model = view.findViewById(R.id.product_model);
                TextView product_quantity = view.findViewById(R.id.product_quantity);
                TextView unit_price = view.findViewById(R.id.unit_price);
                TextView total_price = view.findViewById(R.id.total_price);
                product_name.setText(selectedProducts.get(i).getName());
                product_model.setText(selectedProducts.get(i).getModel());
                product_quantity.setText(selectedProducts.get(i).getQuantity());
                unit_price.setText(selectedProducts.get(i).getPrice());
                total_price.setText(selectedProducts.get(i).getTotal());
                product_model.setText(selectedProducts.get(i).getModel());
                selectedProductsLayout.addView(view);
            }
        } else {
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
        }

        FragmentManager manager2 = getSupportFragmentManager();
        Fragment fragment2 = new PaymentMethod();
        FragmentTransaction transaction2 = manager2.beginTransaction();
        transaction2.replace(R.id.paymentMethod, fragment2).addToBackStack(null);
        transaction2.commit();
        payByCellPay();
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
            case R.id.billing_details:
                if (isOpen) {
                    billingAddress.setVisibility(View.GONE);
                    isOpen = false;
                } else {
                    FragmentManager manager = getSupportFragmentManager();
                    Fragment fragment = new BillingAddressFragment();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.billingAddress, fragment).addToBackStack(null);
                    transaction.commit();
                    billingAddress.setVisibility(View.VISIBLE);
                    isOpen = true;
                }
                break;
            case R.id.delivery_details:
                if (isOpen) {
                     deliveryAddress.setVisibility(View.GONE);
                     isOpen = false;
                } else {
                    FragmentManager manager1 = getSupportFragmentManager();
                    Fragment fragment1 = new BillingAddressFragment();
                    FragmentTransaction transaction1 = manager1.beginTransaction();
                    transaction1.replace(R.id.deliveryAddress, fragment1).addToBackStack(null);
                    transaction1.commit();
                    deliveryAddress.setVisibility(View.VISIBLE);
                    isOpen = true;
                }
                break;
            case R.id.shipping_details:
                if (isOpen) {
                    shippingDetails.setVisibility(View.GONE);
                    isOpen = false;
                } else {
                    FragmentManager manager3 = getSupportFragmentManager();
                    Fragment fragment3 = new ShippingFragment();
                    FragmentTransaction transaction3 = manager3.beginTransaction();
                    transaction3.replace(R.id.shippingDetails, fragment3).addToBackStack(null);
                    transaction3.commit();
                    shippingDetails.setVisibility(View.VISIBLE);
                    isOpen = true;
                }
                break;
            case R.id.payByIMEPay:
                payByIMEPay();
                break;
            case R.id.confirm_button:
                AppUtils.freezeUi(this,true);
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
                presenter.orderProducts(api_token,customer_id,"cod","flat.flat","30","30","","");
                break;
        }
    }

    private void payByIMEPay() {
        String merchantCode = "AIMSSHOP";
        String merchantName = "Aims Shop";
        String module = "AIMSSHOP";
        String username = "aimsshop";
        String password = "ime@1234";
        String reference_value = "31";
        String amount = "200.00";
        String merchant_transaction_recording_url = "http://aimsshop.net/index.php?route=api/aims/imepay/setTransaction&customer_id="+customer_id;

        IMEPayment imePayment = new IMEPayment(this, ENVIRONMENT.TEST);

        imePayment.performPayment(merchantCode,
                merchantName,
                merchant_transaction_recording_url,
                amount,
                reference_value,
                module,
                username,
                password,
                new IMEPaymentCallback() {
                    @Override
                    public void onSuccess(int responseCode,String responseDescription, String transactionId, String msisdn, String amount, String refId) {
                        //Handle Response Code
                        Toast.makeText(CheckoutActivity.this, String.valueOf(responseCode), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void payByCellPay() {
        HashMap<String, String> map = new HashMap<>();
        map.put("merchant_extra", "This is extra data");
        map.put("merchant_extra_2", "This is extra data 2");
          long amount=150000;
        Config config = new Config("cellpayTestKey", "9801977888", "11234", "AIMS SHOP PAYMENT", amount, map, new OnCheckOutListener() {

            @Override
            public void onSuccess(HashMap<String, Object> data) {
                Log.i("Payment confirmed", data + "");
                String refId = data.get("cellpay_ref_id").toString();
                String invoice = data.get("merchant_invoice_number").toString();
                String status = data.get("cellpay_transaction_status").toString();
                String amount = data.get("merchant_amount").toString();
//                Intent intent = new Intent(CellPayActivity.this, SuccessActivity.class);
//                intent.putExtra("invoice", invoice);
//                intent.putExtra("status", status);
//                intent.putExtra("amount", amount);
//                intent.putExtra("refID", refId);
//                startActivity(intent);
            }

            @Override
            public void onError(String action, String message) {
                Log.i(action, message);
                String errorMessage = message;
//                Intent intent = new Intent(CellPayActivity.this, SuccessActivity.class);
//                intent.putExtra("On_Failure", errorMessage);
//                startActivity(intent);
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

    @Override
    public void onSuccess() {
        AppUtils.freezeUi(this,false);
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.delivery_success);
        ImageView delivery_image = dialog.findViewById(R.id.delivery_details);
        Glide.with(this).load(R.drawable.delivery).into(delivery_image);
        Button continueButton=dialog.findViewById(R.id.continueBtn);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CheckoutActivity.this, HomeActivity.class));
                finish();
            }
        });
        dialog.show();
    }

    @Override
    public void onFailure(@NotNull String message) {

    }

    @Override
    public void showStub(boolean show) {

    }
}
