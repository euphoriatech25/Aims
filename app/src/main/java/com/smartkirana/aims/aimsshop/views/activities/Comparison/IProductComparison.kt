package com.smartkirana.aims.aimsshop.views.activities.Comparison

import com.smartkirana.aims.aimsshop.interfaces.BasePresenter
import com.smartkirana.aims.aimsshop.interfaces.BaseResponse
import com.smartkirana.aims.aimsshop.interfaces.BaseView
import com.smartkirana.aims.aimsshop.views.activities.ProductDetails.IProductDetails
import com.smartkirana.aims.aimsshop.views.activities.WishList.IWishList
import com.smartkirana.aims.aimsshop.views.activities.WishList.WishListModel

internal interface IProductComparison {

    interface View : BaseView {
        fun showStub(show: Boolean)
        fun onFailure(message: String?)
        fun onCompareProductSuccessPost(compareModel: CompareModel)

    }

    interface Presenter : BasePresenter {
        fun onCompareProductPost(product_id: ArrayList<String>)
    }


    interface Controller {
         fun onCompareProductPost(route: String?, product_id:ArrayList<String>, listener: IProductComparison.OnFinishListener)
    }

    interface OnFinishListener : BaseResponse {
        fun onCompareProductSuccessPost(compareModel: CompareModel)
        fun onFailure(message: String?)
        fun onNoData()
    }
}