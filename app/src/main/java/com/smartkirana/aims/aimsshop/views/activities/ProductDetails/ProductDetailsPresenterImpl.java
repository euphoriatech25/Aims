package com.smartkirana.aims.aimsshop.views.activities.ProductDetails;

import com.smartkirana.aims.aimsshop.utils.EndPoints;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProductDetailsPresenterImpl implements IProductDetails.Presenter, IProductDetails.OnFinishListener  {

    private IProductDetails.View view;
    private IProductDetails.Controller controller;

    public ProductDetailsPresenterImpl(IProductDetails.View view, ProductDetailsControllerImpl controller) {
        this.view = view;
        this.controller = controller;
    }


    @Override
    public void onNoData() {
        if (view != null) {
            view.showProgressBar(false);
            view.showStub(true);
        }
    }


    @Override
    public void onFailure(@NotNull String message) {
        if (view != null) {
            view.showProgressBar(false);
            view.onFailure(message);
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
    public void getProductList(@NotNull String productId) {
        if (view != null) {
//            if (AppUtils.isNetworkAvailable()) {
                view.showProgressBar(true);
            controller.getProductList(EndPoints.PRODUCT_DETAILS_GET,productId, this);
//            } else {
//                view.noInternetConnection();
//            }
        }
    }

    @Override
    public void onSuccess(@NotNull ProductDetailsModel productDetails) {
        if (view != null) {
            view.onSuccess(productDetails);
            view.showProgressBar(false);
        }
    }

    @Override
    public void addToCart(@NotNull String product_id,@NotNull String customer_id,@NotNull String api_token) {
        if(view!=null){
            controller.addToCart(EndPoints.CARD_ADD, api_token,customer_id, product_id, this);
        }else {
            view.unKnownError();
        }
    }

    @Override
    public void onSuccessAddToCart(@Nullable String message) {
        if (view != null) {
            view.onSuccessAddToCart(message);
            view.showProgressBar(false);
        }else {
            view.onFailure(message);
        }
    }

    @Override
    public void onSuccessWishList(@Nullable String message) {
        if (view != null) {
            view.onSuccessWishList(message);
            view.showProgressBar(false);
        }else {
            view.onSuccessWishList(message);
        }
    }

    @Override
    public void addWishList(@NotNull String product_id, @NotNull String api_token, @NotNull String customer_id) {
        if(view!=null){
            controller.addWishList(EndPoints.WISHLIST_ADD, api_token,customer_id, product_id, this);
        }else {
            view.unKnownError();
        }
    }

    @Override
    public void onDestroy() {

    }
}

