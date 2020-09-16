package com.smartkirana.aims.aimsshop.views.activities.CheckOut;

import com.smartkirana.aims.aimsshop.views.activities.Cart.ICart;
import com.smartkirana.aims.aimsshop.views.activities.Register.CreateAccountControllerImpl;
import com.smartkirana.aims.aimsshop.views.activities.createAccount.ICreateAccount;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CheckoutPresenterImpl  implements ICheckout.Presenter, ICheckout.OnFinishListener{

    private static final String TAG = "CheckoutPresenterImpl";
    private ICheckout.View view;
    private CheckoutControllerImpl controller;


    public CheckoutPresenterImpl(ICheckout.View view,CheckoutControllerImpl controller) {
        this.view = view;
        this.controller = controller;
    }
    @Override
    public void onSuccess(@Nullable String message) {

    }

    @Override
    public void onFailure(@Nullable String message) {

    }

    @Override
    public void onNoData() {

    }

    @Override
    public void noInternetConnection() {

    }

    @Override
    public void connectionTimeOut() {

    }

    @Override
    public void unKnownError() {

    }

    @Override
    public void addAddress(@NotNull String customer_id, @NotNull CheckoutModel checkoutModel) {

    }

    @Override
    public void onDestroy() {

    }
}
