package com.fancy.lastfm.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class Album {

    @Id
    @SerializedName("mbid")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("playcount")
    private Integer playCount;
    @SerializedName("url")
    private String url;

    private String artistName;

    @Generated(hash = 2031643553)
    public Album(String id, String name, Integer playCount, String url,
            String artistName) {
        this.id = id;
        this.name = name;
        this.playCount = playCount;
        this.url = url;
        this.artistName = artistName;
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

    public void setArtist(String artist) {
        this.artistName = artist;
    }

    public String getArtist() {
        return this.artistName;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}