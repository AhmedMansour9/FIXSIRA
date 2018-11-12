package com.example.ic.fixera.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ahmed on 20/10/2018.
 */

public class Spinner_CarModel {
    @SerializedName("car_model")
    @Expose
    private List<CarModel> carModel = null;

    public List<CarModel> getCarModel() {
        return carModel;
    }

    public void setCarModel(List<CarModel> carModel) {
        this.carModel = carModel;
    }

}
