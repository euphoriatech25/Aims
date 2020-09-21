package com.smartkirana.aims.aimsshop.views.fragments.billingAddress;

import com.smartkirana.aims.aimsshop.network.RetrofitInterface;
import com.smartkirana.aims.aimsshop.network.ServiceConfig;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillingAddressControllerImpl implements IBillingAddress.Controller {
    @Override
    public void addAddress(@NotNull String route, @NotNull String api_token, @NotNull String customer_id, @NotNull BillingAddressModel.Address billingAddressModel, @NotNull IBillingAddress.OnFinishListener listener) {
        RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);
        Call<BillingAddressModel> call = post.addAddress(route, api_token, customer_id,
                billingAddressModel.getFirstname(),
                billingAddressModel.getLastname(),
                billingAddressModel.getCompany(),
                billingAddressModel.getAddress_1(),
                billingAddressModel.getAddress_2(),
                billingAddressModel.getPostcode(),
                billingAddressModel.getCity(),
                "2315",
                billingAddressModel.getCountry_id());
        call.enqueue(new Callback<BillingAddressModel>() {
            @Override
            public void onResponse(Call<BillingAddressModel> call, Response<BillingAddressModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        BillingAddressModel model=response.body();
                        BillingAddressModel.Address address=model.getAddress();
                        String addressId=address.getAddress_id();
                        listener.onSuccess(addressId);
                    } else {
                        listener.onNoData();
                    }
                } else {
                    listener.unKnownError();
                }


            }

            @Override
            public void onFailure(Call<BillingAddressModel> call, Throwable throwable) {

            }
        });
    }

    @Override
    public void getAvailableAddress(@NotNull String route, @NotNull String api_token, @NotNull String customer_id, @NotNull IBillingAddress.OnFinishListener listener) {
        RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);
        Call<AvailableModel> call = post.getAddress(route, api_token,customer_id);
        call.enqueue(new Callback<AvailableModel>() {
            @Override
            public void onResponse(Call<AvailableModel> call, Response<AvailableModel> response) {
                if (response.isSuccessful()) {
                    AvailableModel availableModel = response.body();
                    listener.onSuccessAvailableAddress(availableModel);
                } else {
                    listener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<AvailableModel> call, Throwable t) {

            }
        });

    }

    @Override
    public void getAvailableCountries(@NotNull String route, @NotNull String api_token, @NotNull IBillingAddress.OnFinishListener listener) {
        RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);
        Call<CountryModel> call = post.getCountry(route, api_token);
        call.enqueue(new Callback<CountryModel>() {
            @Override
            public void onResponse(Call<CountryModel> call, Response<CountryModel> response) {
                if (response.isSuccessful()) {
                    CountryModel countryModel = response.body();
                    listener.onSuccessCountry(countryModel);
                } else {
                    listener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<CountryModel> call, Throwable throwable) {

            }
        });

    }

    @Override
    public void getAvailableState(@NotNull String route, @NotNull String api_token, @NotNull String country_id, @NotNull IBillingAddress.OnFinishListener listener) {
        RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);
        Call<StateModel> call = post.getState(route, api_token,country_id);
        call.enqueue(new Callback<StateModel>() {
            @Override
            public void onResponse(Call<StateModel> call, Response<StateModel> response) {
                if(response.isSuccessful()){
                    StateModel stateModel = response.body();
                    listener.onSuccessState(stateModel);
                } else {
                    listener.onFailure(response.message());
                }

            }

            @Override
            public void onFailure(Call<StateModel> call, Throwable throwable) {

            }
        });
    }

}
