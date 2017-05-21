package com.fancy.lastfm.store;

import com.fancy.lastfm.api.LastFmApi;
import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Oleg on 21.05.2017.
 */

public class RemoteArtistStore implements ArtistStore {

    private LastFmApi lastFmApi;

    @Override
    public Observable<List<Artist>> getTopArtist() {
        return null;
    }

    @Override
    public Observable<List<Album>> getTopAlbum() {
        return null;
    }
}
