package com.smartkirana.aims.aimsshop.views.activities.WishList

import com.smartkirana.aims.aimsshop.interfaces.BasePresenter
import com.smartkirana.aims.aimsshop.interfaces.BaseResponse
import com.smartkirana.aims.aimsshop.interfaces.BaseView
import com.smartkirana.aims.aimsshop.views.activities.SubCategories.ISubCategories
import com.smartkirana.aims.aimsshop.views.activities.SubCategories.SubCategoriesModel

internal interface IWishList {

    interface View : BaseView {
        fun onSuccess(wishListModel: WishListModel)
        fun showStub(show: Boolean)
        fun onFailure(message: String?)
        fun onRemoveWishListSuccess(message: String?)
        fun onAddtoCartSuccess(message: String?)
    }

    interface Presenter : BasePresenter {
        fun getWishList(api_token:String)
        fun removeWishList(api_token: String,product_id:String)
        fun addToCart(api_token: String,product_id:String)
    }


    interface Controller {
        fun getWishList(route: String, api_token:String, listener: IWishList.OnFinishListener)
        fun removeWishList(route: String, api_token: String,product_id:String, listener: IWishList.OnFinishListener)
        fun addToCart(route: String,api_token: String,product_id:String,listener: IWishList.OnFinishListener)
    }

    interface OnFinishListener : BaseResponse {
        fun onSuccess(wishListModel: WishListModel)
        fun onRemoveWishListSuccess(message: String?)
        fun onAddtoCartSuccess(message: String?)
        fun onFailure(message: String?)
        fun onNoData()
    }
}