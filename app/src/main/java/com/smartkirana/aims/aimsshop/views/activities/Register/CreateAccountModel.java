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

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("customer")
    @Expose
    private Customer customer;

    @SerializedName("success")
    @Expose
    private String success;

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    private String username;
    private String key;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }



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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public class Customer {
        @SerializedName("customer_id")
        @Expose
        private String customerId;
        @SerializedName("fullname")
        @Expose
        private String fullname;
        @SerializedName("customer_token")
        @Expose
        private String customerToken;

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getCustomerToken() {
            return customerToken;
        }

        public void setCustomerToken(String customerToken) {
            this.customerToken = customerToken;
        }
    }
}