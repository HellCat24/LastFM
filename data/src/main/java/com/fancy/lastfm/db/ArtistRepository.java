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
        switch (cacheState) {
            case LOCAL:
                return getLocalTopArtist();
            case REMOTE:
                return getRemoteTopArtist();
            default:
                return Observable.concatDelayError(Arrays.asList(getLocalTopArtist(), getRemoteTopArtist()));
        }
    }

    public Observable<List<Album>> getTopAlbums(String artist) {
        switch (cacheState) {
            case LOCAL:
                return getLocalTopAlbum(artist);
            case REMOTE:
                return getRemoteTopAlbums(artist);
            default:
                return Observable.concatDelayError(Arrays.asList(getLocalTopAlbum(artist), getRemoteTopAlbums(artist)));
        }
    }

    private Observable<List<Artist>> getLocalTopArtist() {
        return localArtistStore.getTopArtist(getSelectedCountry());
    }

    public String getSelectedCountry() {
        return localArtistStore.getSelectedCountry();
    }

    public Observable<List<Album>> getTopAlbum(String artist) {
        return Observable.concatDelayError(Arrays.asList(getLocalTopAlbum(artist), getRemoteTopAlbum(artist)));
    }

    private Observable<List<Album>> getRemoteTopAlbum(String artist) {
        return remoteArtistStore.getTopAlbum(artist);
    }

    private Observable<List<Album>> getLocalTopAlbum(String artist) {
        return localArtistStore.getTopAlbum(artist);
    }

    private Observable<List<Artist>> getRemoteTopArtist() {
        return remoteArtistStore.getTopArtist(getSelectedCountry()).doAfterNext(artists -> localArtistStore.saveArtistList(artists));
    }

    private Observable<List<Album>> getRemoteTopAlbums(String artist) {
        return getRemoteTopAlbum(artist).doAfterNext(albums -> localArtistStore.saveAlbumList(albums));
    }
}
