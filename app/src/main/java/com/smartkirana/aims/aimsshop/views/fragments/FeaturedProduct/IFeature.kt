package com.smartkirana.aims.aimsshop.views.fragments.FeaturedProduct

import com.smartkirana.aims.aimsshop.interfaces.BasePresenter
import com.smartkirana.aims.aimsshop.interfaces.BaseResponse
import com.smartkirana.aims.aimsshop.interfaces.BaseView

interface IFeature {
    interface View : BaseView {
        fun onSuccess(homeFeaturedModel: HomeFeaturedModel)
        fun showStub(show: Boolean)
        fun onFailure(message: String?)
        fun onSuccessAddToCart(message: String?)
        fun onSuccessAWishList(message: String?)
        fun onAddSuccess(message: String?)

    }


    interface Presenter : BasePresenter {
        fun getFeaturedList()
        fun addToCart(product_id: String,customer_id: String,api_token: String)
        fun addWishList(product_id: String,customer_id: String,api_token: String)
    }


    interface Controller {
        fun getFeaturedList(map: Map<String?, String?>?, listener: OnFinishListener?)
        fun addToCart(route: String, api_token: String, product_id: String, customer_id: String, listener:OnFinishListener)
        fun addWishList(product_id: String,customer_id:String,api_token: String, route: String, listener: OnFinishListener)
    }

    interface OnFinishListener : BaseResponse {
        fun onSuccess(homeFeaturedMode: HomeFeaturedModel)
        fun onAddSuccess(message: String?)
        fun onFailure(message: String?)
        fun onNoData()
        fun onSuccessAddToCart(message: String?)
        fun onSuccessAWishList(message: String?)
    }

}