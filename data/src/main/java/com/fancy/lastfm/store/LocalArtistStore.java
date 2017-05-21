package com.fancy.lastfm.store;

import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Oleg on 21.05.2017.
 */

public class LocalArtistStore implements ArtistStore {

    public void saveArtistList(List<Artist> artistList){

    }

    public void saveAlbumList(List<Album> albumList){

    }

    @Override
    public Observable<List<Artist>> getTopArtist(String country) {
        return null;
    }

    @Override
    public Observable<List<Album>> getTopAlbum(String artist) {
        return null;
    }

    public String getSelectedCountry(){

        return null;
    }

    public void saveSelectedCountry(String country){

    }
}
