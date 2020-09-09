package com.smartkirana.aims.aimsshop.views.activities.Cart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smartkirana.aims.aimsshop.Conn;
import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.utils.AppUtils;
import com.smartkirana.aims.aimsshop.utils.Constants;
import com.smartkirana.aims.aimsshop.utils.PrefConstants;
import com.smartkirana.aims.aimsshop.views.activities.CheckOut.CheckoutActivity;
import com.smartkirana.aims.aimsshop.views.activities.Home.HomeActivity;
import com.smartkirana.aims.aimsshop.views.activities.Login.LoginActivity;
import com.smartkirana.aims.aimsshop.views.activities.ProductDetails.ProductDetails;
import com.smartkirana.aims.aimsshop.views.activities.SearchActivity.SearchProductActivity;
import com.smartkirana.aims.aimsshop.views.activities.WishList.WishListActivity;
import com.smartkirana.aims.aimsshop.views.activities.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends BaseActivity implements ICart.View, View.OnClickListener {
    LinearLayout linearLayout, linearlayoutTotal, ifEmpty;
    ProgressBar progressBar;
    String name;
    private CartPresenterImpl presenter;
    private String api_token;
    private Button continue_shopping, checkout, continue_shopping_empty;
    int productQuantity = 0;
    List<CartModel.Product> selectedProducts = new ArrayList<CartModel.Product>();
    boolean isOpen = false;
    private TextView totalDetails;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        linearlayoutTotal = findViewById(R.id.linearlayoutTotal);
        progressBar = findViewById(R.id.progressBar);
        linearLayout = findViewById(R.id.linearLayoutCart);
        continue_shopping = findViewById(R.id.continue_shopping);
        continue_shopping_empty = findViewById(R.id.continue_shopping_empty);
        checkout = findViewById(R.id.checkout);
        totalDetails = findViewById(R.id.totalDetails);
        ifEmpty = findViewById(R.id.ifEmpty);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        SharedPreferences prefs = getSharedPreferences(PrefConstants.USER_DETAILS_PREF, MODE_PRIVATE);
        api_token = prefs.getString(PrefConstants.API_TOKEN, PrefConstants.DEFAULT_VALUE);

        presenter = new CartPresenterImpl(this, new CartControllerImpl());
        presenter.getCartProductList(api_token);
        continue_shopping.setOnClickListener(this);
        checkout.setOnClickListener(this);
        init();
    }

    private void init() {
        changeStatusBarColor();
        setUpToolbar("My Cart", true);
        AppUtils.freezeUi(this, true);
    }
    private void changeStatusBarColor() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.account));

    }

    @Override
    public void onSuccess(final CartModel cartModel) {
        AppUtils.freezeUi(this, false);
        List<CartModel.Total> totals = cartModel.getTotals();

        for (int i = 0; i < totals.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.cart_total, linearlayoutTotal, false);
            TextView total_title = view.findViewById(R.id.total_title);
            total_title.setText(totals.get(i).getTitle() + " : ");
            TextView total_details = view.findViewById(R.id.total_details);
            total_details.setText(totals.get(i).getText());
            linearlayoutTotal.addView(view);
        }

        for (int i = 0; i < cartModel.getProducts().size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.cart_items, linearLayout, false);
            name = cartModel.getProducts().get(i).getName();
            ImageView product_image = view.findViewById(R.id.product_image);
            TextView product_name = view.findViewById(R.id.product_name);
            TextView product_unit_price = view.findViewById(R.id.product_price);
            TextView total_price = view.findViewById(R.id.total_price);
            TextView product_avaibility = view.findViewById(R.id.product_avaibility);
            ImageButton removeCart = view.findViewById(R.id.removeCart);
            ImageButton quantity_update = view.findViewById(R.id.quantity_update);
            ImageButton decrease_quantity = view.findViewById(R.id.decrease_quantity);
            ImageButton increase_quantity = view.findViewById(R.id.increase_quantity);
            TextView product_model = view.findViewById(R.id.product_model);
            EditText product_quantity = view.findViewById(R.id.product_quantity);
            String name = cartModel.getProducts().get(i).getName();

            CheckBox selectProduct = view.findViewById(R.id.selectProduct);
            List<CartModel.Product> product = cartModel.getProducts();
            int finalI = i;

            selectProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedProducts.add(product.get(finalI));
                }
            });

