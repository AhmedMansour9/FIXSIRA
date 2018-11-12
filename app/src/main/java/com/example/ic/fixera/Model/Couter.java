package com.example.ic.fixera.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed on 17/10/2018.
 */

public class Couter {

    @SerializedName("count_cart")
    @Expose
    private Integer countCart;

    public Integer getCountCart() {
        return countCart;
    }

    public void setCountCart(Integer countCart) {
        this.countCart = countCart;
    }
}
