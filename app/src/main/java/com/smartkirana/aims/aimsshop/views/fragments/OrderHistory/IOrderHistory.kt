package com.smartkirana.aims.aimsshop.views.fragments.OrderHistory

import com.smartkirana.aims.aimsshop.interfaces.BasePresenter
import com.smartkirana.aims.aimsshop.interfaces.BaseResponse
import com.smartkirana.aims.aimsshop.interfaces.BaseView
import com.smartkirana.aims.aimsshop.views.fragments.FeaturedProduct.HomeFeaturedModel
import com.smartkirana.aims.aimsshop.views.fragments.FeaturedProduct.IFeature

internal interface IOrderHistory {
    interface View : BaseView {
        fun onSuccess(orderHistoryModel: OrderHistoryModel)
        fun showStub(show: Boolean)
        fun onFailure(message: String?)

    }

    interface Presenter : BasePresenter {
        fun getOrderHistory(api_token: String, order_id: String)
    }


    interface Controller {
        fun getOrderHistory(route: String, api_token: String, order_id: String, listener: IOrderHistory.OnFinishListener)
    }

    interface OnFinishListener : BaseResponse {
        fun onSuccess(orderHistoryModel: OrderHistoryModel)
        fun onFailure(message: String?)
        fun onNoData()
    }
}