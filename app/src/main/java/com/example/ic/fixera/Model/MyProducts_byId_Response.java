package com.example.ic.fixera.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed on 17/10/2018.
 */

public class MyProducts_byId_Response {
    @SerializedName("data")
    @Expose
    private InfoOrders data;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("error")
    @Expose
    private String error;

    public InfoOrders getData() {
        return data;
    }

    public void setData(InfoOrders data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
