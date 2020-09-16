package com.smartkirana.aims.aimsshop.views.activities.SubCategories;

import android.content.Context;

import com.smartkirana.aims.aimsshop.utils.EndPoints;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SubCategoriesPresenterImpl implements ISubCategories.Presenter, ISubCategories.OnFinishListener {

    private ISubCategories.View view;
    private ISubCategories.Controller controller;
    private Context context;


    public SubCategoriesPresenterImpl(ISubCategories.View view, SubCategoriesControllerImpl controller) {
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void onSuccess(@NotNull SubCategoriesModel subCategoriesModel) {
        if (view != null) {
            view.onSuccess(subCategoriesModel);
            view.showProgressBar(false);
        }
    }

    @Override
    public void onFailure(@Nullable String message) {

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
    public void getSubCategoriesList(@NotNull String path) {
        if (view != null) {
//            if (AppUtils.isNetworkAvailable()) {
            view.showProgressBar(true);
            controller.getProductList(EndPoints.CATEGORY_GET, path, this);
//            } else {
//                view.noInternetConnection();
//            }
        }
    }



    @Override
    public void addWishList(@NotNull String product_id, @NotNull String customer_id, @NotNull String api_token) {
        if (view != null) {
//            if (AppUtils.isNetworkAvailable()) {
            view.showProgressBar(true);
            controller.addWishList( product_id,customer_id,api_token,EndPoints.WISHLIST_ADD,this);
//            } else {
//                view.noInternetConnection();
//            }
        }
    }



    @Override
    public void onSuccessWishList(@Nullable String message) {
        if (view != null) {
            view.onSuccessWishList(message);
            view.showProgressBar(false);
        }
    }

    @Override
    public void addToCart(@NotNull String product_id, @NotNull String api_token,@NotNull String customer_id) {
        if (view != null) {
//            if (AppUtils.isNetworkAvailable()) {
            view.showProgressBar(true);
            controller.addToCart(EndPoints.ADD_TO_CART,api_token, product_id,customer_id,this);
//            } else {
//                view.noInternetConnection();
//            }
        }
    }

    @Override
    public void onSuccessAddToCart(@Nullable String message) {
        if (view != null) {
            view.onSuccessAddToCart(message);
            view.showProgressBar(false);
        }
    }
}
