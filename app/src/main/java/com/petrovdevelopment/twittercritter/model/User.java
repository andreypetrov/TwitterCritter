package com.petrovdevelopment.twittercritter.model; /**
 * Coding is fun!
 * Created by Andrey on 2018-02-19.
 */

import java.util.HashMap;
import java.util.Map;

public class User {

    private Long id;
    private String id_str;
    private String name;
    private String screenName;
    private String description;
    private String url;
    private String profile_background_image_url;
    private String profile_background_image_url_https;
    private String profile_image_url;
    private String profile_image_url_https;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdStr() {
        return id_str;
    }

    public void setIdStr(String id_str) {
        this.id_str = id_str;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProfileBackgroundImageUrl() {
        return profile_background_image_url;
    }

    public void setProfileBackgroundImageUrl(String profile_background_image_url) {
        this.profile_background_image_url = profile_background_image_url;
    }

    public String getProfileBackgroundImageUrlHttps() {
        return profile_background_image_url_https;
    }

    public void setProfileBackgroundImageUrlHttps(String profile_background_image_url_https) {
        this.profile_background_image_url_https = profile_background_image_url_https;
    }

    public String getProfileImageUrl() {
        return profile_image_url;
    }

    public void setProfileImageUrl(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public String getProfileImageUrlHttps() {
        return profile_image_url_https;
    }

    public void setProfileImageUrlHttps(String profile_image_url_https) {
        this.profile_image_url_https = profile_image_url_https;
    }
}
