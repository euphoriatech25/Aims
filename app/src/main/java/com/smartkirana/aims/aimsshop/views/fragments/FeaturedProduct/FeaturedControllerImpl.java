package com.smartkirana.aims.aimsshop.views.fragments.FeaturedProduct;

import com.smartkirana.aims.aimsshop.network.RetrofitInterface;
import com.smartkirana.aims.aimsshop.network.ServiceConfig;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.SocketTimeoutException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeaturedControllerImpl implements IFeature.Controller{
    @Override
    public void getFeaturedList(@Nullable Map<String, String> map, @Nullable IFeature.OnFinishListener listener) {
        RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);
        Call<HomeFeaturedModel> call = post.getFeaturedList();
        call.enqueue(new Callback<HomeFeaturedModel>() {
            @Override
            public void onResponse(Call<HomeFeaturedModel> call, Response<HomeFeaturedModel> response) {
                if (response.isSuccessful()) {
                         if(response.body()!=null) {
                            HomeFeaturedModel featuredModel = response.body();
                            listener.onSuccess(featuredModel);
                        }else {
                            listener.onNoData();
                        }
                    } else {
                        listener.unKnownError();
                    }

            }
            @Override
            public void onFailure(Call<HomeFeaturedModel> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    listener.connectionTimeOut();
                } else {
                    listener.unKnownError();
                }
            }
        });
    }

    @Override
    public void addToCart(@NotNull String route, @NotNull String api_token, @NotNull String product_id, @NotNull IFeature.OnFinishListener listener) {
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
    public void addWishList(@NotNull String product_id, @NotNull String api_token, @NotNull String route, @NotNull IFeature.OnFinishListener listener) {
        RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);
        Call<ResponseBody> call = post.addWishList(route,api_token,product_id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                 listener.onSuccessAWishList(response.message());
                    if(response.body()!=null) {
                        listener.onSuccessAWishList(response.message());
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



