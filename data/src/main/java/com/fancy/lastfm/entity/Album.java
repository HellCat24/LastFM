package com.fancy.lastfm.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Album {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("playcount")
    @Expose
    private Integer playCount;
    @SerializedName("url")
    private String url;

    @Generated(hash = 1008081261)
    public Album(String name, Integer playCount, String url) {
        this.name = name;
        this.playCount = playCount;
        this.url = url;
    }

    @Generated(hash = 1609191978)
    public Album() {
    }

    public String getName() {
        return name;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}