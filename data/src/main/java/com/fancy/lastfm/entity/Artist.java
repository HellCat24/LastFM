package com.fancy.lastfm.entity;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

@Entity
@SuppressWarnings("serial")
public class Artist implements Serializable {

    @Id
    @SerializedName("mbid")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("listeners")
    private String listenersCount;
    
    private String imageUrl = null;

    @Generated(hash = 454948368)
    public Artist(String id, String name, String listenersCount, String imageUrl) {
        this.id = id;
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

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}