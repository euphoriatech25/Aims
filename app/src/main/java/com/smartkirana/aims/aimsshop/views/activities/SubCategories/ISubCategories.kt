package com.smartkirana.aims.aimsshop.views.activities.SubCategories

import com.smartkirana.aims.aimsshop.interfaces.BasePresenter
import com.smartkirana.aims.aimsshop.interfaces.BaseResponse
import com.smartkirana.aims.aimsshop.interfaces.BaseView
import com.smartkirana.aims.aimsshop.views.activities.ProductDetails.IProductDetails
import com.smartkirana.aims.aimsshop.views.fragments.FeaturedProduct.IFeature
import com.smartkirana.aims.aimsshop.views.fragments.NavCategories.CategoriesListModel

internal interface ISubCategories {
    interface View : BaseView {
        fun onSuccess(subCategoriesModel: SubCategoriesModel)
        fun showStub(show: Boolean)
        fun onFailure(message: String?)
        fun onSuccessAddToCart(message: String?)
        fun onSuccessWishList(message: String?)
    }

    interface Presenter : BasePresenter {
        fun getSubCategoriesList(path: String)
        fun addToCart(product_id: String,customer_id: String,api_token: String)
        fun addWishList(product_id: String,customer_id: String,api_token: String)
    }


    interface Controller {
        fun getProductList(route :String, path:String,  listener: ISubCategories.OnFinishListener) {
        }
        fun addToCart(route: String, api_token: String, product_id: String,customer_id: String,listener: ISubCategories.OnFinishListener)
        fun addWishList(product_id: String,customer_id: String,api_token: String, route: String, listener: ISubCategories.OnFinishListener)
    }

    interface OnFinishListener : BaseResponse {
        fun onSuccess(subCategoriesModel: SubCategoriesModel)
        fun onFailure(message: String?)
        fun onNoData()
        fun onSuccessAddToCart(message: String?)
        fun onSuccessWishList(message: String?)
    }
}