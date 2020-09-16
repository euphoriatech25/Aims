package com.smartkirana.aims.aimsshop.views.activities.WishList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.utils.AppUtils;
import com.smartkirana.aims.aimsshop.utils.PrefConstants;
import com.smartkirana.aims.aimsshop.views.activities.Cart.CartActivity;
import com.smartkirana.aims.aimsshop.views.activities.Home.HomeActivity;
import com.smartkirana.aims.aimsshop.views.activities.SearchActivity.SearchProductActivity;
import com.smartkirana.aims.aimsshop.views.activities.base.BaseActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WishListActivity extends BaseActivity implements IWishList.View {
    WishListPresenterImpl presenter;
    View view;
    LinearLayout wishlist_layout;
    String api_token,customer_id;
    ProgressBar progressBar;
    private String product_id_public;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_wishlist);
        progressBar = findViewById(R.id.progressBar);
        setUpToolbar("My WishList", true);
        wishlist_layout = findViewById(R.id.wishlist_layout);
        presenter = new WishListPresenterImpl((IWishList.View) this, new WishListControllerImpl());
        SharedPreferences prefs = getSharedPreferences(PrefConstants.USER_DETAILS_PREF, MODE_PRIVATE);
        api_token = prefs.getString(PrefConstants.API_TOKEN, PrefConstants.DEFAULT_VALUE);
        customer_id = prefs.getString(PrefConstants.CUSTOMER_ID, PrefConstants.DEFAULT_VALUE);

        presenter.getWishList(api_token,customer_id);

        changeStatusBarColor();
    }

    private void changeStatusBarColor() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.account));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_favorite);
        item.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        int id = item.getItemId();
        if (id == R.id.action_search) {
            startActivity(new Intent(WishListActivity.this, SearchProductActivity.class));
            finish();
            return true;
        }
        if (id == R.id.action_cart) {
            startActivity(new Intent(WishListActivity.this, CartActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgressBar(boolean showpBar) {
        AppUtils.showProgressBar(showpBar, progressBar);
    }

    @Override
    public void onSuccess(@NotNull WishListModel wishListModel) {
        AppUtils.freezeUi(this, false);
        List<WishListModel.Wishlist> wishlistList = wishListModel.getWishlist();
        if (wishlistList.size() != 0) {
            for (int i = 0; i < wishlistList.size(); i++) {
                product_id_public = wishlistList.get(i).getProductId();
                View itemView = LayoutInflater.from(this).inflate(R.layout.items_wishlist, wishlist_layout, false);
                ImageView wishlist_image;
                LinearLayout wishlist_remove, wishlist_addtocart;
                TextView wishlist_product_name, wishlist_product_model, wishlist_product_price, wishlist_product_special, wishlist_product_avaibility;

                wishlist_image = itemView.findViewById(R.id.wishlist_image);
                wishlist_addtocart = itemView.findViewById(R.id.wishlist_addToCart);
                wishlist_product_name = itemView.findViewById(R.id.wishlist_product_name);
                wishlist_product_model = itemView.findViewById(R.id.wishlist_product_model);
                wishlist_remove = itemView.findViewById(R.id.wishlist_remove);
                wishlist_product_avaibility = itemView.findViewById(R.id.wishlist_product_avaibility);
                wishlist_product_price = itemView.findViewById(R.id.wishlist_product_price);
                wishlist_product_special = itemView.findViewById(R.id.wishlist_product_special);
                wishlist_product_name.setText(wishlistList.get(i).getName());
                wishlist_product_model.setText(wishlistList.get(i).getModel());
                wishlist_product_avaibility.setText(wishlistList.get(i).getStock());
                Glide.with(this).load(wishListModel.getWishlist().get(i).getThumb()).into(wishlist_image);

                String special = wishlistList.get(i).getSpecial();
                if (special.equalsIgnoreCase("false")) {
                    wishlist_product_price.setText(wishlistList.get(i).getPrice());

                } else {
                    wishlist_product_price.setText(wishlistList.get(i).getSpecial());
                    wishlist_product_special.setText(wishlistList.get(i).getPrice());
                    wishlist_product_special.setPaintFlags(wishlist_product_special.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                }

                String product_id;
                product_id = wishlistList.get(i).getProductId();
                wishlist_remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!TextUtils.isEmpty(api_token) && !TextUtils.isEmpty(product_id)&& !TextUtils.isEmpty(customer_id)) {
                            presenter.removeWishList(api_token, product_id,customer_id);
                        } else {
                            AppUtils.showToast(WishListActivity.this, "something went wrong");
                        }
                    }
                });
                wishlist_addtocart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!TextUtils.isEmpty(api_token) && !TextUtils.isEmpty(product_id) && !TextUtils.isEmpty(customer_id)) {
                            presenter.addToCart(api_token, product_id,customer_id);
                            presenter.removeWishList(api_token, product_id,customer_id);
                        } else {
                            AppUtils.showToast(WishListActivity.this, "something went wrong");
                        }
                    }
                });
                wishlist_layout.addView(itemView);
            }
        } else {
            Toast.makeText(this, "No product found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showStub(boolean show) {
        if(show)
        AppUtils.showToast(this, "No Product found");
//        else {}
    }

    @Override
    public void onFailure(@Nullable String message) {
        AppUtils.showSnackBar(view, message);
    }


    @Override
    public void onRemoveWishListSuccess(@Nullable String message) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        AppUtils.showToast(this, message);
    }

    @Override
    public void onAddtoCartSuccess(@Nullable String message) {
        if (!TextUtils.isEmpty(api_token) && !TextUtils.isEmpty(product_id_public)) {
            presenter.removeWishList(api_token, product_id_public,customer_id);
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
