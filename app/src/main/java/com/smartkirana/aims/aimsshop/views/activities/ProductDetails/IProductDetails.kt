package com.smartkirana.aims.aimsshop.views.activities.ProductDetails

import com.smartkirana.aims.aimsshop.interfaces.BasePresenter
import com.smartkirana.aims.aimsshop.interfaces.BaseResponse
import com.smartkirana.aims.aimsshop.interfaces.BaseView
import com.smartkirana.aims.aimsshop.views.fragments.FeaturedProduct.IFeature

internal interface IProductDetails {
    interface View : BaseView {
        fun onSuccess(productDetails: ProductDetailsModel)
        fun showStub(show: Boolean)
        fun onFailure(message: String?)
        fun onSuccessAddToCart(message: String?)
        fun onSuccessWishList(message: String?)

    }

    interface Presenter : BasePresenter {
        fun addToCart(product_id: String, api_token: String)
        fun getProductList(productId: String)
        fun addWishList(product_id: String, api_token: String)
    }


    interface Controller {
        fun getProductList(route :String, product_id:String,  listener: OnFinishListener )
        fun addToCart(route: String, api_token: String, product_id: String, listener: IProductDetails.OnFinishListener)
        fun addWishList(route: String, api_token: String,product_id: String,listener: IProductDetails.OnFinishListener)

    }

    interface OnFinishListener : BaseResponse {
        fun onSuccessAddToCart(message: String?)
        fun onSuccessWishList(message: String?)
        fun onSuccess(productDetails: ProductDetailsModel)
        fun onFailure(message: String?)
        fun onNoData()
    }

}