package com.workerswallet.views.activities.login

import com.smartkirana.aims.aimsshop.interfaces.BasePresenter
import com.smartkirana.aims.aimsshop.interfaces.BaseResponse
import com.smartkirana.aims.aimsshop.interfaces.BaseView
import com.smartkirana.aims.aimsshop.views.activities.Register.CreateAccountModel

internal interface ILogin {
    interface View : BaseView {
        fun getEmail(): String
        fun getPassword(): String
        fun setEmailError()
        fun setPasswordError()
        fun setEmailInvalid()
        fun onSuccess(createAccountModel: CreateAccountModel)
        fun onFailure(message: String)
    }
    interface Presenter : BasePresenter {
        fun loginUser()
    }

    interface Controller {
        fun loginUser(
                model: CreateAccountModel,
                listener: OnFinishListener
        )
    }
    interface OnFinishListener : BaseResponse {
        fun onSuccess(createAccountModel: CreateAccountModel)
        fun onFailure(message: String)
    }

}