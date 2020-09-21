package com.smartkirana.aims.aimsshop.views.activities.CheckOut

import com.smartkirana.aims.aimsshop.interfaces.BasePresenter
import com.smartkirana.aims.aimsshop.interfaces.BaseResponse
import com.smartkirana.aims.aimsshop.interfaces.BaseView
import com.smartkirana.aims.aimsshop.views.activities.Cart.ICart
import com.smartkirana.aims.aimsshop.views.activities.ProductDetails.IProductDetails
import com.smartkirana.aims.aimsshop.views.activities.ProductDetails.ProductDetailsModel

internal interface ICheckout {
    interface View : BaseView {
        fun onSuccess()
        fun onFailure(message: String)
        fun showStub(show: Boolean)

    }

    interface Presenter : BasePresenter {
        fun orderProducts(api_token: String,customer_id:String,payment_method:String,shipping_method:String,address_id:String,shipping_address_id:String,comment:String,affiliate_id:String)

    }


    interface Controller {
        fun orderProducts(route: String, api_token: String,customer_id:String,payment_method:String,shipping_method:String,address_id:String,shipping_address_id:String,comment:String,affiliate_id:String, listener: ICheckout.OnFinishListener?)

    }

    interface OnFinishListener : BaseResponse {
        fun onSuccess(message: String?)
        fun onFailure(message: String?)
        fun onNoData()
    }

}