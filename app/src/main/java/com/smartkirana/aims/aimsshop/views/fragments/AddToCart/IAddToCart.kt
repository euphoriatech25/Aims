package com.smartkirana.aims.aimsshop.views.fragments.AddToCart

import com.smartkirana.aims.aimsshop.interfaces.BasePresenter
import com.smartkirana.aims.aimsshop.interfaces.BaseResponse
import com.smartkirana.aims.aimsshop.interfaces.BaseView
import com.smartkirana.aims.aimsshop.views.activities.Register.CreateAccountModel

internal interface IAddToCart {
    interface View : BaseView {
        fun onSuccess(message: String)
        fun showStub(show: Boolean)
        fun onFailure(message: String?)
    }
    interface Presenter : BasePresenter {
        fun addToCart()
    }

    interface Controller {
        fun addToCart(route :String, product_id:String, listener: OnFinishListener)
    }
    interface OnFinishListener : BaseResponse {
        fun onSuccess(message: String)
        fun onFailure(message: String)
        fun onNoData()
    }

}