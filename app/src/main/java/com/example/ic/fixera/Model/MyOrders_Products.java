package com.example.ic.fixera.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ahmed on 17/10/2018.
 */

public class MyOrders_Products {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("_order_date")
    @Expose
    private String orderDate;
    @SerializedName("_order_currency")
    @Expose
    private String orderCurrency;
    @SerializedName("_order_total")
    @Expose
    private String orderTotal;
    @SerializedName("_final_order_total")
    @Expose
    private String finalOrderTotal;
    @SerializedName("_order_status")
    @Expose
    private String orderStatus;
    @SerializedName("_sub_order")
    @Expose
    private List<Object> subOrder = null;
    @SerializedName("is_review")
    @Expose
    private Boolean isReview;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderCurrency() {
        return orderCurrency;
    }

    public void setOrderCurrency(String orderCurrency) {
        this.orderCurrency = orderCurrency;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getFinalOrderTotal() {
        return finalOrderTotal;
    }

    public void setFinalOrderTotal(String finalOrderTotal) {
        this.finalOrderTotal = finalOrderTotal;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Object> getSubOrder() {
        return subOrder;
    }

    public void setSubOrder(List<Object> subOrder) {
        this.subOrder = subOrder;
    }

    public Boolean getIsReview() {
        return isReview;
    }

    public void setIsReview(Boolean isReview) {
        this.isReview = isReview;
    }


}
