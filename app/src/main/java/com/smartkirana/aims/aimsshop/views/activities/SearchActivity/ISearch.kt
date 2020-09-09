package com.smartkirana.aims.aimsshop.views.activities.SearchActivity

import com.smartkirana.aims.aimsshop.interfaces.BasePresenter
import com.smartkirana.aims.aimsshop.interfaces.BaseResponse
import com.smartkirana.aims.aimsshop.interfaces.BaseView

interface ISearch {
    interface View : BaseView {
        fun onSuccess(productListModel: ProductListModel)
        fun showStub(show: Boolean)
        fun onFailure(message: String?)
        fun onSuccessAddToCart(message: String?)
        fun onSuccessAWishList(message: String?)
    }

    interface Presenter : BasePresenter {
        fun getProductList()
        fun addToCart(product_id: String,api_token: String)
        fun addWishList(product_id: String ,api_token: String)
    }


    interface Controller {
        fun getProductList(map: Map<String?, String?>?, listener: OnFinishListener?)
        fun addToCart(route: String, api_token: String, product_id: String, listener: ISearch.OnFinishListener)
        fun addWishList(product_id: String,api_token: String, route: String, listener: ISearch.OnFinishListener)
    }

    interface OnFinishListener : BaseResponse {
        fun onSuccess(categoriesListModel: ProductListModel)
        fun onFailure(message: String?)
        fun onNoData()
        fun onSuccessAddToCart(message: String?)
        fun onSuccessAWishList(message: String?)
    }

}
