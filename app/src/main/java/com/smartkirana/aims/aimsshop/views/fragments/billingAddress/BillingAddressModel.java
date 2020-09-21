package com.smartkirana.aims.aimsshop.views.fragments.billingAddress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillingAddressModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("address")
    @Expose
    private Address address;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static class Address {

        @SerializedName("address_id")
        @Expose
        private String address_id;

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

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

        @SerializedName("region_state")
        @Expose
        private String region_state;

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getAddress_1() {
            return address_1;
        }

        public void setAddress_1(String address_1) {
            this.address_1 = address_1;
        }

        public String getAddress_2() {
            return address_2;
        }

        public void setAddress_2(String address_2) {
            this.address_2 = address_2;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZone_id() {
            return zone_id;
        }

        public void setZone_id(String zone_id) {
            this.zone_id = zone_id;
        }

        public String getCountry_id() {
            return country_id;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }

        public String getRegion_state() {
            return region_state;
        }

        public void setRegion_state(String region_state) {
            this.region_state = region_state;
        }
    }
}
