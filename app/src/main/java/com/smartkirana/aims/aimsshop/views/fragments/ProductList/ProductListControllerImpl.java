package com.smartkirana.aims.aimsshop.views.fragments.ProductList;

import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ProductListControllerImpl implements IProductList.Controller {
    @Override
    public void getProductList(@Nullable Map<String, String> map, @Nullable IProductList.OnFinishListener listener) {
//        RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);
//        Call<ProductListModel> call = post.getProductList(map);
//            call.enqueue(new Callback<ProductListModel>() {
//                @Override
//                public void onResponse(Call<ProductListModel> call, Response<ProductListModel> response) {
//                    if (response.isSuccessful()) {
//                        if (response.body() != null) {
//                            System.out.println(response.body().getProducts().toString());
//                            listener.onSuccess(response.body());
//                        }
//                    } else {
//                        listener.unKnownError();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ProductListModel> call, Throwable t) {
//                    if (t instanceof SocketTimeoutException) {
//                        listener.connectionTimeOut();
//                    } else {
//                        listener.unKnownError();
//                    }
//                }
//            });
    }

}
