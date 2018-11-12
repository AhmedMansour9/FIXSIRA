package com.example.ic.fixera.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed on 20/10/2018.
 */

public class Profile_Data {

    @SerializedName("profile")
    @Expose
    private Profile_Verndor profile;

    public Profile_Verndor getProfile() {
        return profile;
    }

    public void setProfile(Profile_Verndor profile) {
        this.profile = profile;
    }
}
