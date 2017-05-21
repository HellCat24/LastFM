package com.fancy.lastfm.db;

import com.fancy.lastfm.CacheState;
import com.fancy.lastfm.db.base.CacheRepository;
import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;
import com.fancy.lastfm.store.LocalArtistStore;
import com.fancy.lastfm.store.RemoteArtistStore;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Oleg on 21.05.2017.
 */
public class ArtistRepository extends CacheRepository {

    private LocalArtistStore localArtistStore;
    private RemoteArtistStore remoteArtistStore;

    public ArtistRepository(CacheState cacheState, LocalArtistStore localArtistStore, RemoteArtistStore remoteArtistStore) {
        super(cacheState);
        this.localArtistStore = localArtistStore;
        this.remoteArtistStore = remoteArtistStore;
    }

    public Observable<List<Artist>> getTopArtist() {
        return Observable.concatDelayError(Arrays.asList(localArtistStore.getTopArtist(getSelectedCountry()), getRemoteTopArtist(getSelectedCountry())));
    }

    public String getSelectedCountry() {
        return localArtistStore.getSelectedCountry();
    }

    public Observable<List<Album>> getTopAlbum(String artist) {
        return Observable.concatDelayError(Arrays.asList(localArtistStore.getTopAlbum(artist), remoteArtistStore.getTopAlbum(artist)));
    }

    private Observable<List<Artist>> getRemoteTopArtist(String country) {
        return remoteArtistStore.getTopArtist(country).doAfterNext(artists -> localArtistStore.saveArtistList(artists));
    }

    private Observable<List<Album>> getRemoteTopAlbums(String artist) {
        return remoteArtistStore.getTopAlbum(artist).doAfterNext(albums -> localArtistStore.saveAlbumList(albums));
    }
}
