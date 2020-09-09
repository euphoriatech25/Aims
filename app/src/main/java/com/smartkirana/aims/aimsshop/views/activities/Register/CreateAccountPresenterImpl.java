package com.smartkirana.aims.aimsshop.views.activities.Register;

import android.text.TextUtils;

import com.smartkirana.aims.aimsshop.utils.AppUtils;
import com.smartkirana.aims.aimsshop.views.activities.createAccount.ICreateAccount;

import org.jetbrains.annotations.NotNull;

public class CreateAccountPresenterImpl implements ICreateAccount.Presenter, ICreateAccount.OnFinishListener {

  private static final String TAG = "CREATEACCOUNTACITIVITY";
  private ICreateAccount.View view;
  private CreateAccountControllerImpl controller;

  public CreateAccountPresenterImpl(ICreateAccount.View view, CreateAccountControllerImpl controller) {

    this.view = view;
    this.controller = controller;
  }

  @Override
  public void createAccount() {
    if (view != null) {
      String firstName = view.getFirstName();
      String lastName = view.getLastName();
      String email = view.getEmail();
      String password = view.getPassword();
      String telephone = view.gettelephone();
      String confirmPassword = view.getConfirmPassword();
      String term = "1";

      boolean hasError = false;
      if (TextUtils.isEmpty(firstName)) {
        hasError = true;
        view.setFirstNameError();
      }
      if (TextUtils.isEmpty(lastName)) {
        hasError = true;
        view.setLastNameError();
      }

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
      if (TextUtils.isEmpty(confirmPassword)) {
        hasError = true;
        view.setConfirmError();
      }


      if (!hasError) {
        view.showProgressBar(true);
        CreateAccountModel model = new CreateAccountModel();
        model.setFirstName(firstName);
        model.setLastName(lastName);
        model.setEmail(email);
        model.setTelephone(telephone);
        model.setPassword(password);
        model.setConfirm(confirmPassword);
        model.setTerm(term);
        controller.createAccount(model, this);
      }
    }
  }
  @Override
  public void onSuccess(CreateAccountModel createAccountModel) {
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
  public void onDestroy() {
    view = null;
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
}