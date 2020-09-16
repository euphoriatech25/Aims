package com.smartkirana.aims.aimsshop.views.fragments.billingAddress;

import android.text.TextUtils;
import android.util.Log;

import com.smartkirana.aims.aimsshop.utils.EndPoints;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BillingAddressPresenterImpl implements IBillingAddress.Presenter, IBillingAddress.OnFinishListener {
    private IBillingAddress.View view;
    private IBillingAddress.Controller controller;

    public BillingAddressPresenterImpl(IBillingAddress.View view, IBillingAddress.Controller controller) {
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void onSuccess(@Nullable String message) {
        if (view != null) {
            view.showProgressBar(false);
            view.onSuccess();
        }
    }

    @Override
    public void onFailure(@Nullable String message) {
        if (view != null) {
            view.showProgressBar(false);
            view.onFailure(message);
        }
    }

    @Override
    public void onNoData() {

    }

    @Override
    public void noInternetConnection() {
        if (view != null) {
            view.showProgressBar(false);
            view.noInternetConnection();
        }
    }

    @Override
    public void connectionTimeOut() {
        if (view != null) {
            view.showProgressBar(false);
            view.connectionTimeOut();
        }
    }

    @Override
    public void unKnownError() {
        if (view != null) {
            view.showProgressBar(false);
            view.unKnownError();
        }
    }

    @Override
    public void addAddress(@NotNull String api_token,@NotNull String customer_id) {
        if (view != null) {
            String firstName = view.getFirstName();
            String lastName = view.getLastName();
            String companyName = view.getCompanyName();
            String address1 = view.getAddress1();
            String address2 = view.getAddress2();
            String city = view.getCity();
            String postCode = view.getPostCode();
            String region = view.getRegion();
            String country = view.getCountry();

            boolean hasError = false;
            if (TextUtils.isEmpty(firstName)) {
                hasError = true;
                view.setFirstName();
            }
            if (TextUtils.isEmpty(lastName)) {
                hasError = true;
                view.setLastName();
            }
            if (TextUtils.isEmpty(address1)) {
                hasError = true;
                view.setAddress1();
            }
            if (TextUtils.isEmpty(city)) {
                hasError = true;
                view.setCity();
            }
            if (TextUtils.isEmpty(country)) {
                hasError = true;
                view.setCountry();
            }
            if (TextUtils.isEmpty(region)) {
                hasError = true;
                view.setRegion();
            }

            if (!hasError) {
                view.showProgressBar(true);
                BillingAddressModel model = new BillingAddressModel();
                model.setFirstname(firstName);
                model.setLastname(lastName);
                model.setCompany(companyName);
                model.setCity(city);
                model.setAddress_1(address1);
                model.setAddress_2(address2);
                model.setPostcode(postCode);
                model.setRegion_state(region);
                model.setCountry_id(country);
                Log.e("Region",region);
                controller.addAddress(EndPoints.ADDRESS_ADD,api_token, customer_id, model, this);
            }
        }

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getAvailableAddress(@NotNull String api_token, @NotNull String customer_id) {
        controller.getAvailableAddress(EndPoints.ADDRESS_GET,api_token, customer_id, this);
    }



    @Override
    public void getAvailableCountries(@NotNull String api_token) {
        controller.getAvailableCountries(EndPoints.AVAILABLE_COUNTRIES,api_token, this);

    }

    @Override
    public void getAvailableState(@NotNull String api_token, @NotNull String country_id) {
        controller.getAvailableState(EndPoints.AVAILABLE_STATE,api_token, country_id,this);

    }

    @Override
    public void onSuccessCountry(@NotNull CountryModel countryModel) {
        if (view != null) {
            view.onSuccessCountry(countryModel);
            view.showProgressBar(false);
        }
    }

    @Override
    public void onSuccessState(@NotNull StateModel stateModel) {
        if (view != null) {
            view.onSuccessState(stateModel);
            view.showProgressBar(false);
        }
    }

    @Override
    public void onSuccessAvailableAddress(@NotNull AvailableModel availableModel) {
        if (view != null) {
            view.onSuccessAvailableAddress(availableModel);
            view.showProgressBar(false);
        }
    }
}
