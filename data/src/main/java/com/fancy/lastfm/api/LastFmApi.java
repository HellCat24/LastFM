package com.fancy.lastfm.api;

import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;

import io.reactivex.Observable;

/**
 * Created by Oleg on 21.05.2017.
 */
public interface LastFmApi {

    Observable<Artist> getTopArtist();

    Observable<Album> getTopAlbum();
}
