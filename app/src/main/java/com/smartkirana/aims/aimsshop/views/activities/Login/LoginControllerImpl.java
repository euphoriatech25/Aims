package com.smartkirana.aims.aimsshop.views.activities.Login;

import com.smartkirana.aims.aimsshop.network.RetrofitInterface;
import com.smartkirana.aims.aimsshop.network.ServiceConfig;
import com.smartkirana.aims.aimsshop.views.activities.Register.CreateAccountModel;
import com.workerswallet.views.activities.login.ILogin;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginControllerImpl implements ILogin.Controller{
    @Override
    public void loginUser(@NotNull CreateAccountModel model, @NotNull ILogin.OnFinishListener listener) {
            RetrofitInterface post = ServiceConfig.createService(RetrofitInterface.class);
            Call<CreateAccountModel> call = post.loginUser(model);
            call.enqueue(new Callback<CreateAccountModel>() {
                @Override
                public void onResponse(Call<CreateAccountModel> call, Response<CreateAccountModel> response) {
                    if (response.isSuccessful()) {

                        if (response.body() != null) {
                            CreateAccountModel createAccountModel = response.body();
                            listener.onSuccess(createAccountModel);
//                        String api_token = createAccountModel.getApiToken();
//                        PrefHelper.setUserDetails(PrefConstants.API_TOKEN, api_token);
//                        Log.i(PrefConstants.API_TOKEN, api_token);
                        } else {
                            listener.unKnownError();
                        }

                    } else {
                        listener.unKnownError();
                    }

                }

                @Override
                public void onFailure(Call<CreateAccountModel> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        listener.connectionTimeOut();
                    } else {
                        listener.unKnownError();
                    }
                }
            });
        }
    }
