package com.smartkirana.aims.aimsshop.views.fragments.Categories

import com.smartkirana.aims.aimsshop.interfaces.BasePresenter
import com.smartkirana.aims.aimsshop.interfaces.BaseResponse
import com.smartkirana.aims.aimsshop.interfaces.BaseView
import com.smartkirana.aims.aimsshop.views.fragments.FeaturedProduct.HomeFeaturedModel

interface ICategories {
    interface View : BaseView {
        fun onSuccess(categoriesModel: CategoriesModel)
        fun showStub(show: Boolean)
        fun onFailure(message: String?)
    }

    interface Presenter : BasePresenter {

        fun getFeatureCategoriesList()

    }


    interface Controller {
        fun getFeatureCategoriesList(listener: OnFinishListener?)

    }

    interface OnFinishListener : BaseResponse {
        fun onSuccess(categoriesModel: CategoriesModel)

        fun onFailure(message: String?)
        fun onNoData()
    }
}