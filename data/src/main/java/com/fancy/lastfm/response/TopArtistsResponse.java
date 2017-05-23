package com.fancy.lastfm.response;

import com.fancy.lastfm.entity.Artist;

import java.util.List;

public class TopArtistsResponse {

    private List<Artist> artist = null;

    public List<Artist> getArtist() {
        return artist;
    }

    public void setArtist(List<Artist> artist) {
        this.artist = artist;
    }
}