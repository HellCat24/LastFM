package com.fancy.lastfm.store;

import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Oleg Mazhukin
 */

public interface ArtistStore {

    Observable<List<Artist>> getTopArtist(String country);

    Observable<List<Album>> getTopAlbum(String artist);

}
