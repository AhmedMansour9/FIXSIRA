package com.example.ic.fixera.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed on 20/10/2018.
 */

public class CarModel {

    @SerializedName("manufacturer")
    @Expose
    private String manufacturer;
    @SerializedName("manufacturer_id")
    @Expose
    private Integer manufacturerId;


    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    @Override
    public String toString() {
        return manufacturer;
    }
}
