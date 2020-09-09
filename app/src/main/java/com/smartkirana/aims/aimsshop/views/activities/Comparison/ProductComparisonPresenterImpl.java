package com.smartkirana.aims.aimsshop.views.activities.Comparison;

import com.smartkirana.aims.aimsshop.utils.EndPoints;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProductComparisonPresenterImpl  implements IProductComparison.Presenter, IProductComparison.OnFinishListener  {

    private IProductComparison.View view;
    private IProductComparison.Controller controller;

    public ProductComparisonPresenterImpl(IProductComparison.View view, ProductComparisonControllerImpl controller) {
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
    public void onCompareProductPost(@NotNull ArrayList<String> product_id) {
        if(view!=null){
            controller.onCompareProductPost(EndPoints.PRODUCT_COMPARE,  product_id, this);
        }else {
            view.unKnownError();
        }
    }

    @Override
    public void onCompareProductSuccessPost(@NotNull CompareModel compareModel) {
        if (view != null) {
            view.onCompareProductSuccessPost(compareModel);
            view.showProgressBar(false);
        }else {
           view.showStub(true);
        }
    }

    @Override
    public void onDestroy() {

    }
}
