package com.smartkirana.aims.aimsshop.views.activities.Register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateAccountModel {
    private String firstname;
    private String lastname;
    private String telephone;
    private String email;
    private String password;
    private String confirm;
    private String agree;


    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("api_token")
    @Expose
    private String apiToken;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }



    public String getTerm() {
        return agree;
    }

    public void setTerm(String term) {
        this.agree = term;
    }


    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}