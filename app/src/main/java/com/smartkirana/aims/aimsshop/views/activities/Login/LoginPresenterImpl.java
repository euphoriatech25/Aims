package com.smartkirana.aims.aimsshop.views.activities.Login;

import android.text.TextUtils;

import com.smartkirana.aims.aimsshop.utils.AppUtils;
import com.smartkirana.aims.aimsshop.views.activities.Register.CreateAccountModel;
import com.workerswallet.views.activities.login.ILogin;

import org.jetbrains.annotations.NotNull;

public class LoginPresenterImpl implements ILogin.Presenter, ILogin.OnFinishListener {
    private ILogin.View view;
    private LoginControllerImpl controller;

    public LoginPresenterImpl(ILogin.View view, LoginControllerImpl controller) {

        this.view = view;
        this.controller = controller;
    }

    @Override
    public void onSuccess(@NotNull CreateAccountModel createAccountModel) {
        if (view != null) {
            view.showProgressBar(false);
            view.onSuccess(createAccountModel);
        }
    }

    @Override
    public void onFailure(@NotNull String message) {
        if (view != null) {
            view.showProgressBar(false);
            view.onFailure(message);
        }
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
    public void loginUser() {
        if (view != null) {
            String email = view.getEmail();
            String password = view.getPassword();
            String term = "1";

            boolean hasError = false;
            if (TextUtils.isEmpty(email)) {
                hasError = true;
                view.setEmailError();
            }

            if (!TextUtils.isEmpty(email) && !AppUtils.isValidEmailId(email)) {
                hasError = true;
                view.setEmailInvalid();
            }

            if (TextUtils.isEmpty(password)) {
                hasError = true;
                view.setPasswordError();
            }


            if (!hasError) {
                view.showProgressBar(true);
                CreateAccountModel model = new CreateAccountModel();
                model.setEmail(email);
                model.setPassword(password);
                model.setTerm(term);
                controller.loginUser(model,this);
            }
        }

    }

    @Override
    public void onDestroy() {
        view = null;
    }
}