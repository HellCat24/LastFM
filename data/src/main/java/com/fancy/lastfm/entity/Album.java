package com.fancy.lastfm.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("playcount")
    @Expose
    private Integer playCount;
    @SerializedName("url")
    private String url;

    public String getName() {
        return name;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public String getUrl() {
        return url;
    }
}