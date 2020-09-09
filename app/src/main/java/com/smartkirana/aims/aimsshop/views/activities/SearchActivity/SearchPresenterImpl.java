package com.smartkirana.aims.aimsshop.views.activities.SearchActivity;

import android.content.Context;

import com.smartkirana.aims.aimsshop.utils.Constants;
import com.smartkirana.aims.aimsshop.utils.EndPoints;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class SearchPresenterImpl implements ISearch.Presenter, ISearch.OnFinishListener {
    private ISearch.View view;
    private ISearch.Controller controller;
    private Context context;

    public SearchPresenterImpl(ISearch.View view, ISearch.Controller controller) {
        this.view = view;
        this.controller = controller;
    }
//
//    public SearchPresenterImpl(Context context, SearchControllerImpl controller) {
//        this.context = context;
//        this.controller = controller;
//    }

    @Override
    public void getProductList() {
        if (view != null) {
//            if (AppUtils.isNetworkAvailable()) {
            view.showProgressBar(true);
            Map<String, String> map = new HashMap<>();
            map.put(Constants.CONTENT_TYPE,Constants.JSON );
            controller.getProductList(map, this);
//            } else {
//                view.noInternetConnection();
//            }
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
    public void onSuccess(@NotNull ProductListModel categoriesListModel) {
        if (view != null) {
            view.showProgressBar(false);
            if (categoriesListModel.getProducts().size()!=0)
                view.onSuccess(categoriesListModel);
            else
                view.showStub(true);
        }
    }

    @Override
    public void addToCart(@NotNull String product_id,@NotNull String api_token) {
        if(view!=null){
            controller.addToCart(EndPoints.ADD_TO_CART, api_token, product_id, this);
        }else {
            view.unKnownError();
        }
    }

    @Override
    public void addWishList(@NotNull String product_id, @NotNull String api_token) {
        if (view != null) {
//            if (AppUtils.isNetworkAvailable()) {
            view.showProgressBar(true);

            controller.addWishList(product_id,api_token, EndPoints.WISHLIST_ADD, this);
//            } else {
//                view.noInternetConnection();
//            }
        }

    }

    @Override
    public void onSuccessAddToCart(@Nullable String message) {
        if (view != null) {
            view.showProgressBar(false);
            view.onSuccessAddToCart(message);
        }else
            view.showStub(true);

    }

    @Override
    public void onSuccessAWishList(@Nullable String message) {
        if (view != null) {
            view.showProgressBar(false);
            view.onSuccessAWishList(message);
        }else
                view.showStub(true);

    }
}
