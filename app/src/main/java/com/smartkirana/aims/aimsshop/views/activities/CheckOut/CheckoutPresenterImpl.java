package com.smartkirana.aims.aimsshop.views.activities.CheckOut;

import com.smartkirana.aims.aimsshop.utils.EndPoints;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CheckoutPresenterImpl  implements ICheckout.Presenter, ICheckout.OnFinishListener{

    private static final String TAG = "CheckoutPresenterImpl";
    private ICheckout.View view;
    private CheckoutControllerImpl controller;


    public CheckoutPresenterImpl(ICheckout.View view , CheckoutControllerImpl controller) {
        this.view = view;
        this.controller = controller;
    }
    @Override
    public void onSuccess(@Nullable String message) {
        if (view != null) {
            view.onSuccess();
            view.showProgressBar(false);
        }else {
            view.onFailure(message);
        }
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
    public void onDestroy() {

    }

    @Override
    public void orderProducts(@NotNull String api_token, @NotNull String customer_id, @NotNull String payment_method, @NotNull String shipping_method, @NotNull String address_id, @NotNull String shipping_address_id, @NotNull String comment, @NotNull String affiliate_id) {
        if(view!=null){
            controller.orderProducts(EndPoints.ORDER_ADD, api_token,customer_id, payment_method, shipping_method,address_id,shipping_address_id,comment,affiliate_id,this);
        }else {
            view.unKnownError();
        }
    }
}
