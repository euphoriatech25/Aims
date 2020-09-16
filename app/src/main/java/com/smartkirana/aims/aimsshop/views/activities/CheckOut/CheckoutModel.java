package com.smartkirana.aims.aimsshop.views.activities.CheckOut;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckoutModel {
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("address_1")
    @Expose
    private String address_1;
    @SerializedName("address_2")
    @Expose
    private String address_2;
    @SerializedName("postcode")
    @Expose
    private String postcode;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("zone_id")
    @Expose
    private String zone_id;
    @SerializedName("country_id")
    @Expose
    private String country_id;

    public CheckoutModel(String firstname, String lastname, String company, String address_1, String address_2, String postcode, String city, String zone_id, String country_id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.postcode = postcode;
        this.city = city;
        this.zone_id = zone_id;
        this.country_id = country_id;
    }
}