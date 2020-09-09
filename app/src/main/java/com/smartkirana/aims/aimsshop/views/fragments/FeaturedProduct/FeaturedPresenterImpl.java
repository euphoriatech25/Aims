package com.smartkirana.aims.aimsshop.views.fragments.FeaturedProduct;

import android.content.Context;

import com.smartkirana.aims.aimsshop.utils.Constants;
import com.smartkirana.aims.aimsshop.utils.EndPoints;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class FeaturedPresenterImpl implements IFeature.Presenter, IFeature.OnFinishListener {

    private IFeature.View view;
    private IFeature.Controller controller;
    private Context context;

    public FeaturedPresenterImpl(IFeature.View view, IFeature.Controller controller) {
        this.view = view;
        this.controller = controller;
    }

    public FeaturedPresenterImpl(Context context, FeaturedControllerImpl controller) {
        this.context = context;
        this.controller = controller;
    }



    @Override
    public void onSuccess(@NotNull HomeFeaturedModel homeFeaturedMode) {
        if (view != null) {
            if (homeFeaturedMode.getFeatured().size() != 0)
                view.onSuccess(homeFeaturedMode);
            else
                view.showStub(true);
        }
    }


    @Override
    public void onFailure(@Nullable String message) {
        if (view != null) {
            view.onFailure(message);
        }
    }

    @Override
    public void onNoData() {
        if (view != null) {
            view.showStub(true);
        }
    }

    @Override
    public void noInternetConnection() {
        if (view != null) {
            view.noInternetConnection();
        }
    }

    @Override
    public void connectionTimeOut() {
        if (view != null) {
            view.connectionTimeOut();
        }
    }

    @Override
    public void unKnownError() {
        if (view != null) {
            view.unKnownError();
        }
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getFeaturedList() {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.CONTENT_TYPE, Constants.JSON);
        controller.getFeaturedList(map, this);
    }

    @Override
    public void addToCart(@NotNull String product_id,@NotNull String api_token) {
        if(product_id!=null&&api_token!=null) {
            controller.addToCart(EndPoints.ADD_TO_CART, api_token, product_id, this);
        }else {

        }
    }

    @Override
    public void onSuccessAddToCart(@Nullable String message) {
        if (view != null) {
            view.onSuccessAddToCart(message);
            view.showProgressBar(false);
        }
    }

    @Override
    public void onAddSuccess(@Nullable String message) {
         if (view != null) {
            view.onAddSuccess(message);
            view.showProgressBar(false);
        }

    }

    @Override
    public void onSuccessAWishList(@Nullable String message) {
        if (view != null) {
            view.onSuccessAWishList(message);
            view.showProgressBar(false);
        }
    }

    @Override
    public void addWishList(@NotNull String product_id, @NotNull String api_token) {
        controller.addWishList( product_id, api_token, EndPoints.WISHLIST_ADD,this);
    }
}
