package com.fancy.lastfm.store;

import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Oleg on 21.05.2017.
 */

public interface ArtistStore {

    Observable<List<Artist>> getTopArtist();

    Observable<List<Album>> getTopAlbum();

}
