package com.smartkirana.aims.aimsshop.views.activities.WishList;

import com.smartkirana.aims.aimsshop.network.RetrofitInterface;
import com.smartkirana.aims.aimsshop.network.ServiceConfig;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishListControllerImpl implements IWishList.Controller {

    @Override
    public void getWishList(@NotNull String route, @NotNull String api_token, @NotNull IWishList.OnFinishListener listener) {
        RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);
        Call<WishListModel> call = post.getWishList(route, api_token);
        call.enqueue(new Callback<WishListModel>() {
            @Override
            public void onResponse(Call<WishListModel> call, Response<WishListModel> response) {
                if (response.isSuccessful()) {
                    WishListModel wishListModel = response.body();
                    if (wishListModel != null) {
                        listener.onSuccess(wishListModel);
                    }
                } else {
                    listener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<WishListModel> call, Throwable t) {
            }
        });
    }

    @Override
    public void removeWishList(@NotNull String route, @NotNull String api_token, @NotNull String product_id, @NotNull IWishList.OnFinishListener listener) {
        RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);
        Call<ResponseBody> call = post.removeWishList(route, api_token, product_id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    listener.onRemoveWishListSuccess(response.message());

                } else {
                    listener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    @Override
    public void addToCart(@NotNull String route, @NotNull String api_token, @NotNull String product_id, @NotNull IWishList.OnFinishListener listener) {
        RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);

        Call<ResponseBody> call = post.addAddToCart(route,api_token,product_id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null) {
                        listener.onAddtoCartSuccess(response.message());
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

