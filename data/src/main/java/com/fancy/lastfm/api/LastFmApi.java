package com.fancy.lastfm.api;

import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Oleg on 21.05.2017.
 */
public interface LastFmApi {

    @GET("geo.gettopartists")
    Observable<List<Artist>> getTopArtist(@Path("country") String country);

    @GET("artist.gettopalbums")
    Observable<List<Album>> getTopAlbum(@Path("artist") String artist);
}
