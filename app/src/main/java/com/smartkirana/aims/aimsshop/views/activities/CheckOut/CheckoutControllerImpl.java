package com.smartkirana.aims.aimsshop.views.activities.CheckOut;

import com.smartkirana.aims.aimsshop.network.RetrofitInterface;
import com.smartkirana.aims.aimsshop.network.ServiceConfig;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutControllerImpl implements ICheckout.Controller {
    @Override
    public void orderProducts(@NotNull String route, @NotNull String api_token, @NotNull String customer_id, @NotNull String payment_method, @NotNull String shipping_method, @NotNull String address_id, @NotNull String shipping_address_id, @NotNull String comment, @NotNull String affiliate_id, @Nullable ICheckout.OnFinishListener listener) {
        RetrofitInterface retrofitInterface = ServiceConfig.createService(RetrofitInterface.class);
        Call<ResponseBody> call = retrofitInterface.addOrder(route, api_token, customer_id, payment_method, shipping_method, address_id, shipping_address_id, comment, affiliate_id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    if (response.body() != null) {
                        listener.onSuccess(response.message());
                    }else {
                        listener.onFailure(response.message());
                    }
                }else {
                    listener.onFailure(response.message());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
