package com.example.ic.fixera.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ic on 10/7/2018.
 */

public class Sparts_Data {
    @SerializedName("products")
    @Expose
    private Sparts_Pages products;

    public Sparts_Pages getProducts() {
        return products;
    }

    public void setProducts(Sparts_Pages products) {
        this.products = products;
    }
}
