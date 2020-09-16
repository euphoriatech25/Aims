package com.smartkirana.aims.aimsshop.views.activities.Cart;

import com.smartkirana.aims.aimsshop.network.RetrofitInterface;
import com.smartkirana.aims.aimsshop.network.ServiceConfig;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartControllerImpl implements ICart.Controller {

    @Override
    public void editProductList(@NotNull String route, @NotNull String api_token, @NotNull String key, @NotNull String customer_id, @NotNull String quantity, @Nullable ICart.OnFinishListener listener) {
        RetrofitInterface retrofitInterface = ServiceConfig.createService(RetrofitInterface.class);
        Call<ResponseBody> call = retrofitInterface.editAddToCart(route, api_token, key, quantity,customer_id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listener.onSuccessEditRemove(response.message());
                    } else {
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
    public void removeCartProduct(@NotNull String route, @NotNull String api_token, @NotNull String product_id,@NotNull String customer_id, @Nullable ICart.OnFinishListener listener) {
        RetrofitInterface retrofitInterface = ServiceConfig.createService(RetrofitInterface.class);
        Call<ResponseBody> call = retrofitInterface.removeAddToCart(route, api_token, product_id,customer_id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listener.onSuccessEditRemove(response.message());
                    } else {
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
    public void getCartProductList(@NotNull String route, @NotNull String api_token, @NotNull String customer_id, @Nullable ICart.OnFinishListener listener) {
        RetrofitInterface retrofitInterface = ServiceConfig.createService(RetrofitInterface.class);
        Call<CartModel> call = retrofitInterface.getAddToCart(route, api_token,customer_id);
        call.enqueue(new Callback<CartModel>() {
            @Override
            public void onResponse(Call<CartModel> call, Response<CartModel> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        CartModel cartModel = response.body();
                        if(cartModel.getError()==null) {
                            listener.onSuccess(cartModel);
                        }else {
                            listener.onFailure(cartModel.getError());
                        }
                    } else {
                        listener.onNoData();
                    }
                } else {
                    listener.unKnownError();
                }

            }

            @Override
            public void onFailure(Call<CartModel> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    listener.connectionTimeOut();
                } else {
                    listener.unKnownError();
                }
            }
        });
    }
}
