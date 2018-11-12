package com.example.ic.fixera.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ahmed on 17/10/2018.
 */

public class MyOrdersProducts {
    @SerializedName("orders")
    @Expose
    private List<MyOrders_Products> orders = null;

    public List<MyOrders_Products> getOrders() {
        return orders;
    }

    public void setOrders(List<MyOrders_Products> orders) {
        this.orders = orders;
    }
}
