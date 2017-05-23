package com.fancy.lastfm.store;

import com.fancy.lastfm.api.LastFmApi;
import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;
import com.fancy.lastfm.response.TopAlbumResponse;
import com.fancy.lastfm.response.TopArtistsResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Oleg Mazhukin
 */

public class RemoteArtistStore implements ArtistStore {

    private LastFmApi lastFmApi;

    public RemoteArtistStore(LastFmApi lastFmApi) {
        this.lastFmApi = lastFmApi;
    }

    @Override
    public Observable<List<Artist>> getTopArtist(String country) {
        return lastFmApi.getTopArtist(country).map(TopArtistsResponse::getArtist);
    }

    @Override
    public Observable<List<Album>> getTopAlbum(String artist) {
        return lastFmApi.getTopAlbum(artist).map(TopAlbumResponse::getAlbumList);
    }
}