//            Initialization of Value
            product_name.setText(cartModel.getProducts().get(i).getName());
            product_unit_price.setText(cartModel.getProducts().get(i).getPrice());
            total_price.setText(cartModel.getProducts().get(i).getTotal());
            product_model.setText(cartModel.getProducts().get(i).getModel());
            if (cartModel.getProducts().get(i).getStock()) {
                product_avaibility.setText("In Stock");
            } else {
                product_avaibility.setText("Not In Stock");
            }
            Glide.with(this).load(cartModel.getProducts().get(i).getImage()).into(product_image);

            String cart_id = cartModel.getProducts().get(i).getCartId();

            removeCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.removeCartProduct(cart_id, api_token);
                }
            });

            String product_id = cartModel.getProducts().get(i).getProductId();
            product_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CartActivity.this, ProductDetails.class);
                    intent.putExtra(Constants.PRODUCT_NAME, name);
                    intent.putExtra(Constants.PRODUCT_ID, product_id);
                    startActivity(intent);
                    finish();
                }
            });


            product_quantity.setText(cartModel.getProducts().get(i).getQuantity());
            increase_quantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productQuantity = Integer.parseInt(product_quantity.getText().toString()) + 1;
                    product_quantity.setText(String.valueOf(productQuantity));
                }
            });
            decrease_quantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (productQuantity > 1) {
                        productQuantity = Integer.parseInt(product_quantity.getText().toString()) - 1;
                        product_quantity.setText(String.valueOf(productQuantity));
                    }
                }
            });

            quantity_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.editCartProduct(cart_id, product_quantity.getText().toString(), api_token);
                }
            });

            linearLayout.addView(view);

        }
    }

    @Override
    public void showProgressBar(boolean showpBar) {
        AppUtils.showProgressBar(showpBar, progressBar);
    }

    @Override
    public void showStub(boolean show) {
        if (show) {
            AppUtils.freezeUi(this, false);
            totalDetails.setVisibility(View.GONE);
            bottomNavigationView.setVisibility(View.GONE);
            ifEmpty.setVisibility(View.VISIBLE);
            continue_shopping_empty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(CartActivity.this, HomeActivity.class));
                    finish();
                }
            });

        }

    }

    @Override
    public void onFailure(@org.jetbrains.annotations.Nullable String message) {
        AppUtils.freezeUi(this, false);
        AppUtils.showToast(this, message);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_cart);
        item.setVisible(false);
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, HomeActivity.class));
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        int id = item.getItemId();
        if (id == R.id.action_search) {
            startActivity(new Intent(CartActivity.this, SearchProductActivity.class));
            finish();
            return true;
        }
        if (id == R.id.action_favorite) {
            startActivity(new Intent(CartActivity.this, WishListActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccessEditRemove(@Nullable String message) {
        AppUtils.freezeUi(this, false);
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        AppUtils.showToast(this, message);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.continue_shopping:
                startActivity(new Intent(this, HomeActivity.class));
                finish();
                break;

            case R.id.checkout:
                if (selectedProducts.size() != 0) {
                    if (!api_token.equalsIgnoreCase("No Api Token Found")) {
                        Conn.selectedProduct.put(Constants.SELECTED_PRODUCT, selectedProducts);
                        startActivity(new Intent(this, CheckoutActivity.class));
                    } else {
                        AppUtils.showToast(this, "Please Login/Sign U first ");
                        startActivity(new Intent(this, LoginActivity.class));
                    }
                    finish();
                } else {
                    Toast.makeText(this, "Please select product", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
