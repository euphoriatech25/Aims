package com.smartkirana.aims.aimsshop.views.activities.Home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OutletModel {

    @SerializedName("outlets")
    @Expose
    private List<Outlet> outlets = null;

    public List<Outlet> getOutlets() {
        return outlets;
    }

    public void setOutlets(List<Outlet> outlets) {
        this.outlets = outlets;
    }

    public class Outlet {

        @SerializedName("outlet_id")
        @Expose
        private String outletId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("network_name")
        @Expose
        private String networkName;
        @SerializedName("network_security")
        @Expose
        private String networkSecurity;
        @SerializedName("network_password")
        @Expose
        private String networkPassword;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("sort_order")
        @Expose
        private String sortOrder;
        @SerializedName("created_date")
        @Expose
        private String createdDate;
        @SerializedName("updated_date")
        @Expose
        private String updatedDate;

        public String getOutletId() {
            return outletId;
        }

        public void setOutletId(String outletId) {
            this.outletId = outletId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getNetworkName() {
            return networkName;
        }

        public void setNetworkName(String networkName) {
            this.networkName = networkName;
        }

        public String getNetworkSecurity() {
            return networkSecurity;
        }

        public void setNetworkSecurity(String networkSecurity) {
            this.networkSecurity = networkSecurity;
        }

        public String getNetworkPassword() {
            return networkPassword;
        }

        public void setNetworkPassword(String networkPassword) {
            this.networkPassword = networkPassword;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSortOrder() {
            return sortOrder;
        }

        public void setSortOrder(String sortOrder) {
            this.sortOrder = sortOrder;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getUpdatedDate() {
            return updatedDate;
        }

        public void setUpdatedDate(String updatedDate) {
            this.updatedDate = updatedDate;
        }
    }
}
