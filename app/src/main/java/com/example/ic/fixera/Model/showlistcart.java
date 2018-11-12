package com.example.ic.fixera.Model;

import com.example.ic.fixera.Model.Cart;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ahmed on 14/10/2018.
 */

public class showlistcart {
    @SerializedName("products")
    @Expose
    private List<Cart> products = null;
    @SerializedName("total_price")
    @Expose
    private Integer totalPrice;

    public List<Cart> getProducts() {
        return products;
    }

    public void setProducts(List<Cart> products) {
        this.products = products;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

}
