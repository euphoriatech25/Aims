package com.smartkirana.aims.aimsshop.views.activities.Cart;

import com.smartkirana.aims.aimsshop.utils.EndPoints;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CartPresenterImpl implements ICart.Presenter, ICart.OnFinishListener {
    private ICart.View view;
    private ICart.Controller controller;

    public CartPresenterImpl(ICart.View view, ICart.Controller controller) {
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void getCartProductList(String api_token, @NotNull String customer_id) {
        if (view != null) {
            view.showProgressBar(true);
            controller.getCartProductList(EndPoints.CART_PRODUCTS, api_token, customer_id,this);
        }
    }


    @Override
    public void onFailure(@Nullable String message) {
        if (view != null) {
            view.showProgressBar(false);
            view.onFailure(message);
        }
    }

    @Override
    public void onNoData() {
        if (view != null) {
            view.showProgressBar(false);
            view.showStub(true);
        }
    }

    @Override
    public void noInternetConnection() {
        if (view != null) {
            view.showProgressBar(false);
            view.noInternetConnection();
        }
    }

    @Override
    public void connectionTimeOut() {
        if (view != null) {
            view.showProgressBar(false);
            view.connectionTimeOut();
        }
    }

    @Override
    public void unKnownError() {
        if (view != null) {
            view.showProgressBar(false);
            view.unKnownError();
        }
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onSuccess(@NotNull CartModel cartModel) {
        if (view != null) {
            view.showProgressBar(false);
            if (cartModel.getProducts().size() != 0)
                view.onSuccess(cartModel);
            else
                view.showStub(true);
        }
    }

    @Override
    public void onSuccessEditRemove(@Nullable String message) {
        if (view != null) {
            view.showProgressBar(false);
            view.onSuccessEditRemove(message);
//            if (cartModel.getProducts().size() != 0)
//                view.onSuccess(cartModel);
//            else
//                view.showStub(true);
        }
    }

    @Override
    public void editCartProduct(@NotNull String key, @NotNull String quantity,@NotNull String customer_id, @NotNull String api_token) {
        if (view != null) {
            view.showProgressBar(true);
            controller.editProductList(EndPoints.CART_EDIT, api_token,customer_id, key, quantity,this);

        }
    }

    @Override
    public void removeCartProduct(@NotNull String key, @NotNull String customer_id,@NotNull String api_token) {
        if (view != null) {
            view.showProgressBar(true);
            controller.removeCartProduct(EndPoints.CART_REMOVE, api_token,customer_id, key, this);

        }
    }
}
