package com.fancy.lastfm.entity;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

@Entity
@SuppressWarnings("serial")
public class Artist implements Serializable {

    @SerializedName("name")
    private String name;
    @SerializedName("listeners")
    private String listenersCount;

    private String imageUrl = null;

    @Generated(hash = 1259686718)
    public Artist(String name, String listenersCount, String imageUrl) {
        this.name = name;
        this.listenersCount = listenersCount;
        this.imageUrl = imageUrl;
    }

    @Generated(hash = 19829037)
    public Artist() {
    }

    public String getName() {
        return name;
    }

    public String getListenersCount() {
        return listenersCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setListenersCount(String listenersCount) {
        this.listenersCount = listenersCount;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}