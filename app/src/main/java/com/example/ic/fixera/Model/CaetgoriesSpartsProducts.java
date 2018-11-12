package com.example.ic.fixera.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ic on 10/9/2018.
 */

public class CaetgoriesSpartsProducts {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("categories")
    @Expose
    private List<Sparts_Category> products = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Sparts_Category> getProducts() {
        return products;
    }

    public void setProducts(List<Sparts_Category> products) {
        this.products = products;
    }

}
