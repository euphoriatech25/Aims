package com.smartkirana.aims.aimsshop.views.fragments.NavCategories;

import com.smartkirana.aims.aimsshop.network.RetrofitInterface;
import com.smartkirana.aims.aimsshop.network.ServiceConfig;

import org.jetbrains.annotations.Nullable;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesControllerImpl implements ICategories.Controller {
    @Override
    public void getCategoriesList( @Nullable ICategories.OnFinishListener listener) {
            RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);
            Call<CategoriesListModel> call = post.getCategoriesList();
            call.enqueue(new Callback<CategoriesListModel>() {
                @Override
                public void onResponse(Call<CategoriesListModel> call, Response<CategoriesListModel> response) {
                    if (response.isSuccessful()) {
                        if (response.isSuccessful()) {
                            if(response.body()!=null) {
                                CategoriesListModel categoriesListModel = response.body();
                                listener.onSuccess(categoriesListModel);
                            }else {
                                listener.onNoData();
                            }
                        } else {
                            listener.unKnownError();
                        }

                    } else {
                    }
                }

                @Override
                public void onFailure(Call<CategoriesListModel> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        listener.connectionTimeOut();
                    } else {
                        listener.unKnownError();
                    }
                }
            });
        }
}
