package com.example.ic.fixera.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed on 11/10/2018.
 */

public class CarWashing {

    @SerializedName("centerId")
    @Expose
    private Integer id;
    @SerializedName("vendorId")
    @Expose
    private String vendorId;
    @SerializedName("vendorName")
    @Expose
    private String vendorName;
    @SerializedName("image")
    @Expose
    private String userPhotoUrl;
    @SerializedName("centerName")
    @Expose
    private String name;
    @SerializedName("centerAddress")
    @Expose
    private String address;
    @SerializedName("centerDescription")
    @Expose
    private String description;
    @SerializedName("centerPhone")
    @Expose
    private String phone;
    @SerializedName("centerTelephone")
    @Expose
    private String telephone;
    @SerializedName("centerLat")
    @Expose
    private String lat;
    @SerializedName("centerLng")
    @Expose
    private String lng;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("visit_price")
    @Expose
    private String visitPrice;
    @SerializedName("totalPrice")
    @Expose
    private String totalPrice;
    @SerializedName("total")
    @Expose
    private Integer rateTotal;
    @SerializedName("rating")
    @Expose
    private Integer rateAverage;

    @SerializedName("servicesId")
    @Expose
    private String servicesId;

    @SerializedName("pullImage")
    @Expose
    private String pullImage;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public void setUserPhotoUrl(String userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVisitPrice() {
        return visitPrice;
    }

    public void setVisitPrice(String visitPrice) {
        this.visitPrice = visitPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getRateTotal() {
        return rateTotal;
    }

    public void setRateTotal(Integer rateTotal) {
        this.rateTotal = rateTotal;
    }

    public Integer getRateAverage() {
        return rateAverage;
    }

    public void setRateAverage(Integer rateAverage) {
        this.rateAverage = rateAverage;
    }

    public String getServicesId() {
        return servicesId;
    }

    public void setServicesId(String servicesId) {
        this.servicesId = servicesId;
    }

    public String getPullImage() {
        return pullImage;
    }

    public void setPullImage(String pullImage) {
        this.pullImage = pullImage;
    }

}
