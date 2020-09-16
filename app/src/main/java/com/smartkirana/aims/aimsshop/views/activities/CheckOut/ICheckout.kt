package com.smartkirana.aims.aimsshop.views.activities.CheckOut

import com.smartkirana.aims.aimsshop.interfaces.BasePresenter
import com.smartkirana.aims.aimsshop.interfaces.BaseResponse
import com.smartkirana.aims.aimsshop.interfaces.BaseView
import com.smartkirana.aims.aimsshop.views.activities.ProductDetails.IProductDetails
import com.smartkirana.aims.aimsshop.views.activities.ProductDetails.ProductDetailsModel

internal interface ICheckout {
    interface View : BaseView {
        fun getFirstName(): String
        fun getLastName(): String
        fun getCompanyName(): String
        fun getAddress1(): String
        fun getAddress2(): String
        fun getCity(): String
        fun getPostCode(): String
        fun getRegion(): String
        fun setFirstName(): String
        fun setLastName(): String
        fun setCompanyName(): String
        fun setAddress1(): String
        fun setAddress2(): String
        fun setCity(): String
        fun setPostCode(): String
        fun setRegion(): String

        fun onSuccess()
        fun onFailure(message: String)
        fun showStub(show: Boolean)

    }
    interface Presenter : BasePresenter {
        fun addAddress(customer_id: String,checkoutModel:CheckoutModel)
    }


    interface Controller {
          fun addAddress(route: String, customer_id: String,checkoutModel:CheckoutModel,listener: IProductDetails.OnFinishListener)

    }

    interface OnFinishListener : BaseResponse {
        fun onSuccess(message: String?)
        fun onFailure(message: String?)
        fun onNoData()
    }

}