package com.smartkirana.aims.aimsshop.views.activities.ProductDetails;

import android.util.Log;

import com.smartkirana.aims.aimsshop.network.RetrofitInterface;
import com.smartkirana.aims.aimsshop.network.ServiceConfig;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsControllerImpl implements IProductDetails.Controller {
    @Override
    public void getProductList(@NotNull String route, @NotNull String product_id, @NotNull IProductDetails.OnFinishListener listener) {
        RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);
        Call<ProductDetailsModel> call=post.getProductDetails(route,product_id);
        call.enqueue(new Callback<ProductDetailsModel>() {
            @Override
            public void onResponse(Call<ProductDetailsModel> call, Response<ProductDetailsModel> response) {
                     if(response.isSuccessful()){
                         ProductDetailsModel productDetailsModel=response.body();
                         if (productDetailsModel != null) {
                             listener.onSuccess(productDetailsModel);
                         }
                     }else {
                         listener.onFailure(response.message());
                     }
            }

            @Override
            public void onFailure(Call<ProductDetailsModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void addToCart(@NotNull String route, @NotNull String api_token,@NotNull String cusmoter_id, @NotNull String product_id, @NotNull IProductDetails.OnFinishListener listener) {
        RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);

        Call<ResponseBody> call = post.addAddToCart(route,api_token,product_id,cusmoter_id);
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
    public void addWishList(@NotNull String route, @NotNull String api_token,@NotNull String cusmoter_id, @NotNull String product_id, @NotNull IProductDetails.OnFinishListener listener) {
        RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);
        Call<ResponseBody> call = post.addWishList(route,api_token,product_id,cusmoter_id);
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
