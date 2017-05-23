package com.fancy.lastfm.response;

import com.fancy.lastfm.entity.Album;

import java.util.List;

/**
 * @author Oleg Mazhukin
 */
public class TopAlbumResponse {

    private List<Album> albumList = null;

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }
}
