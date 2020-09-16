package com.smartkirana.aims.aimsshop.views.fragments.billingAddress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryModel {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("countries")
    @Expose
    private List<Country> countries = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
    public class Country {

        @SerializedName("country_id")
        @Expose
        private String countryId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("iso_code_2")
        @Expose
        private String isoCode2;
        @SerializedName("iso_code_3")
        @Expose
        private String isoCode3;
        @SerializedName("address_format")
        @Expose
        private String addressFormat;
        @SerializedName("postcode_required")
        @Expose
        private String postcodeRequired;
        @SerializedName("status")
        @Expose
        private String status;

        public String getCountryId() {
            return countryId;
        }

        public void setCountryId(String countryId) {
            this.countryId = countryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIsoCode2() {
            return isoCode2;
        }

        public void setIsoCode2(String isoCode2) {
            this.isoCode2 = isoCode2;
        }

        public String getIsoCode3() {
            return isoCode3;
        }

        public void setIsoCode3(String isoCode3) {
            this.isoCode3 = isoCode3;
        }

        public String getAddressFormat() {
            return addressFormat;
        }

        public void setAddressFormat(String addressFormat) {
            this.addressFormat = addressFormat;
        }

        public String getPostcodeRequired() {
            return postcodeRequired;
        }

        public void setPostcodeRequired(String postcodeRequired) {
            this.postcodeRequired = postcodeRequired;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }
}
