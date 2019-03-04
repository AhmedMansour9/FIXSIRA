package com.example.ic.fixera.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed on 20/10/2018.
 */

public class ServicesType {

    @SerializedName("servicesId")
    @Expose
    private String servicesId;
    @SerializedName("ServiceName")
    @Expose
    private String serviceName;
    @SerializedName("ForServices")
    @Expose
    private String forServices;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("vendorId")
    @Expose
    private String vendorId;

    public String getServicesId() {
        return servicesId;
    }

    public void setServicesId(String servicesId) {
        this.servicesId = servicesId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getForServices() {
        return forServices;
    }

    public void setForServices(String forServices) {
        this.forServices = forServices;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    @Override
    public String toString() {
        return serviceName;
    }
}
