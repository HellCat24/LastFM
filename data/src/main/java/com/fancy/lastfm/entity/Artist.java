package com.fancy.lastfm.entity;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;

@Entity
public class Artist {

    @SerializedName("name")
    private String name;
    @SerializedName("listeners")
    private String listenersCount;

    private String imageUrl = null;

    public String getName() {
        return name;
    }

    public String getListenersCount() {
        return listenersCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}