package com.example.ic.fixera.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ahmed on 20/10/2018.
 */

public class Profile_Verndor {

    @SerializedName("main_branch")
    @Expose
    private MainBranch mainBranch;
    @SerializedName("general_details")
    @Expose
    private GeneralDetails generalDetails;
    @SerializedName("social_media")
    @Expose
    private SocialMedia socialMedia;
    @SerializedName("location")
    @Expose
    private List<Location_Vendor> location = null;

    public MainBranch getMainBranch() {
        return mainBranch;
    }

    public void setMainBranch(MainBranch mainBranch) {
        this.mainBranch = mainBranch;
    }

    public GeneralDetails getGeneralDetails() {
        return generalDetails;
    }

    public void setGeneralDetails(GeneralDetails generalDetails) {
        this.generalDetails = generalDetails;
    }

    public SocialMedia getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMedia socialMedia) {
        this.socialMedia = socialMedia;
    }

    public List<Location_Vendor> getLocation() {
        return location;
    }

    public void setLocation(List<Location_Vendor> location) {
        this.location = location;
    }

}
