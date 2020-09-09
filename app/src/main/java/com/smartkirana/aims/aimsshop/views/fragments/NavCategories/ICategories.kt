package com.smartkirana.aims.aimsshop.views.fragments.NavCategories

import com.smartkirana.aims.aimsshop.interfaces.BasePresenter
import com.smartkirana.aims.aimsshop.interfaces.BaseResponse
import com.smartkirana.aims.aimsshop.interfaces.BaseView

internal interface ICategories {
    interface View : BaseView {
        fun onSuccess(categoriesListModel:CategoriesListModel)
        fun showStub(show: Boolean)
        fun onFailure(message: String?)
    }

    interface Presenter : BasePresenter {
        fun getCategoriesList()
    }


    interface Controller {
        fun getCategoriesList(listener: OnFinishListener?)
    }

    interface OnFinishListener : BaseResponse {
        fun onSuccess(categoriesListModel:CategoriesListModel)
        fun onFailure(message: String?)
        fun onNoData()
    }

}