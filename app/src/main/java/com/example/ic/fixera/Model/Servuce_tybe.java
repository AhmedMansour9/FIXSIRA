package com.example.ic.fixera.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ahmed on 20/10/2018.
 */

public class Servuce_tybe {


    @SerializedName("services_type")
    @Expose
    private List<ServicesType> servicesType = null;

    public List<ServicesType> getServicesType() {
        return servicesType;
    }

    public void setServicesType(List<ServicesType> servicesType) {
        this.servicesType = servicesType;
    }
}
