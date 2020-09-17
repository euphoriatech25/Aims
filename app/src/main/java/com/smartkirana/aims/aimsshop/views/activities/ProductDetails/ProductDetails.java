package com.smartkirana.aims.aimsshop.views.activities.ProductDetails;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.smartkirana.aims.aimsshop.Conn;
import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.utils.AppUtils;
import com.smartkirana.aims.aimsshop.utils.Constants;
import com.smartkirana.aims.aimsshop.utils.PrefConstants;
import com.smartkirana.aims.aimsshop.views.activities.Cart.CartActivity;
import com.smartkirana.aims.aimsshop.views.activities.WishList.WishListActivity;
import com.smartkirana.aims.aimsshop.views.activities.base.BaseActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails extends BaseActivity implements IProductDetails.View, RecentProductAdapter.OnItemClickListener, View.OnClickListener {

    private String product_name, product_id;
    private ProductDetailsPresenterImpl presenter;
    private ImageView product_image;
    private TextView heading_title, product_code, stock, points, price, product_description, special_product_price, product_model;
    private RecyclerView related_product_recycler;
    private GridLayoutManager layoutManager;
    private RecentProductAdapter recentProductAdapter;
    CardView card_related_products, product_description_card;
    List<ProductDetailsModel.RelatedProduct> relatedProducts;
    private String api_token, customer_id;
    private Button addToCart;
    private ImageButton fav_button, compare_product;
    private View view;
    private ProgressBar progressBar;
    boolean isOpen = false;
    ArrayList<String> productIdList = new ArrayList<>();
    ConstraintLayout containerProductDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_product_details);
        product_image = findViewById(R.id.product_image);
        heading_title = findViewById(R.id.product_name);
        product_code = findViewById(R.id.product_code);
        containerProductDetails = findViewById(R.id.containerProductDetails);
        related_product_recycler = findViewById(R.id.related_product_recycler);
        special_product_price = findViewById(R.id.product_special_price);
        card_related_products = findViewById(R.id.card_related_products);
        product_description_card = findViewById(R.id.product_description_card);
        product_model = findViewById(R.id.product_model);
        progressBar = findViewById(R.id.progressBar);
        addToCart = findViewById(R.id.addToCart);
        stock = findViewById(R.id.stock);
        fav_button = findViewById(R.id.favorite);
        points = findViewById(R.id.points);
        price = findViewById(R.id.product_price);
        product_description = findViewById(R.id.product_description);
        compare_product = findViewById(R.id.compare_product);

        layoutManager = new GridLayoutManager(this, 2);
        related_product_recycler.setLayoutManager(layoutManager);
        related_product_recycler.setHasFixedSize(true);
        related_product_recycler.setFocusable(false);
        related_product_recycler.setAdapter(recentProductAdapter);

        SharedPreferences prefs = getSharedPreferences(PrefConstants.USER_DETAILS_PREF, MODE_PRIVATE);
        api_token = prefs.getString(PrefConstants.API_TOKEN, PrefConstants.DEFAULT_VALUE);
        customer_id = prefs.getString(PrefConstants.CUSTOMER_ID, PrefConstants.DEFAULT_VALUE);
        addToCart.setOnClickListener(this);
        fav_button.setOnClickListener(this);
        compare_product.setOnClickListener(this);
        init();
    }

    private void init() {
        Intent data = getIntent();
        if (data != null) {
            product_name = data.getStringExtra(Constants.PRODUCT_NAME);
            product_id = data.getStringExtra(Constants.PRODUCT_ID);
            setUpToolbar(product_name, true);
            presenter = new ProductDetailsPresenterImpl(this, new ProductDetailsControllerImpl());
            presenter.getProductList(product_id);


        }

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.account));
    }

    @Override
    public void showProgressBar(boolean showpBar) {
        AppUtils.showProgressBar(showpBar, progressBar);
    }

    @Override
    public void onSuccess(@NotNull ProductDetailsModel productDetails) {
        if (productDetails != null) {
            List<ProductDetailsModel.Product> products = productDetails.getProduct();
            for (int i = 0; i < productDetails.getProduct().size(); i++) {
                heading_title.setText(products.get(i).getHeadingTitle());
                product_code.setText(products.get(i).getModel());
                stock.setText(products.get(i).getStock());
                points.setText(products.get(i).getPoints());
                product_model.setText(products.get(i).getModel());
                Glide.with(this).load(products.get(i).getMainImageThumb()).into(product_image);
                relatedProducts = products.get(i).getRelatedProducts();
                if (products.get(i).getRelatedProducts().size() != 0) {
                    recentProductAdapter = new RecentProductAdapter(this, relatedProducts);
                    related_product_recycler.setAdapter(recentProductAdapter);
                    card_related_products.setVisibility(View.VISIBLE);
                    recentProductAdapter.setOnItemClickListener(this);
                } else {
                    card_related_products.setVisibility(View.GONE);
                }

                String special = products.get(i).getSpecial();
                if (special.equalsIgnoreCase("false")) {
                    price.setText(products.get(i).getPrice());
                } else {
                    price.setText(products.get(i).getSpecial());
                    special_product_price.setText(products.get(i).getPrice());
                    special_product_price.setPaintFlags(special_product_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }

                if (products.get(i).getDescription() != "") {
                    product_description_card.setVisibility(View.VISIBLE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        product_description.setText(Html.fromHtml(products.get(i).getDescription(), Html.FROM_HTML_MODE_COMPACT));
                    } else {
                        product_description.setText(Html.fromHtml(products.get(i).getDescription()));
                    }
                } else {
                    product_description_card.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void showStub(boolean show) {
        if (show) {
            AppUtils.showSnackBar(view, "something went wrong");
        }
    }

    @Override
    public void onFailure(@Nullable String message) {
        if (!message.isEmpty()) {
            AppUtils.showToast(this, message);
        }
    }

    @Override
    public void onItemClick(int position, View itemView) {
        if (relatedProducts.size() != 0) {
            Intent intent = new Intent(this, ProductDetails.class);
            intent.putExtra(Constants.PRODUCT_NAME, relatedProducts.get(position).getName());
            intent.putExtra(Constants.PRODUCT_ID, relatedProducts.get(position).getProductId());
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccessAddToCart(@Nullable String message) {
        Snackbar snackbar = Snackbar
                .make(containerProductDetails, "Added to Cart !!!", Snackbar.LENGTH_LONG);
        snackbar.setAction("View Cart", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductDetails.this, CartActivity.class));
                finish();
            }
        });
        snackbar.setTextColor(Color.WHITE);
        snackbar.setBackgroundTint(Color.BLACK);
        snackbar.setActionTextColor(Color.parseColor("#008000"));
        snackbar.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addToCart:
                if (!TextUtils.isEmpty(api_token) && !TextUtils.isEmpty(product_id) && !TextUtils.isEmpty(customer_id)) {
                    presenter.addToCart(product_id, customer_id, api_token);
                } else {
                    Toast.makeText(this, "Please login First", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.favorite:
                if (!TextUtils.isEmpty(api_token) && !TextUtils.isEmpty(product_id) && !TextUtils.isEmpty(customer_id)) {
                    if (isOpen) {
                        fav_button.setColorFilter(Color.parseColor("#FD0505"));
                        presenter.addWishList(product_id, api_token, customer_id);
                        isOpen = false;

                    } else {
                        fav_button.setColorFilter(Color.parseColor("#808080"));
                        isOpen = true;
                    }
                } else {
                    Toast.makeText(this, "Please login First", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.compare_product:
                productIdList.add(product_id);
                Conn.product_id.put(Constants.PRODUCT_ID, productIdList);
                Toast.makeText(this, "Successfully added to Compare List", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onSuccessWishList(@Nullable String message) {
        Snackbar snackbar = Snackbar
                .make(containerProductDetails, "Added to WishList !!!", Snackbar.LENGTH_LONG);
        snackbar.setAction("View Wishlist", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductDetails.this, WishListActivity.class));
                finish();
            }
        });
        snackbar.setTextColor(Color.WHITE);
        snackbar.setBackgroundTint(Color.BLACK);
        snackbar.setActionTextColor(Color.parseColor("#008000"));
        snackbar.show();
    }
}
