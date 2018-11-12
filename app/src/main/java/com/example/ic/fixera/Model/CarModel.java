package com.example.ic.fixera.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed on 20/10/2018.
 */

public class CarModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("models")
    @Expose
    private String models;
    @SerializedName("manufacturer")
    @Expose
    private String manufacturer;
    @SerializedName("manufacturer_id")
    @Expose
    private Integer manufacturerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }

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
        return models;
    }
}
