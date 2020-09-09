package com.smartkirana.aims.aimsshop.views.fragments.OrderHistory;

import com.smartkirana.aims.aimsshop.network.RetrofitInterface;
import com.smartkirana.aims.aimsshop.network.ServiceConfig;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryControllerImpl  implements IOrderHistory.Controller {
    @Override
    public void getOrderHistory(@NotNull String route, @NotNull String api_token, @NotNull String order_id, @NotNull IOrderHistory.OnFinishListener listener) {
       RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);
            Call<OrderHistoryModel> call = post.getOrderHistory(route, api_token);
            call.enqueue(new Callback<OrderHistoryModel>() {
                @Override
                public void onResponse(Call<OrderHistoryModel> call, Response<OrderHistoryModel> response) {
                    if (response.isSuccessful()) {
                        OrderHistoryModel orderHistoryModel=response.body();
                        listener.onSuccess(orderHistoryModel);
                    } else {
                        listener.onFailure(response.message());
                    }
                }
                @Override
                public void onFailure(Call<OrderHistoryModel> call, Throwable t) {
                }
            });
        }
    }

