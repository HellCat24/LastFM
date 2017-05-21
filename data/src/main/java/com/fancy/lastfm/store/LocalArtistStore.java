package com.fancy.lastfm.store;

import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Oleg on 21.05.2017.
 */

public class LocalArtistStore implements ArtistStore {

    @Override
    public Observable<List<Artist>> getTopArtist() {
        return null;
    }

    @Override
    public Observable<List<Album>> getTopAlbum() {
        return null;
    }

    public void saveArtistList(List<Artist> artistList){

    }

    public void saveAlbumList(List<Album> albumList){

    }
}
