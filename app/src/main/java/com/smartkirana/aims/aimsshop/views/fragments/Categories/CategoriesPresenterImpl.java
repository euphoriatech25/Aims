package com.smartkirana.aims.aimsshop.views.fragments.Categories;

import com.smartkirana.aims.aimsshop.utils.Constants;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class CategoriesPresenterImpl implements ICategories.Presenter, ICategories.OnFinishListener {



    private ICategories.View view;
    private ICategories.Controller controller;

    public CategoriesPresenterImpl(ICategories.View view, ICategories.Controller controller) {
        this.view = view;
        this.controller = controller;
    }


    @Override
    public void onSuccess(@NotNull CategoriesModel categoriesModel) {
        if (view != null) {
            if (categoriesModel.getFeaturedcategory().size() != 0)
                view.onSuccess(categoriesModel);
            else
                view.showStub(true);
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
    public void getFeatureCategoriesList() {
        view.showProgressBar(true);
        Map<String, String> map = new HashMap<>();
        map.put(Constants.CONTENT_TYPE, Constants.JSON);
        controller.getFeatureCategoriesList( this);
    }

    @Override
    public void onDestroy() {

    }
}
