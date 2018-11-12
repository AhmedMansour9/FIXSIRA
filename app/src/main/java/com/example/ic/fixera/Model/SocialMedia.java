package com.example.ic.fixera.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed on 20/10/2018.
 */

public class SocialMedia {


    @SerializedName("fb_follow_us_url")
    @Expose
    private String fbFollowUsUrl;
    @SerializedName("twitter_follow_us_url")
    @Expose
    private String twitterFollowUsUrl;
    @SerializedName("linkedin_follow_us_url")
    @Expose
    private String linkedinFollowUsUrl;
    @SerializedName("dribbble_follow_us_url")
    @Expose
    private String dribbbleFollowUsUrl;
    @SerializedName("google_plus_follow_us_url")
    @Expose
    private String googlePlusFollowUsUrl;
    @SerializedName("instagram_follow_us_url")
    @Expose
    private String instagramFollowUsUrl;
    @SerializedName("youtube_follow_us_url")
    @Expose
    private String youtubeFollowUsUrl;

    public String getFbFollowUsUrl() {
        return fbFollowUsUrl;
    }

    public void setFbFollowUsUrl(String fbFollowUsUrl) {
        this.fbFollowUsUrl = fbFollowUsUrl;
    }

    public String getTwitterFollowUsUrl() {
        return twitterFollowUsUrl;
    }

    public void setTwitterFollowUsUrl(String twitterFollowUsUrl) {
        this.twitterFollowUsUrl = twitterFollowUsUrl;
    }

    public String getLinkedinFollowUsUrl() {
        return linkedinFollowUsUrl;
    }

    public void setLinkedinFollowUsUrl(String linkedinFollowUsUrl) {
        this.linkedinFollowUsUrl = linkedinFollowUsUrl;
    }

    public String getDribbbleFollowUsUrl() {
        return dribbbleFollowUsUrl;
    }

    public void setDribbbleFollowUsUrl(String dribbbleFollowUsUrl) {
        this.dribbbleFollowUsUrl = dribbbleFollowUsUrl;
    }

    public String getGooglePlusFollowUsUrl() {
        return googlePlusFollowUsUrl;
    }

    public void setGooglePlusFollowUsUrl(String googlePlusFollowUsUrl) {
        this.googlePlusFollowUsUrl = googlePlusFollowUsUrl;
    }

    public String getInstagramFollowUsUrl() {
        return instagramFollowUsUrl;
    }

    public void setInstagramFollowUsUrl(String instagramFollowUsUrl) {
        this.instagramFollowUsUrl = instagramFollowUsUrl;
    }

    public String getYoutubeFollowUsUrl() {
        return youtubeFollowUsUrl;
    }

    public void setYoutubeFollowUsUrl(String youtubeFollowUsUrl) {
        this.youtubeFollowUsUrl = youtubeFollowUsUrl;
    }
}
