package com.smartkirana.aims.aimsshop.views.fragments.ProductList

import com.smartkirana.aims.aimsshop.interfaces.BasePresenter
import com.smartkirana.aims.aimsshop.interfaces.BaseResponse
import com.smartkirana.aims.aimsshop.interfaces.BaseView

internal interface IProductList {
    interface View : BaseView {
        fun setProductId(): String
        fun setThumb(): String
        fun setName(): String
        fun setDescription(): String
        fun setPrice(): String
        fun setSpecial(): String
        fun setTax(): String
        fun setMinimum(): String
        fun setRating(): String
        fun setHref(): String
    }

    interface Presenter : BasePresenter {
        fun getProductList()
    }

    interface Controller {
        fun getProductList(map: Map<String?, String?>?, listener: OnFinishListener?)
    }

    interface OnFinishListener : BaseResponse {
        fun onSuccess(productListModel: ProductListModel)
    }


}