package com.smartkirana.aims.aimsshop.views.activities.SubCategories;

import com.smartkirana.aims.aimsshop.network.RetrofitInterface;
import com.smartkirana.aims.aimsshop.network.ServiceConfig;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoriesControllerImpl implements ISubCategories.Controller {
    @Override
    public void getProductList(@NotNull String route, @NotNull String path, @NotNull ISubCategories.OnFinishListener listener) {
        RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);
        Call<SubCategoriesModel> call = post.getSubCategories(route, path);
        call.enqueue(new Callback<SubCategoriesModel>() {
            @Override
            public void onResponse(Call<SubCategoriesModel> call, Response<SubCategoriesModel> response) {
                if (response.isSuccessful()) {
                    SubCategoriesModel subCategoriesModel = response.body();
                    if (subCategoriesModel != null) {
                        listener.onSuccess(subCategoriesModel);
                    }
                } else {
                    listener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<SubCategoriesModel> call, Throwable t) {
            }
        });
    }
    @Override
    public void addToCart(@NotNull String route, @NotNull String api_token, @NotNull String product_id, @NotNull ISubCategories.OnFinishListener listener) {
        RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);

        Call<ResponseBody> call = post.addAddToCart(route,api_token,product_id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    listener.onSuccessAddToCart(response.message());

                    if(response.body()!=null) {

                        listener.onSuccessAddToCart(response.message());
                    }else {
                        listener.onNoData();
                    }
                } else {
                    listener.unKnownError();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    listener.connectionTimeOut();
                } else {
                    listener.unKnownError();
                }
            }
        });
    }

    @Override
    public void addWishList(@NotNull String product_id, @NotNull String api_token, @NotNull String route, @NotNull ISubCategories.OnFinishListener listener) {
        RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);
        Call<ResponseBody> call = post.addWishList(route,api_token,product_id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    listener.onSuccessWishList(response.message());
                    if(response.body()!=null) {
                        listener.onSuccessWishList(response.message());
                    }else {
                        listener.onNoData();
                    }
                } else {
                    listener.unKnownError();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    listener.connectionTimeOut();
                } else {
                    listener.unKnownError();
                }
            }
        });
    }
}

