package com.smartkirana.aims.aimsshop.views.fragments.Categories;

import com.smartkirana.aims.aimsshop.network.RetrofitInterface;
import com.smartkirana.aims.aimsshop.network.ServiceConfig;

import org.jetbrains.annotations.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesControllerImpl implements ICategories.Controller {

    @Override
    public void getFeatureCategoriesList(@Nullable ICategories.OnFinishListener listener) {
        RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);
        Call<CategoriesModel> call = post.getCategories();
        call.enqueue(new Callback<CategoriesModel>() {
            @Override
            public void onResponse(Call<CategoriesModel> call, Response<CategoriesModel> response) {
                     if (response.isSuccessful()) {
                        if(response.body()!=null) {
                            CategoriesModel featuredModel = response.body();
                            listener.onSuccess(featuredModel);
                        }else {
                            listener.onNoData();
                        }
                    } else {
                        listener.unKnownError();
                    }


            }

            @Override
            public void onFailure(Call<CategoriesModel> call, Throwable t) {

            }
        });
    }
}
