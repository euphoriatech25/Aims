package com.smartkirana.aims.aimsshop.views.activities.Cart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.smartkirana.aims.aimsshop.Conn;
import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.utils.AppUtils;
import com.smartkirana.aims.aimsshop.utils.Constants;
import com.smartkirana.aims.aimsshop.utils.PrefConstants;
import com.smartkirana.aims.aimsshop.views.activities.CheckOut.CheckoutActivity;
import com.smartkirana.aims.aimsshop.views.activities.Home.HomeActivity;
import com.smartkirana.aims.aimsshop.views.activities.Login.LoginActivity;
import com.smartkirana.aims.aimsshop.views.activities.SearchActivity.SearchProductActivity;
import com.smartkirana.aims.aimsshop.views.activities.WishList.WishListActivity;
import com.smartkirana.aims.aimsshop.views.activities.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends BaseActivity implements ICart.View, View.OnClickListener, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    LinearLayout  linearlayoutTotal, ifEmpty,cartLayout;
    ProgressBar progressBar;
    String name;
    private CartPresenterImpl presenter;
    private String api_token, customer_id;
    private Button continue_shopping, checkout, continue_shopping_empty;
    List<CartModel.Product> selectedProducts = new ArrayList<CartModel.Product>();
    boolean isOpen = false;
    private TextView totalDetails;
    private BottomNavigationView bottomNavigationView;
    List<CartModel.Product>cartList;
    RecyclerView cartRecycler;
    CartListAdapter cartListAdapter;
    private GridLayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        linearlayoutTotal = findViewById(R.id.linearlayoutTotal);
        cartLayout = findViewById(R.id.cartLayout);
        progressBar = findViewById(R.id.progressBar);
        cartRecycler = findViewById(R.id.cartRecycler);
        continue_shopping = findViewById(R.id.continue_shopping);
        continue_shopping_empty = findViewById(R.id.continue_shopping_empty);
        checkout = findViewById(R.id.checkout);
        totalDetails = findViewById(R.id.totalDetails);
        ifEmpty = findViewById(R.id.ifEmpty);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        SharedPreferences prefs = getSharedPreferences(PrefConstants.USER_DETAILS_PREF, MODE_PRIVATE);
        api_token = prefs.getString(PrefConstants.API_TOKEN, PrefConstants.DEFAULT_VALUE);
        customer_id = prefs.getString(PrefConstants.CUSTOMER_ID, PrefConstants.DEFAULT_VALUE);

        presenter = new CartPresenterImpl(this, new CartControllerImpl());
        presenter.getCartProductList(api_token, customer_id);
        continue_shopping.setOnClickListener(this);
        checkout.setOnClickListener(this);

        layoutManager = new GridLayoutManager(this, 1);
        cartRecycler.setLayoutManager(layoutManager);
        cartRecycler.setHasFixedSize(true);
        cartRecycler.setFocusable(false);
        cartRecycler.setAdapter(cartListAdapter);
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
        cartList=cartModel.getProducts();
        cartListAdapter = new CartListAdapter(this, cartList);
        cartRecycler.setAdapter(cartListAdapter);



        for (int i = 0; i < totals.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.cart_total, linearlayoutTotal, false);
            TextView total_title = view.findViewById(R.id.total_title);
            total_title.setText(totals.get(i).getTitle() + " : ");
            TextView total_details = view.findViewById(R.id.total_details);
            total_details.setText(totals.get(i).getText());
            linearlayoutTotal.addView(view);
        }

        addSlidingAnimation();

    }

    private void addSlidingAnimation() {
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(cartRecycler);
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback1 = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // Row is swiped from recycler view
                // remove it from adapter
            }
            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        new ItemTouchHelper(itemTouchHelperCallback1).attachToRecyclerView(cartRecycler);
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
                selectedProducts= Conn.selectedProduct.get(Constants.SELECTED_PRODUCT);
                if (selectedProducts.size() != 0) {
                    if (!api_token.equalsIgnoreCase("No Api Token Found")) {
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

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof CartListAdapter.MyViewHolder) {
            String name = cartList.get(viewHolder.getAdapterPosition()).getName();

             final CartModel.Product deletedItem = cartList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

             cartListAdapter.removeItem(viewHolder.getAdapterPosition());
            if (!TextUtils.isEmpty(api_token) &&  !TextUtils.isEmpty(customer_id)&&cartList!=null) {
                presenter.removeCartProduct(customer_id,cartList.get(position).getCartId(), api_token);
            }

            Snackbar snackbar = Snackbar
                    .make(cartLayout, name + " removed from cart!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    cartListAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}
