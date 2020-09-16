package com.smartkirana.aims.aimsshop.views.activities.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.utils.AppUtils;
import com.smartkirana.aims.aimsshop.utils.PrefConstants;
import com.smartkirana.aims.aimsshop.views.activities.Home.HomeActivity;
import com.smartkirana.aims.aimsshop.views.activities.Register.CreateAccountActivity;
import com.smartkirana.aims.aimsshop.views.activities.Register.CreateAccountModel;
import com.smartkirana.aims.aimsshop.views.activities.base.BaseActivity;
import com.workerswallet.views.activities.login.ILogin;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends BaseActivity implements ILogin.View, View.OnClickListener {
    EditText email, password;
    Button loginButton;
    private LoginPresenterImpl presenter;
    View view;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.loginButton);
        email = findViewById(R.id.editTextEmail);
        progressBar = findViewById(R.id.progressBar);
        password = findViewById(R.id.editTextPassword);
        email.setText("sas@gmail.com");
        password.setText("12345678");
        loginButton.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

    }

    public void onLoginClick(View View) {
        startActivity(new Intent(this, CreateAccountActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

    }

    @NotNull
    @Override
    public String getEmail() {
        return email.getText().toString();
    }

    @NotNull
    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void setEmailError() {
        AppUtils.setError(email);
    }

    @Override
    public void setPasswordError() {
        AppUtils.setError(password);
    }


    @Override
    public void setEmailInvalid() {
        AppUtils.invalidEmail(email);
    }

    @Override
    public void onSuccess(@NotNull CreateAccountModel createAccountModel) {
        CreateAccountModel.Customer customer = createAccountModel.getCustomer();

        SharedPreferences sharedPreferences = getSharedPreferences(PrefConstants.USER_DETAILS_PREF, MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString(PrefConstants.API_TOKEN, createAccountModel.getApiToken());
        myEdit.putString(PrefConstants.CUSTOMER_NAME, customer.getFullname());
        myEdit.putString(PrefConstants.CUSTOMER_ID, customer.getCustomerId());
        myEdit.apply();

        Toast.makeText(this, "Welcome " +customer.getFullname(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override
    public void onFailure(@NotNull String message) {
        AppUtils.showSnackBar(view, message);
    }

    @Override
    public void noInternetConnection() {
        Toast.makeText(this, "No Internet Available", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar(boolean showpBar) {
        AppUtils.showProgressBar(showpBar, progressBar);
    }

    @Override
    public void unKnownError() {
        Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                presenter = new LoginPresenterImpl(this, new LoginControllerImpl());
                presenter.loginUser();
                break;

        }
    }
}
