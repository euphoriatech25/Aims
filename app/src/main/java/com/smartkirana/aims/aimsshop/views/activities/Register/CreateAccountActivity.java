package com.smartkirana.aims.aimsshop.views.activities.Register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.utils.AppUtils;
import com.smartkirana.aims.aimsshop.utils.PrefConstants;
import com.smartkirana.aims.aimsshop.views.activities.Home.HomeActivity;
import com.smartkirana.aims.aimsshop.views.activities.Login.LoginActivity;
import com.smartkirana.aims.aimsshop.views.activities.base.BaseActivity;
import com.smartkirana.aims.aimsshop.views.activities.createAccount.ICreateAccount;

import org.jetbrains.annotations.NotNull;

//import static com.smartkirana.aims.aimsshop.MyApp.getContext;

public class CreateAccountActivity extends BaseActivity implements ICreateAccount.View {
    EditText firstname, lastname, email, password, confirm, telephone;
    Button createAccount;
    private CreateAccountPresenterImpl presenter;
    View view;
    ProgressBar progressBar;
    ScrollView registerScroll;
    CheckBox term;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         init();
    }

    private void init() {
        firstname = findViewById(R.id.editTextFirstName);
        lastname = findViewById(R.id.editTextLastName);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        confirm = findViewById(R.id.editTextConfirmPassword);
        telephone = findViewById(R.id.editTextMobile);
        createAccount = findViewById(R.id.btRegister);
        progressBar = findViewById(R.id.progressBar);
        registerScroll = findViewById(R.id.registerScroll);
        term=findViewById(R.id.termandcondition);
        firstname.setText("Sadikshya");
        lastname.setText("Mishra");
        password.setText("12345678");
        confirm.setText("12345678");
        telephone.setText("9842554500");
        email.setText("@gmail.com");
        changeStatusBarColor();
        presenter = new CreateAccountPresenterImpl(this, new CreateAccountControllerImpl());
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (term.isChecked()) {
                    registerScroll.setScrollY(0);
                    presenter.createAccount();
                } else {
                    Toast.makeText(CreateAccountActivity.this, "Accept Terms and Conditions", Toast.LENGTH_SHORT).show();     }
            }
        });
    }

    private void changeStatusBarColor() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.account));

    }

    public void onLoginClick(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

    @Override
    public void showProgressBar(boolean showProgressBar) {
        AppUtils.showProgressBar(showProgressBar, progressBar);
    }

    @NotNull
    @Override
    public String getFirstName() {
        return firstname.getText().toString();
    }

    @NotNull
    @Override
    public String getLastName() {
        return lastname.getText().toString();
    }

    @NotNull
    @Override
    public String gettelephone() {
        return telephone.getText().toString();
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

    @NotNull
    @Override
    public String getConfirmPassword() {
        return confirm.getText().toString();
    }

    @Override
    public void setFirstNameError() {
        AppUtils.setError(firstname);
    }

    @Override
    public void setLastNameError() {
        AppUtils.setError(lastname);
    }

    @Override
    public void setTelephoneError() {
        AppUtils.setError(telephone);
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
    public void setConfirmError() {
        AppUtils.setError(confirm);
    }

    @Override
    public void setEmailInvalid() {

        AppUtils.invalidEmail(email);
    }

    @Override
    public void onSuccess(@NotNull CreateAccountModel createAccountModel) {
        SharedPreferences sharedPreferences =getSharedPreferences(PrefConstants.USER_DETAILS_PREF, MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString(PrefConstants.API_TOKEN,createAccountModel.getApiToken());
        myEdit.apply();

        Toast.makeText(this, R.string.success_register, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override
    public void onFailure(@NotNull String message) {
        AppUtils.showSnackBar(view, message);
    }

    @Override
    public void noInternetConnection() {

    }

    @Override
    public void connectionTimeOut() {

    }


    @Override
    public void unKnownError() {

    }
    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}