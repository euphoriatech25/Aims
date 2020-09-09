package com.smartkirana.aims.aimsshop.views.activities.Cart

import com.smartkirana.aims.aimsshop.interfaces.BasePresenter
import com.smartkirana.aims.aimsshop.interfaces.BaseResponse
import com.smartkirana.aims.aimsshop.interfaces.BaseView

internal interface ICart {
    interface View : BaseView {
        fun onSuccess(cartModel: CartModel)
        fun onSuccessEditRemove(message: String?)
        fun showStub(show: Boolean)
        fun onFailure(message: String?)
    }

    interface Presenter : BasePresenter {
        fun getCartProductList(api_token: String)
        fun editCartProduct(key: String,quantity:String,api_token: String)
        fun removeCartProduct(key: String,api_token: String)

    }
    interface Controller {
        fun getCartProductList(route: String, api_token: String, listener: OnFinishListener?)
        fun editProductList(route: String, api_token: String, key: String ,quantity:String,listener: OnFinishListener?)
        fun removeCartProduct(route: String, api_token: String, key: String,listener: OnFinishListener?)
    }
    interface OnFinishListener : BaseResponse {
        fun onSuccess(cartModel: CartModel)
        fun onSuccessEditRemove(message: String?)
        fun onFailure(message: String?)
        fun onNoData()
    }

}
