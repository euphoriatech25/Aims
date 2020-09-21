package com.smartkirana.aims.aimsshop.views.fragments.billingAddress

import com.smartkirana.aims.aimsshop.interfaces.BasePresenter
import com.smartkirana.aims.aimsshop.interfaces.BaseResponse
import com.smartkirana.aims.aimsshop.interfaces.BaseView

interface IBillingAddress {
    interface View : BaseView {
        fun getFirstName(): String
        fun getLastName(): String
        fun getCompanyName(): String
        fun getAddress1(): String
        fun getAddress2(): String
        fun getCity(): String
        fun getPostCode(): String
        fun getCountry(): String
        fun getRegion(): String
        fun setFirstName()
        fun setLastName()
        fun setAddress1()
        fun setCity()
        fun setCountry()
        fun setRegion()

        fun onSuccess(addressId:String)
        fun onSuccessAvailableAddress(availableModel:AvailableModel)
        fun onSuccessCountry(countryModel: CountryModel)
        fun onSuccessState(stateModel: StateModel)
        fun onFailure(message: String)
        fun showStub(show: Boolean)

    }

    interface Presenter : BasePresenter {
        fun addAddress(api_token: String,customer_id: String)
        fun getAvailableAddress(api_token: String,customer_id: String)
        fun getAvailableCountries(api_token: String)
        fun getAvailableState(api_token: String,country_id: String)
    }


    interface Controller {
        fun addAddress(route: String, api_token: String, customer_id: String, billingAddressModel: BillingAddressModel.Address, listener: OnFinishListener)
        fun getAvailableAddress(route: String, api_token: String, customer_id: String,  listener: OnFinishListener)
        fun getAvailableCountries(route: String, api_token: String,  listener: OnFinishListener)
        fun getAvailableState(route: String, api_token: String,country_id: String, listener: OnFinishListener)
    }

    interface OnFinishListener : BaseResponse {
        fun onSuccess(addressId:String?)
        fun onSuccessCountry(countryModel: CountryModel)
        fun onSuccessAvailableAddress(availableModel:AvailableModel)
        fun onSuccessState(stateModel: StateModel)
        fun onFailure(message: String?)
        fun onNoData()
    }

}