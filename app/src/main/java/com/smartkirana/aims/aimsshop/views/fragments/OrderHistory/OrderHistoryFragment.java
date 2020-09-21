package com.smartkirana.aims.aimsshop.views.fragments.OrderHistory;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.utils.PrefConstants;
import com.smartkirana.aims.aimsshop.views.activities.ReturnProduct.ReturnProducts;
import com.smartkirana.aims.aimsshop.views.activities.base.BaseFragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static android.content.Context.MODE_PRIVATE;

public class OrderHistoryFragment extends BaseFragment implements IOrderHistory.View, View.OnClickListener {
    OrderHistoryPresenterImpl presenter;
    String api_token;
    ImageButton viewOrderDetail,viewOrderLocation;
    OrderHistoryModel.Order orderHistoryDetails;
    TextView cart_id, customer_name, no_of_products, status, total_price, product_date_added;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_history, parent, false);
        SharedPreferences prefs = getActivity().getSharedPreferences(PrefConstants.USER_DETAILS_PREF, MODE_PRIVATE);
        api_token = prefs.getString(PrefConstants.API_TOKEN, PrefConstants.DEFAULT_VALUE);
        presenter = new OrderHistoryPresenterImpl(this, new OrderHistoryControllerImpl());
        presenter.getOrderHistory(api_token, "1");
        viewOrderDetail = view.findViewById(R.id.viewOrderDetail);
        viewOrderLocation=view.findViewById(R.id.viewOrderLocation);
        cart_id = view.findViewById(R.id.cart_id);
        customer_name = view.findViewById(R.id.customer_name);
        no_of_products = view.findViewById(R.id.no_of_products);
        status = view.findViewById(R.id.status);
        total_price = view.findViewById(R.id.total_price);
        product_date_added = view.findViewById(R.id.product_date_added);
        viewOrderLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getContext(), DeliveryLocationActivity.class));

            }
        });
        return view;
    }

    @Override
    public void onSuccess(@NotNull OrderHistoryModel orderHistoryModel) {
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.cart_total, orderHistory, false);
        orderHistoryDetails = orderHistoryModel.getOrder();



        cart_id.setText(orderHistoryDetails.getOrderId());
        customer_name.setText(orderHistoryDetails.getFirstname() + " " + orderHistoryDetails.getLastname());
        no_of_products.setText(orderHistoryDetails.getOrderId());
        status.setText(orderHistoryDetails.getOrderStatusId());
        total_price.setText(orderHistoryDetails.getTotal());
        product_date_added.setText(orderHistoryDetails.getDateModified());
        viewOrderDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void getOrderHistoryInfo() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.order_history_details, null);
        TextView order_id,product_date_added,payment_method,shipping_method,payment_address,shipping_address;
        ImageButton return_product;
        order_id=dialogView.findViewById(R.id.order_id);
        product_date_added=dialogView.findViewById(R.id.product_date_added);
        payment_method=dialogView.findViewById(R.id.payment_method);
        shipping_method=dialogView.findViewById(R.id.shipping_method);
        payment_address=dialogView.findViewById(R.id.payment_address);
        shipping_address=dialogView.findViewById(R.id.shipping_address);
        return_product=dialogView.findViewById(R.id.return_product);
        return_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getContext(), ReturnProducts.class));
            }
        });

        if(orderHistoryDetails!=null) {
            order_id.setText("Order ID       :"+" #"+orderHistoryDetails.getOrderId());
            product_date_added.setText("Date Added : " + orderHistoryDetails.getDateModified());
            payment_method.setText(orderHistoryDetails.getPaymentMethod());
            shipping_method.setText(orderHistoryDetails.getShippingMethod());
            payment_address.setText(orderHistoryDetails.getPaymentFirstname() + " " + orderHistoryDetails.getPaymentAddress1() + " " + orderHistoryDetails.getPaymentAddress2() + " " + orderHistoryDetails.getPaymentAddressFormat() + "" + orderHistoryDetails.getPaymentFirstname() + " " + orderHistoryDetails.getPaymentAddress1() + " " + orderHistoryDetails.getPaymentAddress2() + " " + orderHistoryDetails.getPaymentAddressFormat());
            shipping_address.setText(orderHistoryDetails.getPaymentFirstname() + " " + orderHistoryDetails.getPaymentAddress1() + " " + orderHistoryDetails.getPaymentAddress2() + " " + orderHistoryDetails.getPaymentAddressFormat() + "" + orderHistoryDetails.getPaymentFirstname() + " " + orderHistoryDetails.getPaymentAddress1() + " " + orderHistoryDetails.getPaymentAddress2() + " " + orderHistoryDetails.getPaymentAddressFormat());
        }


        dialogBuilder.setView(dialogView);
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void showStub(boolean show) {

    }

    @Override
    public void onFailure(@Nullable String message) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.viewOrderLocation:
                Toast.makeText(getContext(), "123456789", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
