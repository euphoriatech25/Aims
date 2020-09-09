package com.smartkirana.aims.aimsshop.views.fragments.OrderHistory;

import com.smartkirana.aims.aimsshop.utils.EndPoints;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OrderHistoryPresenterImpl implements IOrderHistory.Presenter, IOrderHistory.OnFinishListener {
    private IOrderHistory.View view;
    private IOrderHistory.Controller controller;


    public OrderHistoryPresenterImpl(IOrderHistory.View view, IOrderHistory.Controller controller) {
        this.view = view;
        this.controller = controller;
    }



    @Override
    public void onSuccess(@NotNull OrderHistoryModel orderHistoryModel) {
        if (view != null) {
            if (orderHistoryModel.getOrder().getOrderId() !=null)
                view.onSuccess(orderHistoryModel);
            else
                view.showStub(true);
        }
    }

    @Override
    public void onFailure(@Nullable String message) {

    }

    @Override
    public void onNoData() {

    }

    @Override
    public void noInternetConnection() {

    }

    @Override
    public void connectionTimeOut() {

    }

    @Override
    public void unKnownError() {

    }

    @Override
    public void getOrderHistory(@NotNull String api_token, @NotNull String order_id) {
        controller.getOrderHistory(EndPoints.ORDER_HISTORY_GET, api_token, order_id, this);
    }

    @Override
    public void onDestroy() {

    }
}
