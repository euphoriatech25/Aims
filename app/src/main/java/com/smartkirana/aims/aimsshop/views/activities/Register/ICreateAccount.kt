package com.smartkirana.aims.aimsshop.views.activities.createAccount

import com.smartkirana.aims.aimsshop.interfaces.BasePresenter
import com.smartkirana.aims.aimsshop.interfaces.BaseResponse
import com.smartkirana.aims.aimsshop.interfaces.BaseView
import com.smartkirana.aims.aimsshop.views.activities.Register.CreateAccountModel



internal interface ICreateAccount {
    interface View : BaseView {
        fun getFirstName(): String
        fun getLastName(): String
        fun gettelephone(): String
        fun getEmail(): String
        fun getPassword(): String
        fun getConfirmPassword(): String
        fun setFirstNameError()
        fun setLastNameError()
        fun setTelephoneError()
        fun setEmailError()
        fun setPasswordError()
        fun setConfirmError()
        fun setEmailInvalid()
        fun onSuccess(createAccountModel: CreateAccountModel)
        fun onFailure(message: String)
    }

    interface Presenter : BasePresenter {
        fun createAccount()
    }

    interface Controller {
        fun createAccount(
                model: CreateAccountModel,
                listener: OnFinishListener
        )
    }
    interface OnFinishListener : BaseResponse {
        fun onSuccess(createAccountModel: CreateAccountModel)
        fun onFailure(message: String)
    }
}