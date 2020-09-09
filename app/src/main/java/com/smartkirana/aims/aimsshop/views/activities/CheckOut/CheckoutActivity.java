package com.smartkirana.aims.aimsshop.views.activities.CheckOut;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.smartkirana.aims.aimsshop.Conn;
import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.utils.Constants;
import com.smartkirana.aims.aimsshop.views.activities.Cart.CartActivity;
import com.smartkirana.aims.aimsshop.views.activities.Cart.CartModel;
import com.smartkirana.aims.aimsshop.views.activities.SearchActivity.SearchPresenterImpl;
import com.smartkirana.aims.aimsshop.views.activities.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends BaseActivity implements View.OnClickListener {
    //    TextView billing_details;
    LinearLayout billing_details, sub_billinglayout, delivery_details, new_address_layout, payment_method, payment_method_layout;
    boolean isOpen = false;
    RadioButton newAddressLayout;
    private SearchPresenterImpl presenter;
    List<CartModel.Product> selectedProducts = new ArrayList<CartModel.Product>();

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
        newAddressLayout = findViewById(R.id.newAddressLayout);
        new_address_layout = findViewById(R.id.new_address_layout);

        selectedProducts = Conn.selectedProduct.get(Constants.SELECTED_PRODUCT);

        if (selectedProducts.size() != 0) {
            setProductList(selectedProducts);
        }

        billing_details.setOnClickListener(this);
        payment_method.setOnClickListener(this);
        newAddressLayout.setOnClickListener(this);
        delivery_details.setOnClickListener(this);

        changeStatusBarColor();

    }

    private void setProductList(List<CartModel.Product> selectedProducts) {

        TableLayout heading = findViewById(R.id.productHeading);
        TableRow rowheading = new TableRow(this);
        rowheading.setBackgroundResource(R.drawable.edit_box);
        TableRow.LayoutParams lpHeading = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        rowheading.setLayoutParams(lpHeading);
        TextView product_name_heading = new TextView(this);
        TextView model_heading = new TextView(this);
        TextView quantity_heading = new TextView(this);
        TextView unit_price_heading = new TextView(this);
        TextView total_price_heading = new TextView(this);
        TextView productImage_heading = new TextView(this);

        productImage_heading.setTextSize(14);
        product_name_heading.setTextSize(14);
        model_heading.setTextSize(14);
        unit_price_heading.setTextSize(14);
        total_price_heading.setTextSize(14);
        quantity_heading.setTextSize(14);

        productImage_heading.setPadding(25, 15, 15, 5);
        quantity_heading.setPadding(35, 15, 15, 5);
        model_heading.setPadding(35, 15, 15, 5);
        quantity_heading.setPadding(35, 15, 15, 5);
        unit_price_heading.setPadding(25, 15, 15, 5);
        total_price_heading.setPadding(25, 15, 15, 5);

        product_name_heading.setTextColor(Color.parseColor("#FF7700"));
        productImage_heading.setTextColor(Color.parseColor("#FF7700"));
        model_heading.setTextColor(Color.parseColor("#FF7700"));
        unit_price_heading.setTextColor(Color.parseColor("#FF7700"));
        total_price_heading.setTextColor(Color.parseColor("#FF7700"));
        quantity_heading.setTextColor(Color.parseColor("#FF7700"));

        productImage_heading.setText("Image");
        product_name_heading.setText("Name");
        model_heading.setText("Model");
        unit_price_heading.setText("Price");
        total_price_heading.setText("Total");
        quantity_heading.setText("Quantity");
        rowheading.addView(productImage_heading);
        rowheading.addView(product_name_heading);
        rowheading.addView(model_heading);
        rowheading.addView(quantity_heading);
        rowheading.addView(unit_price_heading);
        rowheading.addView(total_price_heading);
        heading.addView(rowheading, 0);


        for (int i = 0; i < selectedProducts.size(); i++) {
            TableLayout ll = (TableLayout) findViewById(R.id.displayLinear);
            TableRow row = new TableRow(this);
            row.setBackgroundResource(R.drawable.edit_box);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            TextView product_name = new TextView(this);
            TextView model = new TextView(this);
            TextView quantity = new TextView(this);
            TextView unit_price = new TextView(this);
            TextView total_price = new TextView(this);
            ImageView productImage = new ImageView(this);

            product_name.setText(selectedProducts.get(i).getName());
            total_price.setText(selectedProducts.get(i).getModel());
            model.setText(selectedProducts.get(i).getQuantity());
            quantity.setText(selectedProducts.get(i).getPrice());
            unit_price.setText(selectedProducts.get(i).getTotal());
            Glide.with(this).load(selectedProducts.get(i).getImage()).into(productImage);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(120, 80);
            productImage.setLayoutParams(layoutParams);


            product_name.setTextSize(14);
            total_price.setTextSize(12);
            model.setTextSize(12);
            quantity.setTextSize(12);
            unit_price.setTextSize(12);
            total_price.setPadding(5, 15, 15, 5);
            unit_price.setPadding(5, 15, 15, 5);
            model.setPadding(5, 15, 15, 5);
            product_name.setPadding(10, 15, 15, 5);
            quantity.setPadding(5, 15, 15, 5);
            productImage.setPadding(5, 5, 5, 5);
            product_name.setTextColor(Color.parseColor("#23a1d1"));
            row.addView(productImage);
            row.addView(product_name);
            row.addView(total_price);
            row.addView(model);
            row.addView(quantity);
            row.addView(unit_price);

            ll.addView(row, i);
        }
    }

    private void changeStatusBarColor() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.account));

    }

    @Override
    public void showProgressBar(boolean showpBar) {

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        item.setVisible(false);
//        return true;
//    }

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
                    sub_billinglayout.setVisibility(View.VISIBLE);
                    isOpen = false;
                } else {
                    sub_billinglayout.setVisibility(View.GONE);
                    isOpen = true;
                }
                break;

            case R.id.delivery_details:
                if (isOpen) {
                    sub_billinglayout.setVisibility(View.VISIBLE);
                    isOpen = false;
                } else {
                    sub_billinglayout.setVisibility(View.GONE);
                    isOpen = true;
                }
                break;
            case R.id.payment_method:
                if (isOpen) {
                    payment_method_layout.setVisibility(View.VISIBLE);
                    isOpen = false;
                } else {
                    payment_method_layout.setVisibility(View.GONE);
                    isOpen = true;
                }
                break;

            case R.id.newAddressLayout:
                if (isOpen) {
                    new_address_layout.setVisibility(View.VISIBLE);
                    isOpen = false;
                } else {
                    new_address_layout.setVisibility(View.GONE);
                    isOpen = true;
                }
                break;

        }

    }
}
