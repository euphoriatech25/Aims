package com.smartkirana.aims.aimsshop.views.fragments.NavCategories;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CategoriesPresenterImpl  implements ICategories.Presenter, ICategories.OnFinishListener {

    private ICategories.View view;
    private ICategories.Controller controller;

    public CategoriesPresenterImpl(ICategories.View view, ICategories.Controller controller) {
        this.view = view;
        this.controller = controller;
    }
    @Override
    public void onSuccess(@NotNull CategoriesListModel categoriesListModel) {
        if (view != null) {
            view.showProgressBar(false);
            if (categoriesListModel.getCategories().size()!=0)
                view.onSuccess(categoriesListModel);
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
    public void getCategoriesList() {
        view.showProgressBar(true);
//        Map<String, String> map = new HashMap<>();
//        map.put(Constants.CONTENT_TYPE, Constants.JSON );
        controller.getCategoriesList( this);

    }

    @Override
    public void onDestroy() {

    }
}
