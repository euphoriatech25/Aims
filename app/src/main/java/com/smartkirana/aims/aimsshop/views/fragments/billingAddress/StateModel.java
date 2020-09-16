package com.smartkirana.aims.aimsshop.views.fragments.billingAddress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StateModel {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("zone")
    @Expose
    private List<Zone> zone = null;

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

    public List<Zone> getZone() {
        return zone;
    }

    public void setZone(List<Zone> zone) {
        this.zone = zone;
    }

    public class Zone {

        @SerializedName("zone_id")
        @Expose
        private String zoneId;
        @SerializedName("country_id")
        @Expose
        private String countryId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("status")
        @Expose
        private String status;

        public String getZoneId() {
            return zoneId;
        }

        public void setZoneId(String zoneId) {
            this.zoneId = zoneId;
        }

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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
