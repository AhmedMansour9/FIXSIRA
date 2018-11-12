package com.example.ic.fixera.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed on 20/10/2018.
 */

public class GeneralDetails {

    @SerializedName("cover_img")
    @Expose
    private String coverImg;
    @SerializedName("vendor_home_page_cats")
    @Expose
    private String vendorHomePageCats;
    @SerializedName("google_map_app_key")
    @Expose
    private String googleMapAppKey;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getVendorHomePageCats() {
        return vendorHomePageCats;
    }

    public void setVendorHomePageCats(String vendorHomePageCats) {
        this.vendorHomePageCats = vendorHomePageCats;
    }

    public String getGoogleMapAppKey() {
        return googleMapAppKey;
    }

    public void setGoogleMapAppKey(String googleMapAppKey) {
        this.googleMapAppKey = googleMapAppKey;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
