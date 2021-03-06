package com.example.ic.fixera.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ic on 10/8/2018.
 */

public class Sparts_AnotherDetails {
    private String id;
    private String  authorId;
    private String content;
    private String contentar;

    private String title;
    private String titlear;
    private String slug;
    private Integer status;
    private String sku;
    private String regularPrice;
    private String salePrice;
    private Integer price;
    private String stockQty;
    private String stockAvailability;
    private String type;
    private String imageUrl;
    private String createdAt;
    private String updatedAt;
    private String vendorname;
    private Integer average;
    private  String average_total;
    private String Vendor_Phone;

    public String getContentar() {
        return contentar;
    }

    public void setContentar(String contentar) {
        this.contentar = contentar;
    }

    public String getTitlear() {
        return titlear;
    }

    public void setTitlear(String titlear) {
        this.titlear = titlear;
    }

    public String getVendor_Phone() {
        return Vendor_Phone;
    }

    public void setVendor_Phone(String vendor_Phone) {
        Vendor_Phone = vendor_Phone;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public Integer getAverage() {
        return average;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }

    public String getAverage_total() {
        return average_total;
    }

    public void setAverage_total(String average_total) {
        this.average_total = average_total;
    }

    public Sparts_AnotherDetails(){

   };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String  getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String  authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getStockQty() {
        return stockQty;
    }

    public void setStockQty(String stockQty) {
        this.stockQty = stockQty;
    }

    public String getStockAvailability() {
        return stockAvailability;
    }

    public void setStockAvailability(String stockAvailability) {
        this.stockAvailability = stockAvailability;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
