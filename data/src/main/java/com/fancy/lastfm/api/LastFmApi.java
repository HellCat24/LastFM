package com.fancy.lastfm.api;

import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;
import com.fancy.lastfm.response.TopAlbumResponse;
import com.fancy.lastfm.response.TopArtistsResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Oleg Mazhukin
 */
public interface LastFmApi {

    @GET("?method=geo.gettopartists")
    Observable<TopArtistsResponse> getTopArtist(@Query("country") String country);

    @GET("?method=artist.gettopalbums")
    Observable<TopAlbumResponse> getTopAlbum(@Query("artist") String artist);
}
