package com.smartkirana.aims.aimsshop.views.activities.Comparison;

import com.smartkirana.aims.aimsshop.network.RetrofitInterface;
import com.smartkirana.aims.aimsshop.network.ServiceConfig;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductComparisonControllerImpl implements IProductComparison.Controller {
    @Override
    public void onCompareProductPost(@Nullable String route, @NotNull ArrayList<String> product_id, @NotNull IProductComparison.OnFinishListener listener) {
        RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);
        Call<CompareModel> call = post.compareProduct(route,product_id);
        call.enqueue(new Callback<CompareModel>() {
            @Override
            public void onResponse(Call<CompareModel> call, Response<CompareModel> response) {
                if(response.isSuccessful()){
                    CompareModel compareModel=response.body();
                    listener.onCompareProductSuccessPost(compareModel);
                    if(response.body()!=null) {
                        listener.onCompareProductSuccessPost(compareModel);
                    }else {
                        listener.onNoData();
                    }
                } else {
                    listener.unKnownError();
                }
            }

            @Override
            public void onFailure(Call<CompareModel> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    listener.connectionTimeOut();
                } else {
                    listener.unKnownError();
                }
            }
        });
    }
}
