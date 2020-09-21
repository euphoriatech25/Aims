package com.smartkirana.aims.aimsshop.views.activities.WishList;

import android.content.Context;

import com.smartkirana.aims.aimsshop.utils.EndPoints;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WishListPresenterImpl implements IWishList.Presenter, IWishList.OnFinishListener {
    private IWishList.View view;
    private IWishList.Controller controller;
    private Context context;

    public WishListPresenterImpl(IWishList.View view, WishListControllerImpl controller) {
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void onSuccess(WishListModel wishListModel ) {
        if (view != null) {
            view.onSuccess(wishListModel);
            view.showProgressBar(false);
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

    }


    @Override
    public void getWishList(@NotNull String api_token,@NotNull String customer_id) {
        if (view != null) {
//            if (AppUtils.isNetworkAvailable()) {
            view.showProgressBar(true);
            controller.getWishList(EndPoints.WISHLIST_GET,api_token,customer_id,this);
//            } else {
//                view.noInternetConnection();
//            }
        }
    }

    @Override
    public void removeWishList(@NotNull String api_token, @NotNull String product_id,@NotNull String customer_id) {
        if (view != null) {
//            if (AppUtils.isNetworkAvailable()) {
            view.showProgressBar(true);
            controller.removeWishList(EndPoints.WISHLIST_REMOVE,api_token,product_id,customer_id,this);
//            } else {
//                view.noInternetConnection();
//            }
        }
    }

    @Override
    public void onRemoveWishListSuccess(@Nullable String message) {
        if (view != null) {
            view.onRemoveWishListSuccess(message);
            view.showProgressBar(false);
        }
    }

    @Override
    public void addToCart(@NotNull String api_token, @NotNull String product_id,@NotNull String customer_id) {
        if (view != null) {
//            if (AppUtils.isNetworkAvailable()) {
            view.showProgressBar(true);
            controller.addToCart(EndPoints.CARD_ADD,api_token,product_id,customer_id,this);
//            } else {
//                view.noInternetConnection();
//            }
        }
    }

    @Override
    public void onAddtoCartSuccess(@Nullable String message) {
        if (view != null) {
            view.onAddtoCartSuccess(message);
            view.showProgressBar(false);
        }
    }
}
