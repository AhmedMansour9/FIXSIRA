package com.example.ic.fixera.Model;

/**
 * Created by Ahmed on 13/10/2018.
 */

public class Details_Service {
    private String id;
    private String vendorName;
    private String userPhotoUrl;
    private String name;
    private String address;
    private String description;
    private String phone;
    private String telephone;
    private String lat;
    private String lng;
    private String rate;
    private double distance;
    private String tybe;
    private String Price;
    private String evragerate;
    private String Total_rate;
    private String vendor_id;
   private String services_id;
   private String TotalPrice;

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getServices_id() {
        return services_id;
    }

    public void setServices_id(String services_id) {
        this.services_id = services_id;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getEvragerate() {
        return evragerate;
    }

    public void setEvragerate(String evragerate) {
        this.evragerate = evragerate;
    }

    public String getTotal_rate() {
        return Total_rate;
    }

    public void setTotal_rate(String total_rate) {
        Total_rate = total_rate;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getTybe() {
        return tybe;
    }

    public void setTybe(String tybe) {
        this.tybe = tybe;
    }
}
